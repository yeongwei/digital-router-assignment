package com.digitalroute;

import com.digitalroute.aggregation.cdr.CdrAggregationEngine;
import com.digitalroute.common.record.aggregation.AggregationKey;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.common.record.aggregation.AggregationSummary;
import com.digitalroute.input.schema.CdrTextInputSchema;
import com.digitalroute.processor.TextInputCallRecordsProcessor;
import com.digitalroute.output.BillingGateway;
import com.digitalroute.record.cdr.CdrRecord;
import com.digitalroute.common.record.Record;

import java.util.HashMap;
import java.util.HashSet;

final public class MyTextInputCallRecordsProcessor extends TextInputCallRecordsProcessor {

    private HashSet<Integer> occuredSeqNum = new HashSet();
    private CdrAggregationEngine cdrAggregationEngine = new CdrAggregationEngine();

    public MyTextInputCallRecordsProcessor(BillingGateway billingGateway) {
        super(billingGateway, new CdrTextInputSchema());
    }

    @Override
    protected void begin() {
        billingGateway().beginBatch();
    }

    @Override
    protected void process(Record record) {
        CdrRecord cdrRecord = transform(record);
        if (cdrRecord == null) {
            // Not a valid CdrRecord
        } else {
            if (!occuredSeqNum.add(cdrRecord.seqNum())) {
                logError(BillingGateway.ErrorCause.DUPLICATE_SEQ_NO, cdrRecord);
            } else {
                if (cdrRecord.onGoingCall()) {
                    cdrAggregationEngine.put(cdrRecord);
                } else if (cdrRecord.endCall()) {
                    cdrAggregationEngine.put(cdrRecord);
                    AggregationRecord aggregationRecord = cdrAggregationEngine.get(cdrRecord);
                    billingGateway().consume(
                            cdrRecord.callId(),
                            aggregationRecord.valueOf("seqNum").valueInt(),
                            cdrRecord.aNum(),
                            cdrRecord.bNum(),
                            aggregationRecord.valueOf("causeForOutput").valueByte(),
                            aggregationRecord.valueOf("duration").valueInt());
                    cdrAggregationEngine.remove(cdrRecord);
                } else if (cdrRecord.incompleteCall()) {
                    boolean result = cdrAggregationEngine.putExistFirst(cdrRecord);
                    if (!result) logError(BillingGateway.ErrorCause.NO_MATCH, cdrRecord);
                } else {
                    // Not a valid causeForOutput
                }
            }
        }
    }

    private CdrRecord transform(Record record) {
        try { return (CdrRecord) record; }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void postProcess() {
        HashMap<AggregationKey, AggregationRecord> aggregations = cdrAggregationEngine.all();
        for (AggregationKey aggregationKey : aggregations.keySet()) {
            AggregationRecord aggregationRecord = aggregations.get(aggregationKey);
            billingGateway().consume(
                    aggregationKey.valueOf("callId").valueString(),
                    aggregationRecord.valueOf("seqNum").valueInt(),
                    aggregationKey.valueOf("aNum").valueString(),
                    aggregationKey.valueOf("bNum").valueString(),
                    aggregationRecord.valueOf("causeForOutput").valueByte(),
                    aggregationRecord.valueOf("duration").valueInt());
        }
    }

    @Override
    protected void end() {
        AggregationSummary aggregationSummary = cdrAggregationEngine.summary();
        billingGateway().endBatch(new Long(aggregationSummary.valueOf("duration").valueInt()));
    }

    protected void logError(BillingGateway.ErrorCause errorCause, CdrRecord record) {
        billingGateway().logError(errorCause, record.callId(), record.seqNum(), record.aNum(), record.bNum());
    }
}

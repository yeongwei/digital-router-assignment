package com.digitalroute;

import com.digitalroute.input.schema.CdrTextInputSchema;
import com.digitalroute.processor.TextInputCallRecordsProcessor;
import com.digitalroute.output.BillingGateway;
import com.digitalroute.record.cdr.CdrRecord;
import com.digitalroute.common.record.Record;

final public class MyTextInputCallRecordsProcessor extends TextInputCallRecordsProcessor {
    public MyTextInputCallRecordsProcessor(BillingGateway billingGateway) {
        super(billingGateway, new CdrTextInputSchema());
    }

    @Override
    protected void begin() {
        billingGateway().beginBatch();
    }

    @Override
    protected void process(Record record) {
        try {
            CdrRecord cdr = (CdrRecord) record;
            System.out.println(cdr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void postProcess() {
        System.out.println("postProcess");
    }

    @Override
    protected void end() { billingGateway().endBatch(0l); } // TODO: Implementation needed

    protected void logError(BillingGateway.ErrorCause errorCause, CdrRecord record) {
        billingGateway().logError(errorCause, record.callId(), record.seqNum(), record.aNum(), record.bNum());
    }
}

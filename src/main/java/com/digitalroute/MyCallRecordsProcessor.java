package com.digitalroute;

import com.digitalroute.input.CallRecordsProcessor;
import com.digitalroute.output.BillingGateway;
import com.digitalroute.record.AggregationResult;
import com.digitalroute.record.CallDataRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

public class MyCallRecordsProcessor implements CallRecordsProcessor {
    private Logger Log = Logger.getLogger(this.getClass().getName());
    private BillingGateway billingGateway;
    private AggregationStash aggregationStash = new AggregationStash();
    private HashSet<Integer> occuredSeqNum = new HashSet();

    public MyCallRecordsProcessor(BillingGateway billingGateway) {
        this.billingGateway = billingGateway;
    }

    /**
     * Should be used to process CDR(Call Data Records) batches from an {@link InputStream}.
     * Look at {@link Application#main(String[])} for an example
     *
     * @param in InputStream that should contain Call Data Records
     */
    @Override
    public void processBatch(InputStream in) {
        billingGateway.beginBatch();
        InputStreamReader inReader = null;
        BufferedReader buffReader = null;
        try {
            inReader = new InputStreamReader(in, "UTF-8");
            buffReader = new BufferedReader(inReader);
            String line;
            while ((line = buffReader.readLine()) != null)
                dispatch(line);
            handlesRemaining();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffReader != null)
                    buffReader.close();
                if (inReader != null)
                    inReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        billingGateway.endBatch(aggregationStash.getTotalDuration());
        aggregationStash.reset();
    }

    /**
     * Process each CDR record
     * @param line
     */
    protected void dispatch(String line) {
        try {
            CallDataRecord cdr = new CallDataRecord(line);
            if (!cdr.isValid()) {
                // Log.warning("Invalid CDR Record " + cdr);
                return;
            }

            if (!occuredSeqNum.add(cdr.getSeqNum())) {
                logError(BillingGateway.ErrorCause.DUPLICATE_SEQ_NO, cdr);
                return;
            }

            if (cdr.isOnGoing()) {
                aggregationStash.add(cdr);
            } else if (cdr.isEnd()) {
                aggregationStash.add(cdr);
                AggregationResult aggregationResult = aggregationStash.compute(cdr);
                consume(aggregationResult);
                aggregationStash.remove(cdr);
            } else if (cdr.isIncomplete()) {
                boolean result = aggregationStash.leach(cdr);
                if (!result)
                    logError(BillingGateway.ErrorCause.NO_MATCH, cdr);
            } else {
                // Log.warning("Invalid CDR causeForOutput " + cdr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles CDR records that are NOT ENDED
     */
    public void handlesRemaining() {
        if (aggregationStash.nonEmpty())
            for (AggregationResult aggregationResult : aggregationStash.getAll())
                consume(aggregationResult);
    }

    /**
     * Wrapper
     * @param errorCause
     * @param cdr
     */
    private void logError(BillingGateway.ErrorCause errorCause, CallDataRecord cdr) {
        billingGateway.logError(errorCause, cdr.getCallId(), cdr.getSeqNum(), cdr.getaNum(), cdr.getbNum());
    }

    /**
     * Wrapper
     * @param aggregationResult
     */
    private void consume(AggregationResult aggregationResult) {
        billingGateway.consume(aggregationResult.getCallId(), aggregationResult.getHighestSeqNum(),
                aggregationResult.getaNum(), aggregationResult.getbNum(), aggregationResult.getLastCauseForOutput(),
                aggregationResult.getTotalDuration());
    }
}

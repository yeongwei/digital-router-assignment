package com.digitalroute.record;

import java.util.ArrayList;

public class AggregationRecords {
    private ArrayList<CallDataRecord> cdrs = new ArrayList();
    private int highestSeqNum;
    private int totalDuration;
    private byte lastCauseForOutput;

    public Boolean add(CallDataRecord cdr) {
        updateSeqHighestNum(cdr);
        accumulateDuration(cdr);
        updateLastCause(cdr);
        return cdrs.add(cdr);
    }

    private void updateSeqHighestNum(CallDataRecord cdr) {
        if (highestSeqNum < cdr.getSeqNum())
            highestSeqNum = cdr.getSeqNum();
    }

    private void accumulateDuration(CallDataRecord cdr) {
        totalDuration += cdr.getDuration();
    }

    private void updateLastCause(CallDataRecord cdr) {
        lastCauseForOutput = cdr.getCauseForOutput();
    }

    public int getHighestSeqNum() {
        return highestSeqNum;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public byte getLastCauseForOutput() {
        return lastCauseForOutput;
    }
}

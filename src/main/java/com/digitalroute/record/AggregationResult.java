package com.digitalroute.record;

public class AggregationResult {
    private String callId;
    private int highestSeqNum;
    private String aNum;
    private String bNum;
    private byte lastCauseForOutput;
    private int totalDuration;

    public AggregationResult(String callId, int highestSeqNum, String aNum, String bNum, byte lastCauseForOutput, int totalDuration) {
        this.callId = callId;
        this.highestSeqNum = highestSeqNum;
        this.aNum = aNum;
        this.bNum = bNum;
        this.lastCauseForOutput = lastCauseForOutput;
        this.totalDuration = totalDuration;
    }

    public String getCallId() {
        return this.callId;
    }

    public int getHighestSeqNum() {
        return this.highestSeqNum;
    }

    public String getaNum() {
        return this.aNum;
    }

    public String getbNum() {
        return this.bNum;
    }

    public byte getLastCauseForOutput() {
        return this.lastCauseForOutput;
    }

    public int getTotalDuration() {
        return this.totalDuration;
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("callId: " + callId)
                .append(" highestSeqNum: " + highestSeqNum)
                .append(" aNum: " + aNum)
                .append(" bNum: " + bNum)
                .append(" lastCauseForOutput: " + lastCauseForOutput)
                .append(" totalDuration: " + totalDuration)
                .toString();

    }
}

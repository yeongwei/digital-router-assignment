package com.digitalroute.record;

import java.util.Objects;

public class AggregationKey {
    private String callId;
    private String aNum;
    private String bNum;

    public AggregationKey(CallDataRecord cdr) {
        this.callId = cdr.getCallId();
        this.aNum = cdr.getaNum();
        this.bNum = cdr.getbNum();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            AggregationKey key = (AggregationKey) obj;
            return callId.equals(key.getCallId()) && aNum.equals(key.getaNum()) && bNum.equals(key.getbNum());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(callId, aNum, bNum);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("callId: " + callId)
                .append(" aNum: " + aNum)
                .append(" bNum: " + bNum)
                .toString();
    }

    public String getCallId() {
        return this.callId;
    }

    public String getaNum() {
        return aNum;
    }

    public String getbNum() {
        return bNum;
    }
}

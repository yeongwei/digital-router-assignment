package com.digitalroute.record;

public class CallDataRecord {
    public final static String INCOMPLETE_RECORD_CALL_ID = "_";

    public final static byte INCOMPLETE_CALL = 0;
    public final static byte ON_GOING_CALL = 1;
    public final static byte END_CALL = 2;

    private String callId;
    private int seqNum;
    private String aNum;
    private String bNum;
    private byte causeForOutput;
    private int duration;

    private AggregationKey aggregationKey;
    private AggregationSubKey aggregationSubKey;

    private boolean valid = true;

    // TODO: Need revision. Lazy way of making sure incoming records are alright
    public CallDataRecord(String cdr) {
        try {
            String[] _cdr = cdr.split(":|,");
            callId = _cdr[0];
            seqNum = Integer.parseInt(_cdr[1]);
            aNum = _cdr[2];
            bNum = _cdr[3];
            causeForOutput = Byte.parseByte(_cdr[4]);
            duration = Integer.parseInt(_cdr[5]);

            aggregationKey = new AggregationKey(this);
            aggregationSubKey = new AggregationSubKey(this);
        } catch (Exception e) {
            valid = false;
        }
    }

    public AggregationKey aggregationKey() {
        return aggregationKey;
    }

    public AggregationSubKey aggregationSubKey() {
        return aggregationSubKey;
    }

    public boolean isIncomplete() {
        return causeForOutput == INCOMPLETE_CALL;
    }

    public boolean isOnGoing() {
        return causeForOutput == ON_GOING_CALL;
    }

    public boolean isEnd() {
        return causeForOutput == END_CALL;
    }

    public String getCallId() {
        return callId;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public String getaNum() {
        return aNum;
    }

    public String getbNum() {
        return bNum;
    }

    public byte getCauseForOutput() {
        return causeForOutput;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append("callId: " + callId)
                .append(" seqNum: " + seqNum)
                .append(" aNum: " + aNum)
                .append(" bNum: " + bNum)
                .append(" causeForOutput: " + causeForOutput)
                .append(" duration: " + duration)
                .toString();
    }
}

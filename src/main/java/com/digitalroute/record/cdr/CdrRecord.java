package com.digitalroute.record.cdr;

import com.digitalroute.common.record.Record;

public class CdrRecord extends Record {
    private final static String INCOMPLETE_RECORD_CALL_ID = "_";
    private final static byte INCOMPLETE_CALL = 0;
    private final static byte ON_GOING_CALL = 1;
    private final static byte END_CALL = 2;

    public CdrRecord() {
        super(new CdrRecordSchema());
    }

    // TODO: Use boxed type to return appropriate type to avoid casting

    public String callId() { return (String) get("callId").get(); }

    public int seqNum() { return (int) get("seqNum").get(); }

    public String aNum() { return (String) get("aNum").get(); }

    public String bNum() { return (String) get("bNum").get(); }

    public byte causeForOutput() { return (byte) get("causeForOutput").get(); }

    public int duration() { return (byte) get("duration").get(); }

    public boolean onGoingCall() { return this.causeForOutput() == ON_GOING_CALL; }

    public boolean incompleteCall() { return this.causeForOutput() == INCOMPLETE_CALL; }

    public boolean endCall() { return this.causeForOutput() == END_CALL; }
}

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

    public String callId() { return get("callId").valueString(); }

    public int seqNum() { return get("seqNum").valueInt(); }

    public String aNum() { return (String) get("aNum").valueString(); }

    public String bNum() { return (String) get("bNum").valueString(); }

    public byte causeForOutput() { return get("causeForOutput").valueByte(); }

    public int duration() { return get("duration").valueInt(); }

    public boolean onGoingCall() { return this.causeForOutput() == ON_GOING_CALL; }

    public boolean incompleteCall() { return this.causeForOutput() == INCOMPLETE_CALL; }

    public boolean endCall() { return this.causeForOutput() == END_CALL; }
}

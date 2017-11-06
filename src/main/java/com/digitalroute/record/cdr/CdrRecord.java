package com.digitalroute.record.cdr;

import com.digitalroute.record.common.Field;
import com.digitalroute.record.common.Record;

public class CdrRecord extends Record {
    private final static String INCOMPLETE_RECORD_CALL_ID = "_";
    private final static byte INCOMPLETE_CALL = 0;
    private final static byte ON_GOING_CALL = 1;
    private final static byte END_CALL = 2;

    public CdrRecord() {
        super(new CdrRecordSchema());
    }

    public boolean onGoingCall() {
        return super.get(4) == Byte.valueOf(ON_GOING_CALL);
    }

    public boolean incompleteCall() {
        return super.get(4) == Byte.valueOf(INCOMPLETE_CALL);
    }

    public boolean endCall() {
        return super.get(4) == Byte.valueOf(END_CALL);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

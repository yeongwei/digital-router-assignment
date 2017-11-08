package com.digitalroute.record.cdr;

import org.junit.*;

import static org.junit.Assert.assertTrue;

public class CdrRecordTestSpec {
    @Test
    public void testCdrRecord() {
        CdrRecord cdrRec = new CdrRecord();

        cdrRec.set(0, "A"); // callId
        assertTrue(cdrRec.get(0).get().equals("A"));
        assertTrue(cdrRec.get(0).get() == "A");

        cdrRec.set(1, "10"); // seqNum
        assertTrue(cdrRec.get(1).get().equals(10));
        assertTrue(cdrRec.get(1).get() == Integer.valueOf(10));

        cdrRec.set(4, "0"); // causeForOutput
        assertTrue(cdrRec.get(4).get().equals((byte) 0));
        assertTrue(cdrRec.get(4).get() == Byte.valueOf((byte) 0));

        cdrRec.set("callId", "B"); // callId
        assertTrue(cdrRec.get("callId").get().equals("B"));
        assertTrue(cdrRec.get("callId").get() == "B");
    }
}

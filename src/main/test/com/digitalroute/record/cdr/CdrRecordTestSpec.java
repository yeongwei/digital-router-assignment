package com.digitalroute.record.cdr;

import org.junit.*;

import static org.junit.Assert.assertTrue;

public class CdrRecordTestSpec {
    @Test
    public void testCdrRecord() {
        CdrRecord cdrRec = new CdrRecord();

        cdrRec.set(0, "A"); // callId
        assertTrue(cdrRec.get(0).value().equals("A"));
        assertTrue(cdrRec.get(0).value() == "A");
        assertTrue(cdrRec.callId().equals("A"));

        cdrRec.set(1, "10"); // seqNum
        assertTrue(cdrRec.get(1).value().equals(10));
        assertTrue(cdrRec.get(1).value() == Integer.valueOf(10));
        assertTrue(cdrRec.seqNum() == 10);

        cdrRec.set(4, "0"); // causeForOutput
        assertTrue(cdrRec.get(4).value().equals((byte) 0));
        assertTrue(cdrRec.get(4).value() == Byte.valueOf((byte) 0));
        assertTrue(cdrRec.causeForOutput() == 0);

        cdrRec.set("callId", "B"); // callId
        assertTrue(cdrRec.get("callId").value().equals("B"));
        assertTrue(cdrRec.get("callId").value() == "B");
        assertTrue(cdrRec.callId().equals("B"));
    }
}

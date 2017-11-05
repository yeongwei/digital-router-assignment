package com.digitalroute.record.cdr;

import com.digitalroute.record.cdr.CdrRecord;
import org.junit.*;

import static org.junit.Assert.assertTrue;

public class CdrRecordTestSpec {
    @Test
    /*
        Assumed text input
     */
    public void testCdrRecord() {
        CdrRecord cdrRec = new CdrRecord();

        cdrRec.set(0, "A"); // callId
        assertTrue(cdrRec.get(0).equals("A"));
        assertTrue(cdrRec.get(0) == "A");

        cdrRec.set(1, "10"); // seqNum
        assertTrue(cdrRec.get(1).equals(10));
        assertTrue(cdrRec.get(1) == Integer.valueOf(10));

        cdrRec.set(4, "0"); // causeForOutput
        assertTrue(cdrRec.get(4).equals((byte) 0));
        assertTrue(cdrRec.get(4) == Byte.valueOf((byte) 0));
    }
}

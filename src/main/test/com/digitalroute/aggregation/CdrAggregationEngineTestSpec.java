package com.digitalroute.aggregation;

import com.digitalroute.aggregation.cdr.CdrAggregationEngine;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.record.cdr.CdrRecord;
import org.junit.Test;

import static org.junit.Assert.*;

public class CdrAggregationEngineTestSpec {
    @Test
    public void test() {
        CdrAggregationEngine cdrAggregationEngine = new CdrAggregationEngine();

        CdrRecord cdrRec1 = new CdrRecord();
        cdrRec1.set(0, "A");
        cdrRec1.set(1, 10);
        cdrRec1.set(2, "11");
        cdrRec1.set(3, "22");
        cdrRec1.set(4, (byte) 2);
        cdrRec1.set(5, 10);

        cdrAggregationEngine.put(cdrRec1);
        cdrAggregationEngine.put(cdrRec1);
        cdrAggregationEngine.putExistFirst(cdrRec1);

        CdrRecord cdrRec2 = new CdrRecord();
        cdrRec2.set(0, "B");
        cdrRec2.set(1, 10);
        cdrRec2.set(2, "11");
        cdrRec2.set(3, "22");
        cdrRec2.set(4, (byte) 2);
        cdrRec2.set(5, 10);

        cdrAggregationEngine.put(cdrRec2);

        AggregationRecord aggregationRecord1 = cdrAggregationEngine.get(cdrRec1);

        System.out.println(aggregationRecord1.toString());
        assertTrue(aggregationRecord1.toString().equals("AggregationRecord(seqNum: 10, duration: 30)"));

        AggregationRecord aggregationRecord2 = cdrAggregationEngine.get(cdrRec2);

        System.out.println(aggregationRecord2.toString());
        assertTrue(aggregationRecord2.toString().equals("AggregationRecord(seqNum: 10, duration: 10)"));
    }
}

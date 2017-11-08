package com.digitalroute.record.aggregation;

import com.digitalroute.aggregation.AggregationEngine;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.record.cdr.CdrAggregationSpec;
import com.digitalroute.record.cdr.CdrRecord;
import org.junit.Test;

import static org.junit.Assert.*;

public class AggregationEngineTestSpec {
    class SampleAggregationEngine extends AggregationEngine {
        public SampleAggregationEngine() { super(new CdrAggregationSpec()); }
    }

    @Test
    public void test() {
        SampleAggregationEngine engine = new SampleAggregationEngine();

        CdrRecord cdrRec = new CdrRecord();
        cdrRec.set(0, "A");
        cdrRec.set(1, 10);
        cdrRec.set(2, "11");
        cdrRec.set(3, "22");
        cdrRec.set(4, (byte) 2);
        cdrRec.set(5, 10);

        engine.put(cdrRec);
        engine.put(cdrRec);
        engine.put(cdrRec);

        AggregationRecord aggregationRecord = engine.getAndRemove(cdrRec);
        System.out.println(aggregationRecord.toString());
        assertTrue(aggregationRecord.toString().equals("AggregationRecord(seqNum: 10, duration: 30)"));
    }
}

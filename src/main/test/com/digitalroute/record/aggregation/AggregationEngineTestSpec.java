package com.digitalroute.record.aggregation;

import com.digitalroute.aggregation.AggregationEngine;
import com.digitalroute.record.cdr.CdrAggregationSpec;
import com.digitalroute.record.cdr.CdrRecord;
import org.junit.Test;

public class AggregationEngineTestSpec {
    class SampleAggregationEngine extends AggregationEngine {
        public SampleAggregationEngine() {
            super(new CdrAggregationSpec());
        }
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

        System.out.println(engine.get(cdrRec).toString());
    }
}

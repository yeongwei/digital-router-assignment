package com.digitalroute.record.aggregation;

import com.digitalroute.aggregation.AggregationEngine;
import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.aggregation.AggregationKey;
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
        cdrRec.set("callId", "A");
        cdrRec.set("seqNum", 10);
        cdrRec.set("aNum", "11");
        cdrRec.set("bNum", "22");
        cdrRec.set("causeForOutput", (byte) 2);
        cdrRec.set("duration", 10);

        engine.put(cdrRec);
        engine.put(cdrRec);
        cdrRec.set("causeForOutput", (byte) 1);
        engine.put(cdrRec);

        AggregationRecord aggregationRecord = engine.get(cdrRec);
        System.out.println(aggregationRecord.toString());
        assertTrue(aggregationRecord.toString().equals("AggregationRecord(seqNum: 10, causeForOutput: 1, duration: 30)"));
    }
}

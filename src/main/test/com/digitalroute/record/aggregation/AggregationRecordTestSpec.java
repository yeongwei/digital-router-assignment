package com.digitalroute.record.aggregation;

import com.digitalroute.common.record.aggregation.AggregationFormula;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.common.record.aggregation.AggregationType;
import com.digitalroute.record.cdr.CdrRecord;
import com.digitalroute.record.cdr.CdrRecordSchema;
import org.junit.Test;

import static org.junit.Assert.*;

public class AggregationRecordTestSpec {
    @Test
    public void test() {
        CdrRecord cdrRec = new CdrRecord();
        CdrRecordSchema cdrSchema = (CdrRecordSchema) cdrRec.recordSchema();

        AggregationFormula aggFrml1 = new AggregationFormula(cdrSchema.fieldDescriptor(1), AggregationType.MAX); // seqNum
        AggregationFormula aggFrml2 = new AggregationFormula(cdrSchema.fieldDescriptor(5), AggregationType.SUM); // duration

        AggregationRecord aggRec = new AggregationRecord(new AggregationFormula[]{ aggFrml1, aggFrml2 } );

        cdrRec.set(1, 10);
        cdrRec.set(5, 10);
        aggRec.put(cdrRec);

        cdrRec.set(1, 20);
        cdrRec.set(5, 10);
        aggRec.put(cdrRec);

        System.out.println(aggRec.toString());
        assertTrue(aggRec.toString().equals("AggregationRecord(seqNum: 20, duration: 20)"));
    }
}

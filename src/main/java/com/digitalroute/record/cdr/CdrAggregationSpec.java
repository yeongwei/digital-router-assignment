package com.digitalroute.record.cdr;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.record.aggregation.AggregationFormula;
import com.digitalroute.common.record.aggregation.AggregationSpec;

import static com.digitalroute.common.record.aggregation.AggregationType.*;
import static com.digitalroute.record.cdr.CdrRecordSchema.*;

/**
 * Aggregation definition for CDR
 */
final public class CdrAggregationSpec extends AggregationSpec {
    public CdrAggregationSpec() {
        super(new FieldDescriptor[]{ callId, aNum, bNum}, new AggregationFormula[] {
                new AggregationFormula(seqNum, MAX),
                new AggregationFormula(causeForOutput, MAX),
                new AggregationFormula(duration, SUM)
        });
    }
}

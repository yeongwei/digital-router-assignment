package com.digitalroute.record.cdr;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.record.aggregation.AggregationFormula;
import com.digitalroute.common.record.aggregation.AggregationSpec;
import com.digitalroute.common.record.aggregation.AggregationType;

import static com.digitalroute.common.field.FieldType.BYTE;
import static com.digitalroute.common.field.FieldType.INT;
import static com.digitalroute.common.field.FieldType.STRING;

final public class CdrAggregationSpec extends AggregationSpec {
    public CdrAggregationSpec() {
        super(new FieldDescriptor[]{
                new FieldDescriptor("callId", STRING),
                new FieldDescriptor("aNum", STRING),
                new FieldDescriptor("bNum", STRING)
        }, new AggregationFormula[] {
                new AggregationFormula(new FieldDescriptor("seqNum", INT), AggregationType.MAX),
                new AggregationFormula(new FieldDescriptor("causeForOutput", BYTE), AggregationType.MAX),
                new AggregationFormula(new FieldDescriptor("duration", INT), AggregationType.SUM)
        });
    }
}

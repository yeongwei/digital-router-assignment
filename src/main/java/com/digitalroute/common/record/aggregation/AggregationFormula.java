package com.digitalroute.common.record.aggregation;

import com.digitalroute.common.field.FieldDescriptor;

public class AggregationFormula {
    private FieldDescriptor fieldDescriptor;
    private AggregationType aggregationType;

    public AggregationFormula(FieldDescriptor fieldDescriptor, AggregationType aggregationType) {
        this.fieldDescriptor = fieldDescriptor;
        this.aggregationType = aggregationType;
    }

    public FieldDescriptor fieldDescriptor() { return fieldDescriptor; }

    public AggregationType aggregationType() { return aggregationType; }
}

package com.digitalroute.common.record.aggregation;

import com.digitalroute.common.field.FieldDescriptor;

public class AggregationSpec {
    private FieldDescriptor[] keys;
    private AggregationFormula[] formulas;

    public AggregationSpec(FieldDescriptor[] keys, AggregationFormula[] formulas) {
        this.keys = keys;
        this.formulas = formulas;
    }

    public FieldDescriptor[] keys() { return keys; }

    public AggregationFormula[] formulas() { return formulas; }
}

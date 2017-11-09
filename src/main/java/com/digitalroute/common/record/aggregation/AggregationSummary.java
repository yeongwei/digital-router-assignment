package com.digitalroute.common.record.aggregation;

import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.Record;

public class AggregationSummary {
    private AggregationFormula[] aggregationFormulas;
    private Object[] values;

    public AggregationSummary(AggregationFormula[] aggregationFormulas) {
        this.aggregationFormulas = aggregationFormulas;
        this.values = new Object[aggregationFormulas.length];
        for (int i = 0; i < aggregationFormulas.length; i++)
            values[i] = initialize(aggregationFormulas[i].fieldDescriptor().typeCode());
    }

    public Object valueOf(String name) {
        for (int i = 0; i < aggregationFormulas.length; i++) {
            if (aggregationFormulas[i].fieldDescriptor().name().equals(name))
                return values[i];
        }
        return null;
    }

    public void put(Record record) {
        for (int i = 0; i < aggregationFormulas.length; i++) {
            Object value = record.get(aggregationFormulas[i].fieldDescriptor().name()).value();
            FieldValue fv = new FieldValue(aggregationFormulas[i].fieldDescriptor(), value);
            switch (aggregationFormulas[i].aggregationType()) {
                case MAX: if (fv.larger(values[i]))
                    values[i] = fv.value();
                break;
                case SUM:
                    values[i] = fv.add(values[i]);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer aggResStr = new StringBuffer("AggregationSummary(");
        for (int i = 0; i < aggregationFormulas.length; i++) {
            aggResStr.append(aggregationFormulas[i].fieldDescriptor().name() + ": " + values[i]);
            if (i + 1 == aggregationFormulas.length)
                aggResStr.append(")");
            else
                aggResStr.append(", ");
        }
        return super.toString();
    }

    private Object initialize(FieldType typeCode) {
        switch (typeCode) {
            case INT: return new Integer(0);
            case LONG: return new Long(0);
            case BYTE: return (byte) 0;
            default: return 0;
        }
    }
}

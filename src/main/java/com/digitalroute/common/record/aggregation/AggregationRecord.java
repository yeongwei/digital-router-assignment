package com.digitalroute.common.record.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.record.Record;

public class AggregationRecord {
    private AggregationFormula[] aggregationFormulas;
    private Object[] state;

    public AggregationRecord(AggregationFormula[] aggregationFormulas) {
        this.aggregationFormulas = aggregationFormulas;
        this.state = new Object[aggregationFormulas.length];
        for (int i = 0; i < aggregationFormulas.length; i++)
            state[i] = initialize(aggregationFormulas[i].fieldDescriptor().typeCode());
    }

    public boolean put(Record record) {
        for (int i = 0; i < aggregationFormulas.length; i++) {
            final FieldDescriptor fd = aggregationFormulas[i].fieldDescriptor();
            switch (aggregationFormulas[i].aggregationType()) {
                case MAX:
                    if (larger(record.get(fd.name()).get(), state[i], fd.typeCode()))
                        state[i] = record.get(fd.name()).get();
                break;
                case SUM:
                    state[i] = sum(record.get(fd.name()).get(), state[i], fd.typeCode());
                break;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer aggRecStr = new StringBuffer("AggregationRecord(");
        for (int i = 0; i < aggregationFormulas.length; i++) {
            aggRecStr.append(aggregationFormulas[i].fieldDescriptor().name() + ": " + state[i]);
            if (i + 1 == aggregationFormulas.length)
                aggRecStr.append(")");
            else
                aggRecStr.append(", ");
        }
        return aggRecStr.toString();
    }

    // TODO: This needs to go somewhere
    private Object initialize(FieldType typeCode) {
        switch (typeCode) {
            case INT:
                return new Integer(0);
            case LONG:
                return new Long(0);
            default:
                return 0;
        }
    }

    private boolean larger(Object obj1, Object obj2, FieldType typeCode) {
        switch (typeCode) {
            case INT: return (int) obj1 > (int) obj2;
            case LONG: return (long) obj1 > (long) obj2;
            default: return false;
        }
    }

    private Object sum(Object obj1, Object obj2, FieldType typeCode) {
        switch (typeCode) {
            case INT: return ((int) obj1 + (int) obj2);
            case LONG: return ((long) obj1 + (long) obj2);
            default: return 0;
        }
    }
}

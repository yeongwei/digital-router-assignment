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
                    if (record.get(fd.name()).larger(state[i]))
                        state[i] = record.get(fd.name()).value();
                break;
                case SUM:
                    state[i] = record.get(fd.name()).add(state[i]);
                break;
            }
        }
        return true;
    }

    public Object valueOf(String name) {
        for (int i = 0; i < aggregationFormulas.length; i++) {
            if (aggregationFormulas[i].fieldDescriptor().name().equals(name))
                return state[i];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuffer aggRecStr = new StringBuffer("AggregationRecord(");
        for (int i = 0; i < aggregationFormulas.length; i++) {
            aggRecStr.append(aggregationFormulas[i].fieldDescriptor().name() + ": " + state[i]);
            if (i + 1 == aggregationFormulas.length) aggRecStr.append(")");
            else aggRecStr.append(", ");
        }
        return aggRecStr.toString();
    }

    private Object initialize(FieldType typeCode) {
        switch (typeCode) {
            case INT: return new Integer(0);
            case LONG: return new Long(0);
            default: return 0;
        }
    }
}

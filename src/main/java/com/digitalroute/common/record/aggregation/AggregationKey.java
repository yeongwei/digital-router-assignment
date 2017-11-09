package com.digitalroute.common.record.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldValue;

import java.util.Objects;

public class AggregationKey {
    private FieldValue[] fieldValues;
    private FieldDescriptor[] fieldDescriptors;

    public AggregationKey(FieldDescriptor[] fieldDescriptors, FieldValue[] fieldValues) {
        this.fieldDescriptors = fieldDescriptors;
        this.fieldValues = fieldValues;
    }

    public FieldValue valueOf(String name) {
        for (int i = 0; i < fieldDescriptors.length; i++)
            if (fieldDescriptors[i].name().equals(name))
                return fieldValues[i];
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldValues);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            AggregationKey aggregationKey = (AggregationKey) obj;
            boolean result = true;
            for (int i = 0; i < fieldValues.length; i++)
                result &= fieldValues[i].equals(aggregationKey.fieldValues[i]);
            return result;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer aggKeyStr = new StringBuffer("AggregationKey(");
        for (int i = 0; i < fieldValues.length; i++)
            if (i + 1 == fieldValues.length)
                aggKeyStr.append(fieldValues[i] + ")");
            else
                aggKeyStr.append(fieldValues[i] + ", ");

        return aggKeyStr.toString();
    }
}

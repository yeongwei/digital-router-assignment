package com.digitalroute.common.field;

import java.util.Objects;

public class FieldValue {
    private FieldDescriptor fieldDescriptor;
    private Object value;

    public FieldValue(FieldDescriptor fieldDescriptor, Object value) {
        this.fieldDescriptor = fieldDescriptor;
        this.value = set(value);
    }

    public FieldDescriptor getFieldDescriptor() {
        return fieldDescriptor;
    }

    // TODO: In reality there should be some Boxed Types to handle this mess
    private Object set(Object value) {
        switch (fieldDescriptor.typeCode()) {
            case STRING:
                return String.valueOf(value);
            case INT:
                if (value instanceof String)
                    return Integer.valueOf((String) value);
                else
                    return (Integer) value;
            case BYTE:
                if (value instanceof String)
                    return Byte.valueOf((String) value);
                else
                    return (Byte) value;
            case BOOLEAN:
                return (Boolean) value;
            case LONG:
                return (Long) value;
            default:
                return value;
        }
    }

    // TODO: Although setter might have done the dirty job but do checking as well ???
    public Object value() { return value; }

    // TODO: Although castings are all consolidate here, need revision
    public String valueString() { return (String) value; }

    public int valueInt() { return (int) value; }

    public byte valueByte() { return (byte) value; }

    public long valueLong() { return (long) value; }

    public boolean larger(Object that) {
        switch (fieldDescriptor.typeCode()) {
            case INT: return valueInt() > (int) that;
            case LONG: return valueLong() > (long) that;
            default: return false;
        }
    }

    public Object add(Object that) {
        switch (fieldDescriptor.typeCode()) {
            case INT: return valueInt() + (int) that;
            case LONG: return valueLong() + (long) that;
            default: return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldDescriptor, value);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            FieldValue fv = (FieldValue) obj;
            return fieldDescriptor.equals(fv.fieldDescriptor) && value.equals(fv.value);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer fieldValueStr = new StringBuffer()
                .append(fieldDescriptor.name() + ": " + value);
        return fieldValueStr.toString();
    }
}

package com.digitalroute.common.field;

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
        switch (fieldDescriptor.getTypeCode()) {
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

    // TODO: Although setter might have done the dirty job but do checking as well
    public Object get() {
        switch (fieldDescriptor.getTypeCode()) {
            case STRING:
                return String.valueOf(value);
            case INT:
                return (Integer) value;
            case BYTE:
                return (Byte) value;
            case BOOLEAN:
                return (Boolean) value;
            case LONG:
                return (Long) value;
            default:
                return value;
        }
    }
}

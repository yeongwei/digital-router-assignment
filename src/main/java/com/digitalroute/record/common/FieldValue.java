package com.digitalroute.record.common;

public class FieldValue {
    private Field field;
    private Object value;

    public FieldValue(Field field, Object value) {
        this.field = field;
        this.value = set(value);
    }

    public Field getField() {
        return field;
    }

    // TODO: In reality there should be some Boxed Types to handle this mess
    private Object set(Object value) {
        switch (field.getTypeCode()) {
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

    /*
        Setter should have handled the type accordingly
     */
    public Object get() {
        switch (field.getTypeCode()) {
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

package com.digitalroute.record.common;

public abstract class RecordSchema {
    private Field[] fields; // Positioning of the fields is important

    public RecordSchema(Field[] fields) {
        this.fields = fields;
    }

    public Field getField(int index) {
        return fields[index];
    }

    public int getFieldLength() {
        return fields.length;
    }
}

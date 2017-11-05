package com.digitalroute.record.common;

public abstract class Record {
    private RecordSchema recordSchema;
    private FieldValue[] fieldValues;

    public Record(RecordSchema recordSchema) {
        this.recordSchema = recordSchema;
        this.fieldValues = new FieldValue[recordSchema.getFieldLength()];
    }

    /**
     * I
     * @param index starts from 0
     * @param value
     */
    public void set(int index, Object value) {
        fieldValues[index] = new FieldValue(recordSchema.getField(index), value);
    }

    /**
     *
     * @param index starts from 0
     * @return
     */
    public Object get(int index) {
        return fieldValues[index].get();
    }
}

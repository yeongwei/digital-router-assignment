package com.digitalroute.record.common;

public abstract class Record {
    private RecordSchema recordSchema;
    private FieldValue[] fieldValues;

    public Record(RecordSchema recordSchema) {
        this.recordSchema = recordSchema;
        this.fieldValues = new FieldValue[recordSchema.getFieldLength()];
    }

    public RecordSchema recordSchema() {
        return recordSchema;
    }

    /**
     * I
     *
     * @param index starts from 0
     * @param value
     */
    public void set(int index, Object value) {
        fieldValues[index] = new FieldValue(recordSchema.getField(index), value);
    }

    /**
     * @param index starts from 0
     * @return
     */
    public Object get(int index) {
        return fieldValues[index].get();
    }

    public Object get(String name) {
        return fieldValues[recordSchema.getIndexOf(name)].get();
    }

    @Override
    public String toString() {
        StringBuffer recordString = new StringBuffer(getClass().getSimpleName() + "(");
        for (int i = 0; i < recordSchema().getFieldLength(); i++)
            if (i + 1 == recordSchema().getFieldLength())
                recordString.append(get(i).toString());
            else
                recordString.append(get(i).toString() + ", ");
        recordString.append(")");
        return recordString.toString();
    }
}

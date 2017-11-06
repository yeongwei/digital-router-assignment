package com.digitalroute.common.record;

import com.digitalroute.common.field.FieldValue;

public abstract class Record {
    private RecordSchema recordSchema;
    private FieldValue[] fieldValues;

    public Record(RecordSchema recordSchema) {
        this.recordSchema = recordSchema;
        this.fieldValues = new FieldValue[recordSchema.fieldDescriptorsLength()];
    }

    public RecordSchema recordSchema() {
        return recordSchema;
    }

    /**
     * Set record value with index
     * @param index starts from 0
     * @param value
     */
    public void set(int index, Object value) {
        fieldValues[index] = new FieldValue(recordSchema.fieldDescriptor(index), value);
    }

    /**
     * Set record value with name
     * @param name
     * @param value
     */
    public void set(String name, Object value) {
        int index = recordSchema.indexOf(name);
        set(index, value);
    }

    /**
     * Get record value with index
     * @param index starts from 0
     * @return
     */
    public Object get(int index) {
        return fieldValues[index].get();
    }

    /**
     * Get record value with name
     * @param name
     * @return
     */
    public Object get(String name) {
        return get(recordSchema.indexOf(name));
    }

    @Override
    public String toString() {
        StringBuffer recordString = new StringBuffer(getClass().getSimpleName() + "(");
        for (int i = 0; i < recordSchema().fieldDescriptorsLength(); i++)
            if (i + 1 == recordSchema().fieldDescriptorsLength())
                recordString.append(get(i).toString());
            else
                recordString.append(get(i).toString() + ", ");
        recordString.append(")");
        return recordString.toString();
    }
}

package com.digitalroute.common.record;

import com.digitalroute.common.field.FieldDescriptor;

public abstract class RecordSchema {
    private FieldDescriptor[] fieldDescriptors; // Positioning of the fieldDescriptors is important

    public RecordSchema(FieldDescriptor[] fieldDescriptors) {
        this.fieldDescriptors = fieldDescriptors;
    }

    public FieldDescriptor fieldDescriptor(int index) {
        return fieldDescriptors[index];
    }

    public int fieldDescriptorsLength() {
        return fieldDescriptors.length;
    }

    public FieldDescriptor[] fieldDescriptors() {
        return fieldDescriptors;
    }

    /**
     * Get the field index with name.
     *
     * @param name
     * @return if found then > 0 else -1
     */
    public int indexOf(String name) {
        for (int i = 0; i < fieldDescriptorsLength(); i++) {
            if (fieldDescriptor(i).name().equals(name))
                return i;
        }
        return -1;
    }
}

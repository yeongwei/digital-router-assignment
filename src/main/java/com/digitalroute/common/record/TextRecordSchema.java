package com.digitalroute.common.record;

import com.digitalroute.common.field.FieldDescriptor;

/**
 * Schema to describe input text record
 */
public abstract class TextRecordSchema extends RecordSchema {
    private String delimiter;
    private String newLine;
    private boolean hasHeader;
    private int numOfFields;

    public TextRecordSchema(String delimiter, String newLine, boolean hasHeader, FieldDescriptor[] fieldDescriptors) {
        super(fieldDescriptors);

        this.delimiter = delimiter;
        this.newLine = newLine;
        this.hasHeader = hasHeader;
        this.numOfFields = fieldDescriptors.length;
    }

    public abstract Record onRead(String line);

    protected String delimiter() {
        return delimiter;
    }

    protected String newLine() {
        return newLine;
    }

    protected boolean hasHeader() {
        return hasHeader;
    }

    protected int numOfFields() {
        return numOfFields;
    }
}

package com.digitalroute.record.common;

public abstract class TextRecordSchema extends RecordSchema {
    private String delimiter;
    private String newLine;
    private boolean hasHeader;
    private int numOfFields;

    public TextRecordSchema(String delimiter, String newLine, boolean hasHeader, Field[] fields) {
        super(fields);

        this.delimiter = delimiter;
        this.newLine = newLine;
        this.hasHeader = hasHeader;
        this.numOfFields = fields.length;
    }

    public abstract Record onRead(String line);

    protected String getDelimiter() {
        return delimiter;
    }

    protected String getNewLine() {
        return newLine;
    }

    protected boolean hasHeader() {
        return hasHeader;
    }

    protected int getNumOfFields() {
        return numOfFields;
    }
}

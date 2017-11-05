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

    public String getDelimiter() {
        return delimiter;
    }

    public String getNewLine() {
        return newLine;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public int getNumOfFields() {
        return numOfFields;
    }
}

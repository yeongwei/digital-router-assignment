package com.digitalroute.input.common;

import com.digitalroute.record.common.Record;
import com.digitalroute.record.common.TextRecordSchema;

public abstract class TextInputSchema {
    private TextRecordSchema textRecordSchema;

    public TextInputSchema(TextRecordSchema textRecordSchema) {
        this.textRecordSchema = textRecordSchema;
    }

    public Record onRead(String line) {
        String[] values = line.split(textRecordSchema.getDelimiter());
        if (values.length == textRecordSchema.getFieldLength())
            return transform(values);
        else
            return null;
    }

    public abstract Record transform(String[] values);
}

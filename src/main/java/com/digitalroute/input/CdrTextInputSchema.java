package com.digitalroute.input;

import com.digitalroute.input.common.TextInputSchema;
import com.digitalroute.record.cdr.CdrRecord;
import com.digitalroute.record.cdr.CdrRecordSchema;
import com.digitalroute.record.common.Record;

final public class CdrTextInputSchema extends TextInputSchema {
    public CdrTextInputSchema() {
        super(new CdrRecordSchema());
    }

    @Override
    public Record transform(String[] values) {
        CdrRecord cdr = new CdrRecord();
        for (int i = 0; i < values.length; i ++)
            cdr.set(i, values[i]);
        return cdr;
    }
}

package com.digitalroute.input.schema;

import com.digitalroute.input.schema.TextInputSchema;
import com.digitalroute.record.cdr.CdrRecordSchema;

final public class CdrTextInputSchema extends TextInputSchema {
    public CdrTextInputSchema() {
        super(new CdrRecordSchema());
    }
}

package com.digitalroute.input;

import com.digitalroute.input.common.TextInputSchema;
import com.digitalroute.record.cdr.CdrRecordSchema;

final public class CdrTextInputSchema extends TextInputSchema {
    public CdrTextInputSchema() {
        super(new CdrRecordSchema());
    }
}

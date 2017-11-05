package com.digitalroute.record.cdr;

import com.digitalroute.record.common.Field;

import static com.digitalroute.record.common.FieldType.*;

import com.digitalroute.record.common.TextRecordSchema;

final public class CdrRecordSchema extends TextRecordSchema {
    public CdrRecordSchema() {
        super(":|.", "\n", false, new Field[]{
                new Field("callId", STRING),
                new Field("seqNum", INT),
                new Field("aNum", STRING),
                new Field("bNum", STRING),
                new Field("causeForOutput", BYTE),
                new Field("duration", INT),
        });
    }
}
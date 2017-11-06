package com.digitalroute.record.cdr;

import com.digitalroute.common.field.FieldDescriptor;

import static com.digitalroute.common.field.FieldType.*;

import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.TextRecordSchema;

final public class CdrRecordSchema extends TextRecordSchema {
    public CdrRecordSchema() {
        super(":|,", "\n", false, new FieldDescriptor[]{
                new FieldDescriptor("callId", STRING),
                new FieldDescriptor("seqNum", INT),
                new FieldDescriptor("aNum", STRING),
                new FieldDescriptor("bNum", STRING),
                new FieldDescriptor("causeForOutput", BYTE),
                new FieldDescriptor("duration", INT),
        });
    }

    @Override
    public Record onRead(String line) {
        String[] values = line.split(super.delimiter());
        if (values.length == super.fieldDescriptorsLength()) {
            CdrRecord cdr = new CdrRecord();
            for (int i = 0; i < values.length; i ++)
                cdr.set(i, values[i]);
            return cdr;
        } else
            return null; // TODO: Need type to avoid NPE
    }
}

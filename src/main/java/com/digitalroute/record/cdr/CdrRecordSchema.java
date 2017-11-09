package com.digitalroute.record.cdr;

import com.digitalroute.common.field.FieldDescriptor;

import static com.digitalroute.common.field.FieldType.*;

import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.TextRecordSchema;

/**
 * Record Schema for CDR
 */
final public class CdrRecordSchema extends TextRecordSchema {

    public static FieldDescriptor callId = new FieldDescriptor("callId", STRING);
    public static FieldDescriptor seqNum = new FieldDescriptor("seqNum", INT);
    public static FieldDescriptor aNum = new FieldDescriptor("aNum", STRING);
    public static FieldDescriptor bNum = new FieldDescriptor("bNum", STRING);
    public static FieldDescriptor causeForOutput = new FieldDescriptor("causeForOutput", BYTE);
    public static FieldDescriptor duration = new FieldDescriptor("duration", INT);

    public CdrRecordSchema() {
        super(":|,", "\n", false,
                new FieldDescriptor[]{callId, seqNum, aNum, bNum, causeForOutput, duration,});
    }

    @Override
    public Record onRead(String line) {
        String[] values = line.split(super.delimiter());
        if (values.length == super.fieldDescriptorsLength()) {
            CdrRecord cdr = new CdrRecord();
            for (int i = 0; i < values.length; i++)
                cdr.set(i, values[i]);
            return cdr;
        } else
            return null; // TODO: Need type to avoid NPE
    }
}

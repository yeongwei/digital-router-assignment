package com.digitalroute.record.common;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.field.FieldValue;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldDescriptorTestSpec {
    @Test
    public void testString() {
        FieldDescriptor f = new FieldDescriptor("name", FieldType.STRING);
        assertTrue(f.name() == "name");

        FieldValue fv = new FieldValue(f, "digitalrouter");
        assertTrue(fv.get() == "digitalrouter");
        assertTrue(fv.get().equals("digitalrouter"));
    }

    @Test
    public void testInt() {
        FieldDescriptor f = new FieldDescriptor("counter", FieldType.INT);
        FieldValue fv = new FieldValue(f, 100);
        assertTrue(fv.get() == Integer.valueOf(100));
        assertTrue(fv.get().equals(100));
    }

    @Test
    public void testByte() {
        FieldDescriptor f = new FieldDescriptor("counter", FieldType.BYTE);
        FieldValue fv = new FieldValue(f, (byte) 127);
        assertTrue(fv.get() == Byte.valueOf("127"));
        assertTrue(fv.get().equals((byte) 127));
    }

    @Test
    public void testLong() {
        FieldDescriptor f = new FieldDescriptor("counter", FieldType.LONG);
        FieldValue fv = new FieldValue(f, 127L);
        assertTrue(fv.get() == Long.valueOf("127"));
        assertTrue(fv.get().equals((long) 127));
    }

    @Test
    public void testTypeCode() {
        FieldType ft = FieldType.STRING;
        assertTrue(ft.name() == "STRING");
        assertTrue(ft.ordinal() == 0);
    }

    @Test
    public void testEquality() {
        FieldDescriptor f1 = new FieldDescriptor("field1", FieldType.STRING);
        FieldDescriptor f2 = new FieldDescriptor("field1", FieldType.STRING);
        assertFalse(f1 == f2);
        assertTrue(f1.equals(f2));
    }
}

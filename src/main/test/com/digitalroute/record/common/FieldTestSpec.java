package com.digitalroute.record.common;

import org.junit.*;

import static org.junit.Assert.assertTrue;

public class FieldTestSpec {
    @Test
    public void testString() {
        Field f = new Field("name", FieldType.STRING);
        assertTrue(f.getName() == "name");

        FieldValue fv = new FieldValue(f, "digitalrouter");
        assertTrue(fv.get() == "digitalrouter");
        assertTrue(fv.get().equals("digitalrouter"));
    }

    @Test
    public void testInt() {
        Field f = new Field("counter", FieldType.INT);
        FieldValue fv = new FieldValue(f, 100);
        assertTrue(fv.get() == Integer.valueOf(100));
        assertTrue(fv.get().equals(100));
    }

    @Test
    public void testByte() {
        Field f = new Field("counter", FieldType.BYTE);
        FieldValue fv = new FieldValue(f, (byte) 127);
        assertTrue(fv.get() == Byte.valueOf("127"));
        assertTrue(fv.get().equals((byte) 127));
    }

    @Test
    public void testLong() {
        Field f = new Field("counter", FieldType.LONG);
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
}

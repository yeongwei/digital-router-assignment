package com.digitalroute.record.common;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.field.FieldValue;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class FieldValueTestSpec {
    @Test
    public void testEquality() {
        FieldDescriptor fd = new FieldDescriptor("counter", FieldType.STRING);
        FieldValue fv1 = new FieldValue(fd, "ABC");
        FieldValue fv2 = new FieldValue(fd, "ABC");
        assertTrue(fv1 != fv2);
        assertTrue(fv1.equals(fv2));
    }
}

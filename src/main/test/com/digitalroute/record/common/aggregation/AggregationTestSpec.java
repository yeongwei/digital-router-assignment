package com.digitalroute.record.common.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.field.FieldValue;

import com.digitalroute.common.record.aggregation.AggregationKey;
import com.digitalroute.common.record.aggregation.AggregationSummary;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AggregationTestSpec {
    @Test
    public void testEquality() {
        FieldDescriptor fd = new FieldDescriptor("counter", FieldType.STRING);
        FieldValue fv = new FieldValue(fd, "ABC");
        AggregationKey aggKey1 = new AggregationKey(new FieldDescriptor[]{ fd }, new FieldValue[]{ fv });
        AggregationKey aggKey2 = new AggregationKey(new FieldDescriptor[]{ fd }, new FieldValue[]{ fv });
        assertTrue(aggKey1.equals(aggKey2));

        HashMap<AggregationKey, String> m = new HashMap<AggregationKey, String>();
        m.put(aggKey1, "A");
        assertTrue(m.containsKey(aggKey1));
        assertTrue(aggKey2.valueOf("counter").equals("ABC"));
    }

    @Test
    public void testString() {
        FieldDescriptor fd = new FieldDescriptor("counter", FieldType.STRING);
        FieldValue fv = new FieldValue(fd, "ABC");
        AggregationKey aggKey1 = new AggregationKey(new FieldDescriptor[]{ fd }, new FieldValue[]{ fv });
        assertTrue(aggKey1.toString().equals("AggregationKey(counter: ABC)"));
    }
}

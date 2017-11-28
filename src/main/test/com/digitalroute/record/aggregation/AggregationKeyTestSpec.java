package com.digitalroute.record.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldType;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.aggregation.AggregationKey;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AggregationKeyTestSpec {
    @Test
    public void test() {
        FieldDescriptor fd = new FieldDescriptor("name", FieldType.STRING);
        FieldValue fv = new FieldValue(fd, "digitalrouter");
        AggregationKey aggregationKey = new AggregationKey(new FieldDescriptor[]{ fd }, new FieldValue[]{ fv });
        FieldValue fv2 = aggregationKey.valueOf("name");
        assertTrue(fv2.valueString() == "digitalrouter");
    }
}

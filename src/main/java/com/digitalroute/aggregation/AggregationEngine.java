package com.digitalroute.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.aggregation.AggregationKey;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.common.record.aggregation.AggregationSpec;

import java.util.HashMap;

public abstract class AggregationEngine {
    private AggregationSpec aggregationSpec;
    private HashMap<AggregationKey, AggregationRecord> aggregations = new HashMap();

    public AggregationEngine(AggregationSpec aggregationSpec) {
        this.aggregationSpec = aggregationSpec;
    }

    public boolean put(Record record) {
        AggregationKey aggregationKey = createKey(record);
        AggregationRecord aggregationRecord = aggregations.getOrDefault(aggregationKey, new AggregationRecord(aggregationSpec.formulas()));
        aggregationRecord.put(record);
        aggregations.put(aggregationKey, aggregationRecord);
        return true;
    }

    public AggregationRecord get(Record record) {
        AggregationKey aggregationKey = createKey(record);
        return aggregations.getOrDefault(aggregationKey, new AggregationRecord(aggregationSpec.formulas()));
    }

    private AggregationKey createKey(Record record) {
        FieldDescriptor[] keys = aggregationSpec.keys();
        FieldValue[] keyValues = new FieldValue[keys.length];
        for (int i = 0; i < keys.length; i++)
            keyValues[i] = record.get(keys[i].name());
        return new AggregationKey(keyValues);
    }
}

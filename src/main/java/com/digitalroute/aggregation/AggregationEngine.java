package com.digitalroute.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.aggregation.AggregationFormula;
import com.digitalroute.common.record.aggregation.AggregationKey;
import com.digitalroute.common.record.aggregation.AggregationRecord;
import com.digitalroute.common.record.aggregation.AggregationSpec;

import java.util.HashMap;

public abstract class AggregationEngine {
    private AggregationSpec aggregationSpec;
    private FieldDescriptor[] keys;
    private AggregationFormula[] formulas;
    private HashMap<AggregationKey, AggregationRecord> aggregations = new HashMap();

    public AggregationEngine(AggregationSpec aggregationSpec) {
        this.aggregationSpec = aggregationSpec;
        this.keys = aggregationSpec.keys();
        this.formulas = aggregationSpec.formulas();
    }

    public AggregationKey put(Record record) {
        AggregationKey aggregationKey = aggregationKey(record);
        AggregationRecord aggregationRecord = aggregations.getOrDefault(aggregationKey, new AggregationRecord(aggregationSpec.formulas()));
        aggregationRecord.put(record);
        aggregations.put(aggregationKey, aggregationRecord);
        return aggregationKey;
    }

    protected boolean put(AggregationKey aggregationKey, Record record) {
        return aggregations.get(aggregationKey).put(record);
    }

    /**
     * Get and remove
     * @param record
     * @return
     */
    public AggregationRecord getAndRemove(Record record) {
        AggregationKey aggregationKey = aggregationKey(record);
        if (aggregations.containsKey(aggregationKey)) {
            AggregationRecord aggregationRecord = aggregations.get(aggregationKey);
            aggregations.remove(aggregationKey);
            return aggregationRecord;
        }
        else return null;
    }

    private AggregationKey aggregationKey(Record record) {
        FieldValue[] keyValues = new FieldValue[keys.length];
        for (int i = 0; i < keys.length; i++)
            keyValues[i] = record.get(keys[i].name());
        return new AggregationKey(keyValues);
    }
}

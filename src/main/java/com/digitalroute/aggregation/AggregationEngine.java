package com.digitalroute.aggregation;

import com.digitalroute.common.field.FieldDescriptor;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.Record;
import com.digitalroute.common.record.aggregation.*;

import java.util.HashMap;

public abstract class AggregationEngine {
    private AggregationSpec aggregationSpec;
    private FieldDescriptor[] keys;
    private AggregationFormula[] formulas;
    private HashMap<AggregationKey, AggregationRecord> aggregations = new HashMap();
    private AggregationSummary aggregationSummary;

    protected AggregationEngine(AggregationSpec aggregationSpec) {
        this.aggregationSpec = aggregationSpec;
        this.keys = aggregationSpec.keys();
        this.formulas = aggregationSpec.formulas();
        this.aggregationSummary = new AggregationSummary(aggregationSpec.formulas());
    }

    /**
     * Add / update aggregation entry with record
     * @param record
     * @return
     */
    public AggregationKey put(Record record) {
        AggregationKey aggregationKey = aggregationKey(record);
        AggregationRecord aggregationRecord = aggregations.getOrDefault(aggregationKey,
                new AggregationRecord(aggregationSpec.formulas()));
        aggregationRecord.put(record);
        aggregations.put(aggregationKey, aggregationRecord);
        updateSummary(record);
        return aggregationKey;
    }

    /**
     * Update Record into existing aggregation entry
     * @param aggregationKey
     * @param record
     * @return
     */
    protected boolean put(AggregationKey aggregationKey, Record record) {
        boolean status = aggregations.get(aggregationKey).put(record);
        updateSummary(record);
        return status;
    }

    /**
     * Get Aggregation Record
     * @param record
     * @return
     */
    public AggregationRecord get(Record record) {
        AggregationKey aggregationKey = aggregationKey(record);
        if (aggregations.containsKey(aggregationKey)) {
            AggregationRecord aggregationRecord = aggregations.get(aggregationKey);
            return aggregationRecord;
        }
        else return null;
    }

    /**
     * Remove aggregation
     * @param record
     * @return
     */
    public AggregationRecord remove(Record record) {
        return aggregations.remove(aggregationKey(record));
    }

    /**
     * Retrieve all AggregationRecord
     * @return
     */
    public HashMap<AggregationKey, AggregationRecord>  all() {
        return aggregations;
    }

    /**
     * Get AggregationSummary
     * @return
     */
    public AggregationSummary summary() { return aggregationSummary; }

    /**
     * Make AggregationKey with Record
     * @param record
     * @return
     */
    private AggregationKey aggregationKey(Record record) {
        FieldValue[] keyValues = new FieldValue[keys.length];
        for (int i = 0; i < keys.length; i++)
            keyValues[i] = record.get(keys[i].name());
        return new AggregationKey(keys, keyValues);
    }

    /**
     * Update each record into AggregationSummary
     * @param record
     */
    private void updateSummary(Record record) { aggregationSummary.put(record); }
}

package com.digitalroute.aggregation.cdr;

import com.digitalroute.aggregation.AggregationEngine;
import com.digitalroute.common.field.FieldValue;
import com.digitalroute.common.record.aggregation.AggregationKey;
import com.digitalroute.record.cdr.CdrAggregationSpec;
import com.digitalroute.record.cdr.CdrRecord;

import java.util.HashMap;
import java.util.function.Function;


final public class CdrAggregationEngine extends AggregationEngine {
    private HashMap<CdrShortKey, AggregationKey> keyLookup = new HashMap();

    public CdrAggregationEngine() { super(new CdrAggregationSpec()); }

    /**
     * Add CdrRecord into aggregation
     * @param cdrRecord
     * @return
     */
    public boolean put(CdrRecord cdrRecord) {
        AggregationKey aggregationKey = super.put(cdrRecord);
        updateKeyLook(cdrShortKey(cdrRecord), aggregationKey);
        return true;
    }

    /**
     * Leach existing AggregationKey for ongoing calls
     * @param cdrRecord
     * @return
     */
    public boolean putExistFirst(CdrRecord cdrRecord) {
        AggregationKey aggregationKey = keyLookup.get(cdrShortKey(cdrRecord));
        if (aggregationKey == null) return false;
        else return super.put(aggregationKey, cdrRecord);
    }

    /**
     * Track first occurence of AggregationKey
     * @param cdrShortKey
     * @param aggregationKey
     * @return
     */
    private AggregationKey updateKeyLook(CdrShortKey cdrShortKey, AggregationKey aggregationKey) {
        return keyLookup.computeIfAbsent(cdrShortKey, new Function<CdrShortKey, AggregationKey>() {
            @Override
            public AggregationKey apply(CdrShortKey cdrShortKey) {
                return aggregationKey;
            }
        });
    }

    /**
     * Helper method to construct CdrShortKey with aNum and bNum
     * @param cdrRecord
     * @return
     */
    private CdrShortKey cdrShortKey(CdrRecord cdrRecord) {
        return new CdrShortKey(new FieldValue[]{ cdrRecord.get("aNum"), cdrRecord.get("bNum") });
    }
}

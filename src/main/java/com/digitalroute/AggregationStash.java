package com.digitalroute;

import com.digitalroute.record.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.logging.Logger;

public class AggregationStash {
    private Logger Log = Logger.getLogger(this.getClass().getName());
    /* Aggregation bucket*/
    private HashMap<AggregationKey, AggregationRecords> stash = new HashMap();
    /* Keep tracks of the first occurrence of AggregationKey */
    private HashMap<AggregationSubKey, AggregationKey> keyLookup = new HashMap();

    /**
     * Add CDR for On going or end call
     * @param cdr
     * @return
     */
    public boolean add(CallDataRecord cdr) {
        updateKeyLook(cdr);
        AggregationRecords aggregationRecords = stash.getOrDefault(cdr.aggregationKey(), new AggregationRecords());
        boolean result = aggregationRecords.add(cdr);
        int initialSize = stash.size();
        stash.put(cdr.aggregationKey(), aggregationRecords);
        if (stash.size() == initialSize) {
            // Log.fine("Updated aggregation for Aggreation Key: " + cdr.aggregationKey());
        } else {
            // Log.fine("Created aggregation for Aggreation Key: " + cdr.aggregationKey());
        }
        // Log.fine("Total aggregation objects: " + stash.size());
        return result;
    }

    /**
     * Add CDR for incomplete calls to first occurred AggregationKey
     * @param cdr
     * @return
     */
    public boolean leach(CallDataRecord cdr) {
        AggregationKey aggregationKey = keyLookup.get(cdr.aggregationSubKey());
        if (aggregationKey == null)
            return false;
        else
            return stash.get(aggregationKey).add(cdr);
    }

    public long getTotalDuration() {
        int totalDuration = 0;
        for (AggregationRecords aggregationRecords: stash.values())
            totalDuration += aggregationRecords.getTotalDuration();
        return totalDuration;
    }

    /**
     * Compute the AggregationResult for a specific AggregationKey
     * @param cdr
     * @return
     */
    public AggregationResult compute(CallDataRecord cdr) {
        AggregationRecords aggregationRecords = stash.get(cdr.aggregationKey());
        AggregationResult aggregationResult = new AggregationResult(cdr.getCallId(), aggregationRecords.getHighestSeqNum(),
                cdr.getaNum(), cdr.getbNum(), aggregationRecords.getLastCauseForOutput(), aggregationRecords.getTotalDuration());
        // Log.fine("Computed Aggregation Result " + aggregationResult);
        return aggregationResult;
    }

    public void remove(CallDataRecord cdr) {
        stash.remove(cdr.aggregationKey());
        keyLookup.remove(cdr.aggregationSubKey());
    }

    public boolean nonEmpty() {
        return stash.size() > 0;
    }

    public ArrayList<AggregationResult> getAll() {
        ArrayList<AggregationResult> all = new ArrayList();
        for (AggregationKey key : stash.keySet()) {
            AggregationRecords aggregationRecords = stash.get(key);
            all.add(new AggregationResult(key.getCallId(), aggregationRecords.getHighestSeqNum(),
                    key.getaNum(), key.getbNum(), aggregationRecords.getLastCauseForOutput(), aggregationRecords.getTotalDuration()));
        }
        return all;
    }

    public void reset() {
        stash.clear();
        keyLookup.clear();
    }

    /**
     * Store first occurrence of AggregationKey
     * @param cdr
     * @return
     */
    private AggregationKey updateKeyLook(CallDataRecord cdr) {
        return keyLookup.computeIfAbsent(cdr.aggregationSubKey(), new Function<AggregationSubKey, AggregationKey>() {
            @Override
            public AggregationKey apply(AggregationSubKey aggregationSubKey) {
                // Log.fine("Found new Aggregation Key " + cdr.aggregationKey());
                return cdr.aggregationKey();
            }
        });
    }
}

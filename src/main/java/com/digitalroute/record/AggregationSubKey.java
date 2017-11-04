package com.digitalroute.record;

import java.util.Objects;

public class AggregationSubKey {
    private String aNum;
    private String bNum;

    public AggregationSubKey(CallDataRecord cdr) {
        this.aNum = cdr.getaNum();
        this.bNum = cdr.getbNum();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            AggregationSubKey key = (AggregationSubKey) obj;
            return aNum.equals(key.getaNum()) && bNum.equals(key.getbNum());
        } catch (Exception e) {
            return  false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(aNum, bNum);
    }

    public String getaNum() {
        return aNum;
    }

    public String getbNum() {
        return bNum;
    }
}

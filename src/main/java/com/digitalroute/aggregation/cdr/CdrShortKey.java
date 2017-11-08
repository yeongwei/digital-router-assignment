package com.digitalroute.aggregation.cdr;

import com.digitalroute.common.field.FieldValue;

import java.util.Objects;

class CdrShortKey {
    FieldValue[] keyValues;

    CdrShortKey(FieldValue... keyValues) {
        this.keyValues = keyValues;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyValues);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            CdrShortKey shortKey = (CdrShortKey) obj;
            boolean result = true;
            for (int i = 0; i < keyValues.length; i++)
                result &= keyValues[i].equals(shortKey.keyValues[i]);
            return result;
        } catch (Exception e) {
            return false;
        }
    }
}

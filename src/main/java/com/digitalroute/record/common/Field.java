package com.digitalroute.record.common;

public class Field {

    private String name;
    private FieldType typeCode;

    public Field(String name, FieldType typeCode) {
        this.name = name;
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public FieldType getTypeCode() {
        return typeCode;
    }

    @Override
    public String toString() {
        return "Field(name: " + name + ", typeCode: " + typeCode.name() + " (" + typeCode.ordinal() + "))";
    }
}

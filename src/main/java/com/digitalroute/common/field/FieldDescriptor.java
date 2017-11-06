package com.digitalroute.common.field;

public class FieldDescriptor {

    private String name;
    private FieldType typeCode;

    public FieldDescriptor(String name, FieldType typeCode) {
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
        return "FieldDescriptor(name: " + name + ", typeCode: " + typeCode.name() + " (" + typeCode.ordinal() + "))";
    }
}

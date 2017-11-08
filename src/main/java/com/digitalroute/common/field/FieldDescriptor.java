package com.digitalroute.common.field;

import java.util.Objects;

public class FieldDescriptor {

    private String name;
    private FieldType typeCode;

    public FieldDescriptor(String name, FieldType typeCode) {
        this.name = name;
        this.typeCode = typeCode;
    }

    public String name() {
        return name;
    }

    public FieldType typeCode() {
        return typeCode;
    }

    @Override
    public String toString() {
        return "FieldDescriptor(name: " + name + ", typeCode: " + typeCode.name() + " (" + typeCode.ordinal() + "))";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, typeCode);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            FieldDescriptor f = (FieldDescriptor) obj;
            return name() == f.name() && typeCode() == f.typeCode();
        } catch (Exception e) {
            return false;
        }
    }
}

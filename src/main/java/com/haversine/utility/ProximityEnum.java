package com.haversine.utility;

public enum ProximityEnum {

    NEARBY("Nearby Suburbs"),
    FRINGE("Fringe Suburbs");

    private String value;

    ProximityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static ProximityEnum getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(ProximityEnum v : values())
            if(value.equalsIgnoreCase(v.getValue())) return v;
        throw new IllegalArgumentException();
    }
}

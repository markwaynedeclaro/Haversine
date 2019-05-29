package com.haversine.domain;

import java.io.Serializable;

/**
 * entity for the map key for Suburb object
 */
public class SuburbKey implements Serializable {

    private static final long serialVersionUID = 4L;
    private int Pcode;
    private String Locality;



    public SuburbKey(int Pcode, String Locality) {
        super();
        this.Pcode = Pcode;
        this.Locality = Locality;
    }


    public int getPcode() {
        return Pcode;
    }

    public void setPcode(int pcode) {
        Pcode = pcode;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SuburbKey) {
            SuburbKey sk = (SuburbKey) obj;
            return (sk.Locality.trim().equalsIgnoreCase(this.Locality.trim()) && sk.Pcode == this.Pcode);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        hashcode = Pcode*20;
        hashcode += Locality.hashCode();
        return hashcode;
    }

}


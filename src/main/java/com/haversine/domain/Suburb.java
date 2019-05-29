package com.haversine.domain;

import java.io.Serializable;

/**
 * The main entity that we'll be using
 * Field names are based from the field names of the input json file
 */
public class Suburb implements Serializable {

    private static final long serialVersionUID = 4L;

    private int Pcode;
    private String Locality;
    private String State;
    private String Comments;
    private String Category;
    private Double Longitude;
    private Double Latitude;
    private String proximityType;
    private double proximity;


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

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public String getProximityType() {
        return proximityType;
    }

    public void setProximityType(String proximityType) {
        this.proximityType = proximityType;
    }

    public double getProximity() {
        return proximity;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }
}

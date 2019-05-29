package com.haversine.service;

import com.haversine.domain.Suburb;
import com.haversine.domain.SuburbKey;

import java.util.List;
import java.util.Map;

/**
 * The main service class for Suburb functions
 */
public interface SuburbService {

    List<Suburb> getDataFromFile(String filename);

    Map<SuburbKey, Suburb> generateDataMap(List<Suburb> list);

    List<Suburb> getCloseSuburbs(Suburb suburb, List<Suburb> list);

}

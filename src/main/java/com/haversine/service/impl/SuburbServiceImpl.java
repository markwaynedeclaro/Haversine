package com.haversine.service.impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haversine.domain.Suburb;
import com.haversine.domain.SuburbKey;
import com.haversine.service.SuburbService;
import com.haversine.utility.GeneralUtility;
import com.haversine.utility.ProximityEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of service class for Suburb functions
 */
public class SuburbServiceImpl implements SuburbService {

    private GeneralUtility utility;

    public SuburbServiceImpl() {
        this.utility = new GeneralUtility();
    }

    @Override
    public List<Suburb> getDataFromFile(String filename) {
        List<Suburb> suburbList = new ArrayList<>();
        String jsonString = utility.readFileIntoString(utility.getFileFromResources(filename));

        try {
            Gson gson = new Gson();
            TypeToken<List<Suburb>> token = new TypeToken<List<Suburb>>() {};
            suburbList = gson.fromJson(jsonString, token.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return suburbList;
    }

    @Override
    public Map<SuburbKey, Suburb> generateDataMap(List<Suburb> list) {
        Map<SuburbKey, Suburb> map = new HashMap<>();
        for (Suburb suburb : list) {
            if (suburb.getLatitude() != null && suburb.getLongitude() != null) {
                map.put(new SuburbKey(suburb.getPcode(), suburb.getLocality().toUpperCase()), suburb);
            }
        }
        return map;
    }

    @Override
    public List<Suburb> getCloseSuburbs(Suburb chosenSuburb, List<Suburb> list) {

        List<Suburb> newList = new ArrayList<>();
        int nearbyCount = 0;
        int fringeCount = 0;
        int maxCount = 15;

        for (Suburb s : list) {
            double distance = this.utility.haversineDistance(chosenSuburb.getLatitude(), chosenSuburb.getLongitude(), s.getLatitude(), s.getLongitude());

            if (distance < 10.0) {
                if (nearbyCount < maxCount) {
                    s.setProximityType(ProximityEnum.NEARBY.toString());
                    s.setProximity(distance);
                    newList.add(s);
                    nearbyCount++;
                }
            } else if (distance < 50.0 && fringeCount < maxCount) {
                s.setProximityType(ProximityEnum.FRINGE.toString());
                s.setProximity(distance);
                newList.add(s);
                fringeCount++;
            }

            if (fringeCount == maxCount && nearbyCount == maxCount)
                break;
        }

        return newList;
    }


}

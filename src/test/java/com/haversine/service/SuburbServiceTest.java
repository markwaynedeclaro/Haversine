package com.haversine.service;

import com.haversine.domain.Suburb;
import com.haversine.domain.SuburbKey;
import com.haversine.service.impl.SuburbServiceImpl;
import com.haversine.utility.ProximityEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class SuburbServiceTest {

    private SuburbServiceImpl suburbService;
    private String fileName;
    private String testLocality;
    private int testPostCode;

    @Before
    public void setUp() {
        suburbService = new SuburbServiceImpl();
        fileName = "aus_suburbs.json";
        testLocality = "homebush";
        testPostCode = 2140;
    }


    @Test
    public void testGetDataFromFile_size() {
        List<Suburb> suburbList =  suburbService.getDataFromFile(fileName);
        assertEquals(suburbList.size(), 16544);
    }


    @Test
    public void testGenerateDataMap() {
        Map<SuburbKey, Suburb> suburbMap =  suburbService.generateDataMap( suburbService.getDataFromFile(fileName));
        assertEquals(suburbMap.size(), 15428);
        Suburb chosenSuburb = suburbMap.get(new SuburbKey(2140, testLocality));
        assertNull(chosenSuburb);
        chosenSuburb = suburbMap.get(new SuburbKey(testPostCode, testLocality.toUpperCase()));
        assertNotNull(chosenSuburb);
        assertEquals(chosenSuburb.getState(), "NSW");
        chosenSuburb = suburbMap.get(new SuburbKey(2140, "akjsgdaskdgksagdjjg"));
        assertNull(chosenSuburb);
    }


    @Test
    public void testGetCloseSuburbs() {
        Map<SuburbKey, Suburb> suburbMap =  suburbService.generateDataMap( suburbService.getDataFromFile(fileName));
        Suburb chosenSuburb = suburbMap.get(new SuburbKey(testPostCode, testLocality.toUpperCase()));
        List<Suburb> suburbListValid =  new ArrayList<>(suburbMap.values());
        List<Suburb> closeSuburbs = suburbService.getCloseSuburbs(chosenSuburb, suburbListValid);
        List<Suburb> nearbySuburbs = closeSuburbs.stream().
                                        filter(s->s.getProximityType().equals(ProximityEnum.NEARBY.toString()))
                                        .collect(Collectors.toList());
        assertEquals(nearbySuburbs.size(), 15);
        List<Suburb> fringeSuburbs = closeSuburbs.stream().
                filter(s->s.getProximityType().equals(ProximityEnum.FRINGE.toString()))
                .collect(Collectors.toList());
        assertEquals(fringeSuburbs.size(), 15);
    }

}

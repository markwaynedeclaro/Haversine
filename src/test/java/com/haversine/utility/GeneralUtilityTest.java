package com.haversine.utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GeneralUtilityTest {

    private GeneralUtility utility;
    private String fileName;
    private double latitude_1;
    private double latitude_2;
    private double longitude_1;
    private double longitude_2;
    private DecimalFormat decimalFormat;

    @Before
    public void setUp() {
        utility = new GeneralUtility();
        fileName = "aus_suburbs.json";
        latitude_1 = 10.0;
        longitude_1 = -10.0;
        latitude_2 = 50.0;
        longitude_2 = 80.0;
        decimalFormat = new DecimalFormat("#");
    }

    @Test
    public void testGetFileFromResources() {
        File inputFile = this.utility.getFileFromResources(fileName);
        assertEquals(inputFile.getName(), fileName);
    }

    @Test
    public void testReadFileIntoString() {
        String inputFileContent = this.utility.readFileIntoString(this.utility.getFileFromResources("application.properties"));
        assertEquals(inputFileContent, "Test Data");
    }

    @Test
    public void testHaversineDistance() {
        double distance = this.utility.haversineDistance(latitude_1, longitude_1, latitude_2, longitude_2);
        System.out.println(distance);
        assertTrue(true);
        assertEquals(decimalFormat.format(9157.55228626), decimalFormat.format(distance) );
    }


    @After
    public void tearDown() {

    }
}

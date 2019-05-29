package com.haversine.utility;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;



/**
 * General Utility class
 */
public class GeneralUtility
{

    private final double earthRadius = 6371;

    // get file from classpath, resources folder
    public File getFileFromResources(String fileName)
    {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }


    public String readFileIntoString(File file)
    {
        String out = "";

        try {
            if (file == null)
                return "";
            out = FileUtils.readFileToString(file, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }



    public double haversineDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        double latitudeDiff = Math.toRadians(latitude2 - latitude1);
        double longitudeDiff = Math.toRadians(longitude2 - longitude1);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(latitudeDiff / 2),2) + Math.pow(Math.sin(longitudeDiff / 2),2) * Math.cos(latitude1) * Math.cos(latitude2);
        return 2 * Math.asin(Math.sqrt(a)) * earthRadius;
    }


}

package com.jukusoft.opendmx.commons.utils;

/**
 * this class contains the paths to specific dirs.
 *
 * @author Justin Kuenzel
 */
public class Dirs {

    /**
     * the path to the data directory.
     */
    public static String DATA = "./data/";

    /**
     * the key for the system property to override the data directory.
     */
    private static final String DIR_PROPERTY_KEY = "dataDir";

    static {
        if (System.getProperty(DIR_PROPERTY_KEY) != null) {
            DATA = System.getProperty(DIR_PROPERTY_KEY);
        }
    }

}

package com.abhi.fabkutbusiness.app;

/**
 * Created by abhi on 31/03/17.
 */

public class AppConfigFile {


    // Theme color
    public static final String themeColor = "#132465";

    // 1 for one way
    // 2 for two way
    // 3 for round way
    public static final int tripType = 1;

    // 1 for From one city to another only
    // 2 for From one place to another within a city only
    // 3 for rBoth
    public static final int tripScope = 1;

    // 1 for manually input
    // 2 for auto pick current location
    // 3 for google search api
    public static final int[] tripPickupLocation = {1};


}

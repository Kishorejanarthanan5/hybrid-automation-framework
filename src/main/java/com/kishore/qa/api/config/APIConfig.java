package com.kishore.qa.api.config;

import com.kishore.qa.constants.FrameworkConstants;
import com.kishore.qa.utils.ConfigReader;
import io.restassured.RestAssured;

public final class APIConfig {

    private static boolean isInitialized = false;

    private APIConfig() {
    }

    public static void setup() {
        if (!isInitialized) {
            RestAssured.baseURI = ConfigReader.getProperty(FrameworkConstants.API_BASE_URL_KEY);
            isInitialized = true;
        }
    }
}
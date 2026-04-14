package com.kishore.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();
    private static final String CONFIG_PATH = "/src/main/resources/config.properties";

    // Private constructor to prevent instantiation
    private ConfigReader() {
        // intentionally empty
    }

    // Static block - executes once when class is loaded
    static {
        String userDir = System.getProperty("user.dir");
        try (FileInputStream fis = new FileInputStream(userDir + CONFIG_PATH)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties from: " + userDir, e);
        }
    }

    public static String getProperty(String key) {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }
        return value;
    }

}
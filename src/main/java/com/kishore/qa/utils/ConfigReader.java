package com.kishore.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.kishore.qa.constants.FrameworkConstants;

public class ConfigReader {

    private static final Properties prop = new Properties();

    // Private constructor to prevent instantiation
    private ConfigReader() {}

    // Static block - executes once when class is loaded
    static {
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_PATH)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config file from path: "
                            + FrameworkConstants.CONFIG_PATH, e);
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
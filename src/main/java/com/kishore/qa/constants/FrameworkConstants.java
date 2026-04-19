package com.kishore.qa.constants;

public final class FrameworkConstants {

    // Private constructor to prevent instantiation
    private FrameworkConstants() {}

    // ===== File Paths =====
    public static final String CONFIG_PATH =
            System.getProperty("user.dir") + "/src/main/resources/config.properties";

    // ===== Config Keys =====
    public static final String BROWSER_KEY = "browser";
    public static final String URL_KEY = "url";
    public static final String IMPLICIT_WAIT_KEY = "implicit.wait";
    public static final String EXPLICIT_WAIT_KEY = "explicit.wait";

    // ===== Default Values =====
    public static final int DEFAULT_EXPLICIT_WAIT = 10;
}
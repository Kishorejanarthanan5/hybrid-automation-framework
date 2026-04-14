package com.kishore.qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // ThreadLocal ensures each thread gets its own WebDriver instance for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize WebDriver based on the given browser name
    public static void initDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions(); // Configure Chrome startup settings
                options.addArguments("--start-maximized"); // Launch browser in maximized mode
                options.addArguments("--remote-allow-origins=*"); // Handle cross-origin issues in newer Chrome versions
                options.addArguments("--disable-notifications"); // Disable browser notifications
                driver.set(new ChromeDriver(options)); // Create and store ChromeDriver for current thread
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver()); // Create and store FirefoxDriver for current thread
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver()); // Create and store EdgeDriver for current thread
                break;

            default:
                throw new IllegalArgumentException("Browser name: " + browserName + " Not supported. Please use Chrome, Edge or Firefox");
        }
    }

    // Get WebDriver instance for the current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit browser and remove WebDriver instance from ThreadLocal
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit(); // Close browser session
            driver.remove(); // Clean up ThreadLocal to avoid memory leaks
        }
    }

}
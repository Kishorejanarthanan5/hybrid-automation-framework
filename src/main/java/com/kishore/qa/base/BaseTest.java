package com.kishore.qa.base;

import com.kishore.qa.driver.DriverFactory;
import com.kishore.qa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");
        String implicitWait = ConfigReader.getProperty("implicit.wait");
        int waitTime = Integer.parseInt(implicitWait);

        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}

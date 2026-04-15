package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.driver.DriverFactory;
import com.kishore.qa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin(){
        // Step 1: Get the driver (created by setUp automatically)
        WebDriver driver = DriverFactory.getDriver();

        // Step 2: Click "Signup/Login" link on home page
        driver.findElement(By.cssSelector("a[href='/login']")).click();
        // Step 3: Enter email
        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(ConfigReader.getProperty("valid.email"));
        // Step 4: Enter password
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys(ConfigReader.getProperty("valid.password"));
        // Step 5: Click login button
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();
        // Step 6: Assert logout link is visible
        WebElement logoutLink = driver.findElement(By.cssSelector("a[href='/logout']"));
        Assert.assertTrue(logoutLink.isDisplayed(), "Login failed - logout link not found");
    }
}

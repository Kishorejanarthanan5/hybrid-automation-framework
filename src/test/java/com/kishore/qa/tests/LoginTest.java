package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.driver.DriverFactory;
import com.kishore.qa.pages.HomePage;
import com.kishore.qa.pages.LoginPage;
import com.kishore.qa.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @Test
    public void testValidLogin(){
        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickSignupLogin();
        loginPage.enterEmail(ConfigReader.getProperty("valid.email"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();

        Assert.assertTrue(homePage.isLogoutLinkDisplayed(), "Login failed");
    }
}

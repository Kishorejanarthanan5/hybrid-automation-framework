package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.pages.HomePage;
import com.kishore.qa.pages.LoginPage;
import com.kishore.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickSignupLogin();
        loginPage.loginAs(ConfigReader.getProperty("valid.email"), ConfigReader.getProperty("valid.password"));
        // Strong assertion (correct page + correct validation)
        Assert.assertTrue(homePage.isUserLoggedIn("kishore"),
                "Login failed: Expected username not displayed after login");
    }
    @Test
    public void testInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickSignupLogin();
        loginPage.loginAs(ConfigReader.getProperty("invalid.email"), ConfigReader.getProperty("invalid.password"));
        // Assertion for Invalid
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),"Expected error message is not displayed");
    }
}

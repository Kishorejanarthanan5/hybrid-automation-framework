package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.pages.HomePage;
import com.kishore.qa.pages.LoginPage;
import com.kishore.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
                { "unregistered_" + System.currentTimeMillis() + "@test.com", "AnyPass123",
                        "unregistered email" },

                { ConfigReader.getProperty("valid.email"), "DefinitelyWrong123",
                        "registered email, wrong password" },

                { ConfigReader.getProperty("valid.email"), "   ",
                        "registered email, whitespace-only password" }
        };
    }

    @Test
    public void testValidLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickSignupLogin();
        loginPage.loginAs(ConfigReader.getProperty("valid.email"), ConfigReader.getProperty("valid.password"));
        Assert.assertTrue(homePage.isUserLoggedIn(ConfigReader.getProperty("valid.username")),
                "Login failed: Expected username not displayed after login");
    }

    @Test(dataProvider = "invalidCredentials")
    public void testInvalidLogin(String email, String password, String scenario) {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);

        Assert.assertTrue(loginPage.isLoginErrorDisplayed(),
                "Expected site login error for scenario: " + scenario);
    }
}
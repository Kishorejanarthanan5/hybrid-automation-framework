package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // ===== Locators =====
    private final WebDriver driver;
    private final WaitUtils wait;
    private final By emailText = By.cssSelector("input[data-qa='login-email']");
    private final By passwordText = By.cssSelector("input[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("button[data-qa='login-button']");
    private final By loginErrorMessage = By.xpath("//p[contains(text(),'incorrect')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }
    // ===== Actions =====
    public void enterEmail(String email) {
        wait.waitForElementVisible(emailText).sendKeys(email);
    }

    public void enterPassword(String password){
        wait.waitForElementVisible(passwordText).sendKeys(password);
    }

    public void clickLogin(){
        wait.waitForElementClickable(loginBtn).click();
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    // ===== Validations =====
    public boolean isLoginErrorDisplayed() {
        return wait.waitForElementVisible(loginErrorMessage).isDisplayed();
    }

}


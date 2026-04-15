package com.kishore.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By emailText = By.cssSelector("input[data-qa='login-email']");
    private final By passwordText = By.cssSelector("input[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("button[data-qa='login-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterEmail(String email) {
        driver.findElement(emailText).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(passwordText).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginBtn).click();
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

}


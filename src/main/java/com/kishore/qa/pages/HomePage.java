package com.kishore.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By logoutLink = By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickSignupLogin(){
        driver.findElement(signupLoginLink).click();
    }

    public boolean isLogoutLinkDisplayed() {
        return driver.findElement(logoutLink).isDisplayed();
    }
}

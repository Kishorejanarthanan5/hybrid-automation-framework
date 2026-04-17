package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By productsLink = By.cssSelector("a[href='/products']");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickSignupLogin(){
        wait.waitForElementClickable(signupLoginLink).click();
    }

    public boolean isLogoutLinkDisplayed() {
        return wait.waitForElementVisible(logoutLink).isDisplayed();
    }

    public void clickProducts(){
        wait.waitForElementClickable(productsLink).click();
    }
}

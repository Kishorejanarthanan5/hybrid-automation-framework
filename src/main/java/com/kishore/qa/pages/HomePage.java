package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final By signupLoginLink = By.cssSelector("a[href='/login']");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By loggedInUser = By.xpath("//a[.//b]");
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickSignupLogin(){
        wait.waitForElementClickable(signupLoginLink).click();
    }


    public void clickProducts(){
        wait.waitForElementClickable(productsLink).click();
    }
    // ===== Validations =====
    // Basic check (any user logged in)
    public boolean isUserLoggedIn() {
        return wait.waitForElementVisible(loggedInUser).isDisplayed();
    }

    // Strong check (specific user)
    public boolean isUserLoggedIn(String username) {
        By dynamicUser = By.xpath("//a[.//b[text()='" + username + "']]");
        return wait.waitForElementVisible(dynamicUser).isDisplayed();
    }
}

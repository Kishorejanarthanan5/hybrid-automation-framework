package com.kishore.qa.utils;

import com.kishore.qa.constants.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;

        int timeout = Integer.parseInt(
                ConfigReader.getProperty(FrameworkConstants.EXPLICIT_WAIT_KEY)
        );

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // Wait until element is visible
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is clickable
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait until URL contains a specific fragment
    public boolean waitForUrlContains(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    // Wait until hover method
    public void hoverOverElement(By locator) {
        WebElement element = waitForElementVisible(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}

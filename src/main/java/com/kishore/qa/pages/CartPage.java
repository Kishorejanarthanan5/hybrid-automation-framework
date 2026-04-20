package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    // ===== Locators =====
    private final By cartTable = By.id("cart_info"); // main cart container
    private final By cartProductNames = By.cssSelector(".cart_description a");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // ===== Validations =====

    // Basic check → confirms cart page is loaded
    public boolean isCartPageDisplayed() {
        return wait.waitForElementVisible(cartTable).isDisplayed();
    }

    // Strong check → verifies specific product exists
    public boolean isProductInCart(String productName) {
        wait.waitForElementVisible(cartTable);   // ensure cart loaded first

        List<WebElement> products = driver.findElements(cartProductNames);

        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
}
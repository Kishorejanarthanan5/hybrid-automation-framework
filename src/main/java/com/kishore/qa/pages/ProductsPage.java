package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WaitUtils wait;
    // ===== Locators =====
    private final By searchInput = By.id("search_product");
    private final By searchBtn = By.id("submit_search");
    private final By searchedProductsTitle = By.cssSelector("h2.title.text-center");
    private final By productsList = By.cssSelector(".single-products");

    private final By firstProduct = By.cssSelector(".product-image-wrapper:first-of-type");
    private final By firstProductOverlay = By.cssSelector(".product-image-wrapper:hover .product-overlay");
    private final By addToCartBtn = By.cssSelector(".product-overlay .add-to-cart");
    private final By viewCartBtn = By.cssSelector("#cartModal a[href='/view_cart']");

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // ===== Actions =====
    public void searchProduct(String productName) {
        WebElement input = wait.waitForElementVisible(searchInput);
        input.clear();
        input.sendKeys(productName);
        wait.waitForElementClickable(searchBtn).click();
    }
    public void clickViewCartOnModal() {
        wait.waitForElementClickable(viewCartBtn).click();
    }

    public void addFirstProductToCart() {

        // Step 1: Get first product container
        WebElement product = wait.waitForElementVisible(firstProduct);
        // Step 2: Hover on that product
        wait.hoverOverElement(firstProduct);
        // Step 3: Find add-to-cart INSIDE this product
        WebElement addToCart = product.findElement(By.cssSelector(".product-overlay .add-to-cart"));

        // Step 4: Click using JS (avoids overlay issues)
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
    }

    // ===== Validations =====
    public boolean isSearchResultsDisplayed() {
        return wait.waitForElementVisible(searchedProductsTitle).isDisplayed();
    }
}

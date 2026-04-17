package com.kishore.qa.pages;

import com.kishore.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final By searchInput = By.id("search_product");
    private final By searchBtn = By.id("submit_search");
    private final By searchedProductsTitle = By.cssSelector("h2.title.text-center");
    private final By productsList = By.cssSelector(".single-products");

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void searchProduct(String productName) {
        WebElement input = wait.waitForElementVisible(searchInput);
        input.clear();
        input.sendKeys(productName);
        wait.waitForElementClickable(searchBtn).click();
    }

    public boolean isSearchResultsDisplayed() {
        return wait.waitForElementVisible(searchedProductsTitle).isDisplayed();
    }

}

package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.driver.DriverFactory;
import com.kishore.qa.pages.CartPage;
import com.kishore.qa.pages.HomePage;
import com.kishore.qa.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void testProductSearch() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.clickProducts();
        productsPage.searchProduct("Dress");

        Assert.assertTrue(productsPage.isSearchResultsDisplayed(),
                "Search results are not displayed properly");
    }

    @Test
    public void testAddToCart() {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.clickProducts();

        productsPage.addFirstProductToCart();
        productsPage.clickViewCartOnModal();

        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page not displayed after adding product");
    }
}

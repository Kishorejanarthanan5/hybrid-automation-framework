package com.kishore.qa.tests;

import com.kishore.qa.base.BaseTest;
import com.kishore.qa.driver.DriverFactory;
import com.kishore.qa.pages.HomePage;
import com.kishore.qa.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void testProductSearch() {
        WebDriver driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.clickProducts();
        productsPage.searchProduct("Dress");

        Assert.assertTrue(productsPage.isSearchResultsDisplayed(),"Search results are not displayed properly");
    }
}

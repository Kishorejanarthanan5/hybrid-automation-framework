package com.kishore.qa.tests;

import com.kishore.qa.api.services.ProductAPI;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductAPITest {

    private ProductAPI productAPI;

    @BeforeClass
    public void setup() {
        productAPI = new ProductAPI();
    }

    @Test
    public void testGetAllProducts() {

        Response response = productAPI.getAllProducts();

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("responseCode"), 200);
    }

    @Test
    public void testSearchProduct() {

        Response response = productAPI.searchProduct("dress");

        assertEquals(response.getStatusCode(), 200);
        List<String> productNames = response.jsonPath().getList("products.name");
        boolean hasMatch = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("dress"));
        assertTrue(hasMatch, "No dress products found in search results");
    }
}
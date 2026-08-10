package com.kishore.qa.api.services;

import com.kishore.qa.api.config.APIConfig;
import com.kishore.qa.constants.APIEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductAPI {

    public ProductAPI() {
        APIConfig.setup();
    }

    public Response getAllProducts() {
        return given()
                .when()
                .get(APIEndpoints.GET_ALL_PRODUCTS);
    }

    public Response searchProduct(String productName) {
        return given()
                .formParam("search_product", productName)
                .when()
                .post(APIEndpoints.SEARCH_PRODUCT);
    }
}
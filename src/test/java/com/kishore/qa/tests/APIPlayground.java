package com.kishore.qa.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APIPlayground {

    @Test
    public void getAllProducts() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://automationexercise.com/api/productsList");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.body().asString());
    }

    @Test
    public void searchProduct() {
        Response response = RestAssured
                .given()
                .formParam("search_product", "dress")
                .when()
                .post("https://automationexercise.com/api/searchProduct");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.body().asString());
    }
}
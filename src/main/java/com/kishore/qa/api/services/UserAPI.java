package com.kishore.qa.api.services;

import com.kishore.qa.api.config.APIConfig;
import com.kishore.qa.constants.APIEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    public UserAPI() {
        APIConfig.setup();
    }

    /**
     * Creates a user. Caller supplies the 3 fields tests care about;
     * everything else is required-by-the-API noise with sane defaults.
     */
    public Response createUser(String name, String email, String password) {
        return given()
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", "Mr")
                .formParam("birth_date", "1")
                .formParam("birth_month", "January")
                .formParam("birth_year", "1990")
                .formParam("firstname", name)
                .formParam("lastname", "Automation")
                .formParam("company", "Test Co")
                .formParam("address1", "1 Test Street")
                .formParam("address2", "Block B")
                .formParam("country", "India")
                .formParam("state", "Tamil Nadu")
                .formParam("city", "Coimbatore")
                .formParam("zipcode", "641001")
                .formParam("mobile_number", "9999999999")
                .when()
                .post(APIEndpoints.CREATE_ACCOUNT)
                .then()
                .extract().response();
    }

    public Response getUserByEmail(String email) {
        return given()
                .queryParam("email", email)
                .when()
                .get(APIEndpoints.GET_USER_BY_EMAIL)
                .then()
                .extract().response();
    }

    /** Cleanup helper — API 12. */
    public Response deleteUser(String email, String password) {
        return given()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(APIEndpoints.DELETE_ACCOUNT)
                .then()
                .extract().response();
    }
}
package com.kishore.qa.api.services;

import com.kishore.qa.api.config.APIConfig;
import com.kishore.qa.constants.APIEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    // Shared defaults — createAccount and updateAccount require the full
    // param set, so both must agree or an update silently mutates fields
    // the test never intended to change.
    private static final String TITLE          = "Mr";
    private static final String BIRTH_DATE     = "1";
    private static final String BIRTH_MONTH    = "January";
    private static final String BIRTH_YEAR     = "1990";
    private static final String LASTNAME       = "Automation";
    private static final String COMPANY        = "Test Co";
    private static final String ADDRESS1       = "1 Test Street";
    private static final String ADDRESS2       = "Block B";
    private static final String COUNTRY        = "India";
    private static final String STATE          = "Tamil Nadu";
    private static final String ZIPCODE        = "641001";
    private static final String MOBILE_NUMBER  = "9999999999";
    private static final String DEFAULT_CITY   = "Coimbatore";

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
                .formParam("title", TITLE)
                .formParam("birth_date", BIRTH_DATE)
                .formParam("birth_month", BIRTH_MONTH)
                .formParam("birth_year", BIRTH_YEAR)
                .formParam("firstname", name)
                .formParam("lastname", LASTNAME)
                .formParam("company", COMPANY)
                .formParam("address1", ADDRESS1)
                .formParam("address2", ADDRESS2)
                .formParam("country", COUNTRY)
                .formParam("state", STATE)
                .formParam("city", DEFAULT_CITY)
                .formParam("zipcode", ZIPCODE)
                .formParam("mobile_number", MOBILE_NUMBER)
                .when()
                .post(APIEndpoints.CREATE_ACCOUNT)
                .then()
                .extract().response();
    }

    /**
     * Updates a user. API 13 is a full-record PUT, not a PATCH — every field
     * is overwritten, so defaults here mirror createUser() exactly. Only the
     * caller-supplied name and city differ from the created state.
     */
    public Response updateUser(String email, String password, String name, String city) {
        return given()
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", TITLE)
                .formParam("birth_date", BIRTH_DATE)
                .formParam("birth_month", BIRTH_MONTH)
                .formParam("birth_year", BIRTH_YEAR)
                .formParam("firstname", name)
                .formParam("lastname", LASTNAME)
                .formParam("company", COMPANY)
                .formParam("address1", ADDRESS1)
                .formParam("address2", ADDRESS2)
                .formParam("country", COUNTRY)
                .formParam("state", STATE)
                .formParam("city", city)
                .formParam("zipcode", ZIPCODE)
                .formParam("mobile_number", MOBILE_NUMBER)
                .when()
                .put(APIEndpoints.UPDATE_ACCOUNT)
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
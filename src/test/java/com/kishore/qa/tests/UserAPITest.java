package com.kishore.qa.tests;

import com.kishore.qa.api.services.UserAPI;
import com.kishore.qa.utils.ConfigReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserAPITest {

    private static final String DEFAULT_PASSWORD = "Test@1234";

    private UserAPI userAPI;
    private final List<String> createdEmails = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        userAPI = new UserAPI();
    }

    private String uniqueEmail() {
        String email = "kishore_qa_" + System.currentTimeMillis() + "@test.com";
        createdEmails.add(email);   // registered before creation, on purpose
        return email;
    }

    @AfterClass(alwaysRun = true)
    public void cleanUpCreatedUsers() {
        for (String email : createdEmails) {
            Response response = userAPI.deleteUser(email, DEFAULT_PASSWORD);
            int code = response.jsonPath().getInt("responseCode");
            if (code != 200) {
                System.out.println("WARN cleanup failed for " + email + " -> " + response.asString());
            }
        }
        createdEmails.clear();
    }


    @Test
    public void testCreateUser() {
        String email = uniqueEmail();

        Response response = userAPI.createUser("Kishore", email, DEFAULT_PASSWORD);

        // transport-level status
        assertEquals(response.getStatusCode(), 200, "HTTP status");

        // the real result lives in the body
        JsonPath body = new JsonPath(response.asString());
        assertEquals(body.getInt("responseCode"), 201, "API responseCode");
        assertEquals(body.getString("message"), "User created!", "API message");
    }

    @Test
    public void testGetUserByEmail() {
        String email = ConfigReader.getProperty("valid.email");

        Response response = userAPI.getUserByEmail(email);

        assertEquals(response.getStatusCode(), 200, "HTTP status");

        JsonPath body = new JsonPath(response.asString());
        assertEquals(body.getInt("responseCode"), 200, "API responseCode");
        assertNotNull(body.getString("user.email"), "user block should be present");
        assertEquals(body.getString("user.email"), email, "returned email");
    }
}
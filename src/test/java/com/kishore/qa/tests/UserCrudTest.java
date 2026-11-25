package com.kishore.qa.tests;

import com.kishore.qa.api.services.UserAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * CRUD persistence chain for the User API.
 *
 * Steps 1, 3 and 5 confirm the API *said* the operation worked.
 * Steps 2, 4 and 6 confirm it *actually* worked by re-fetching state.
 * The second group is the point of this class.
 *
 * Deliberately uses dependsOnMethods: this test verifies a state sequence,
 * so a failed CREATE should skip the rest rather than cascade failures.
 * Must not be run in parallel.
 */
public class UserCrudTest {

    private static final String PASSWORD      = "Test@1234";
    private static final String ORIGINAL_NAME = "Kishore CRUD";
    private static final String ORIGINAL_CITY = "Coimbatore";
    private static final String UPDATED_NAME  = "Kishore Updated";
    private static final String UPDATED_CITY  = "Chennai";

    private UserAPI userAPI;
    private String email;              // class field — each @Test gets a fresh stack
    private boolean userDeleted;       // so cleanup doesn't double-delete

    @BeforeClass
    public void setUp() {
        userAPI = new UserAPI();
        email = "kishore_crud_" + System.currentTimeMillis() + "@test.com";
    }

    // ---------- Step 1: CREATE ----------

    @Test(description = "Step 1: create a user with a unique email")
    public void createUser() {
        Response response = userAPI.createUser(ORIGINAL_NAME, email, PASSWORD);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 201,
                "Unexpected responseCode on create. Body: " + body.prettify());
        assertEquals(body.getString("message"), "User created!",
                "Unexpected create message");
    }

    // ---------- Step 2: GET after create ----------

    @Test(dependsOnMethods = "createUser",
            description = "Step 2: the created user is retrievable")
    public void getUserAfterCreate() {
        Response response = userAPI.getUserByEmail(email);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 200,
                "Created user not retrievable. Body: " + body.prettify());
        assertNotNull(body.get("user"), "No user object in response");
        assertEquals(body.getString("user.email"), email,
                "Returned email does not match the one created");
        assertEquals(body.getString("user.city"), ORIGINAL_CITY,
                "City was not persisted as created");
    }

    // ---------- Step 3: UPDATE ----------

    @Test(dependsOnMethods = "getUserAfterCreate",
            description = "Step 3: update the user's name and city")
    public void updateUser() {
        Response response = userAPI.updateUser(email, PASSWORD, UPDATED_NAME, UPDATED_CITY);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 200,
                "Unexpected responseCode on update. Body: " + body.prettify());
        assertEquals(body.getString("message"), "User updated!",
                "Unexpected update message");
    }

    // ---------- Step 4: GET after update — the one that matters ----------

    @Test(dependsOnMethods = "updateUser",
            description = "Step 4: the update actually persisted")
    public void verifyUpdatePersisted() {
        Response response = userAPI.getUserByEmail(email);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 200,
                "User not retrievable after update. Body: " + body.prettify());
        assertEquals(body.getString("user.city"), UPDATED_CITY,
                "City did not persist — API reported success but state did not change");
        assertEquals(body.getString("user.name"), UPDATED_NAME,
                "Name did not persist — API reported success but state did not change");
    }

    // ---------- Step 5: DELETE ----------

    @Test(dependsOnMethods = "verifyUpdatePersisted",
            description = "Step 5: delete the user")
    public void deleteUser() {
        Response response = userAPI.deleteUser(email, PASSWORD);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 200,
                "Unexpected responseCode on delete. Body: " + body.prettify());
        assertEquals(body.getString("message"), "Account deleted!",
                "Unexpected delete message");
        userDeleted = true;
    }

    // ---------- Step 6: GET after delete — the other one that matters ----------

    @Test(dependsOnMethods = "deleteUser",
            description = "Step 6: the deleted user is genuinely gone")
    public void verifyUserGone() {
        Response response = userAPI.getUserByEmail(email);
        JsonPath body = response.jsonPath();

        assertEquals(body.getInt("responseCode"), 404,
                "Deleted user is still retrievable. Body: " + body.prettify());
    }

    // ---------- Cleanup ----------

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        if (userDeleted) {
            return;
        }
        try {
            userAPI.deleteUser(email, PASSWORD);
        } catch (Exception e) {
            System.out.println("Cleanup delete failed for " + email + ": " + e.getMessage());
        }
    }
}
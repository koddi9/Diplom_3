package util;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserTestUtil {

    private static final String CREATE_API = "/api/auth/register";
    private static final String LOGIN_API = "/api/auth/login";
    private static final String DELETE_API = "/api/auth/user";
    private static final String EDIT_API = "/api/auth/user";


    @Step("Creating user. Send POST request to " + CREATE_API)
    public static Response createTestUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post(CREATE_API);
    }

    @Step("Deleting user. Send DELETE request to " + DELETE_API)
    public static Response deleteTestUser(User user, String token) {
        return given()
                .contentType(ContentType.JSON)
                .and().header(new Header("Authorization", token))
                .and()
                .body(user)
                .when()
                .delete(DELETE_API);
    }

    public static Response deleteTestUser(User user) {
        return null;
    }



    @Step("Log in user. Send POST request to " + LOGIN_API)
    public static Response loginTestUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .post(LOGIN_API);
    }

    @Step("Log in user. Send POST request to " + LOGIN_API)
    public static Response editTestUser(User user, String token) {
        return given()
                .contentType(ContentType.JSON)
                .and().header(new Header("Authorization", token))
                .and()
                .body(user)
                .when()
                .patch(EDIT_API);
    }
}

package util;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.ResponseAuth;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static util.UserTestUtil.createTestUser;
import static util.UserTestUtil.deleteTestUser;

public class UserEditingTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check editing user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void EditingAuthorizedUserTest() {
        User newUser = new User("test6@email.com", "12345TeSt", "nikita");

        Response response = createTestUser(newUser);

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));
        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check creating user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void EditingUnauthorizedUserTest() {
        User newUser = new User("test11@email.com", "12345TeSt", "nikita");

        Response response = createTestUser(newUser);

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));
        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }
}

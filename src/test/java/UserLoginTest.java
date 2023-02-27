import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.ResponseAuth;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static util.UserTestUtil.*;

public class UserLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check log in user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void loginIsPossibleTest() {
        User newUser = new User("test33@email.com", "1234TeSt", "nikita");
        createTestUser(newUser);

        Response response = loginTestUser(newUser);

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));

        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check log in user with wrong credentials is impossible")
    @Description("Returns 401 code and body with description error message")
    public void wrongCredentialsTest() {
        User newUser = new User("test44@email.com", "1234TeSt", "nikita");

        newUser.setEmail("not_existed_login");
        newUser.setPassword("any_other_password");
        Response response = loginTestUser(newUser);

        response.then().statusCode(401).and()
                .assertThat().body("message", equalTo("email or password are incorrect"));

        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check log in user without required fields is impossible")
    @Description("Returns 401 code and body with description error message")
    public void missingFieldTest() {
        User newUser = new User("test555@email.com", "1234TeSt", "nikita");

        newUser.setEmail(null);
        Response response = loginTestUser(newUser);

        response.then().statusCode(401).and()
                .assertThat().body("message", equalTo("email or password are incorrect"));

        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }
}

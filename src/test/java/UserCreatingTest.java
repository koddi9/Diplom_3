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

public class UserCreatingTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check creating user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void creatingIsPossibleTest() {
        User newUser = new User("test11@email.com", "12345TeSt", "nikita");

        Response response = createTestUser(newUser);

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));
        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check creating already existed user is impossible")
    @Description("Returns 403 code and body with description error message")
    public void sameCredentialsTest() {
        User newUser = new User("test22@email.com", "1234TeSt", "nikita");
        createTestUser(newUser);

        Response response = createTestUser(newUser);

        response.then().statusCode(403).and()
                .assertThat().body("message", equalTo("User already exists"));

        String at = response.getBody().as(ResponseAuth.class).getAccessToken();
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check creating user without required fields is impossible")
    @Description("Returns 403 code and body with description error message")
    public void missingFieldTest() {
        User newUser = new User(null, "1234", "nikita");

        Response response = createTestUser(newUser);

        response.then().statusCode(403).and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }
}

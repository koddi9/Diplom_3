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
import static util.UserTestUtil.*;

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
        User newUser = new User("test666@email.com", "12345TeSt", "nikita");

        Response createResponse = createTestUser(newUser);
        String at = createResponse.getBody().as(ResponseAuth.class).getAccessToken();
        newUser.setName("nikolay");
        Response editResponse = editTestUser(newUser, at);
        editResponse.then().statusCode(200).and().assertThat().body("success", equalTo(true));
        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check editing unauthorized user is impossible")
    @Description("Returns 401 code and body with description error message")
    public void EditingUnauthorizedUserTest() {
        User newUser = new User("test777@email.com", "12345TeSt", "nikita");

        Response response = editTestUser(newUser,"");

        response.then().statusCode(401).and()
                .assertThat().body("message", equalTo("You should be authorised"));
    }
}

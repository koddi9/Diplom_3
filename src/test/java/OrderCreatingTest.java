import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.Order;
import model.ResponseAuth;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static util.OrderTestUtil.createTestOrder;
import static util.UserTestUtil.createTestUser;
import static util.UserTestUtil.deleteTestUser;

public class OrderCreatingTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check creating order for authorized user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void creatingOrderForAuthorizedUserTest() {
        User newUser = new User("test888@email.com", "12345TeSt", "nikita");
        Response createResponse = createTestUser(newUser);
        String at = createResponse.getBody().as(ResponseAuth.class).getAccessToken();

        Order testOrder = new Order(List.of("61c0c5a71d1f82001bdaaa6d"));
        Response response = createTestOrder(testOrder, at);

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));

        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check creating order for unauthorized user is possible")
    @Description("Returns 200 code and body with success: true status")
    public void creatingOrderForUnauthorizedUserTest() {
        Order testOrder = new Order(List.of("61c0c5a71d1f82001bdaaa6d"));
        Response response = createTestOrder(testOrder, "");

        response.then().statusCode(200).and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Check creating order withoutIngredients is impossible")
    @Description("Returns 400 code and body with description error message")
    public void creatingOrderWithoutIngredientsTest() {
        User newUser = new User("test888@email.com", "12345TeSt", "nikita");
        Response createResponse = createTestUser(newUser);
        String at = createResponse.getBody().as(ResponseAuth.class).getAccessToken();

        Order testOrder = new Order(Collections.emptyList());
        Response response = createTestOrder(testOrder, at);

        response.then().statusCode(400).and().assertThat().body("message", equalTo("Ingredient ids must be provided"));

        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check creating order with wrong ingredient hash is impossible")
    @Description("Returns 500 code and body with description error message")
    public void creatingOrderWithWrongIngredientHashTest() {
        User newUser = new User("test888@email.com", "12345TeSt", "nikita");
        Response createResponse = createTestUser(newUser);
        String at = createResponse.getBody().as(ResponseAuth.class).getAccessToken();

        Order testOrder = new Order(List.of("wrong_hash"));
        Response response = createTestOrder(testOrder, at);

        response.then().statusCode(500);

        deleteTestUser(newUser, at);
    }
}

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

import java.util.List;

import static org.hamcrest.Matchers.*;
import static util.OrderTestUtil.createTestOrder;
import static util.OrderTestUtil.getOrderList;
import static util.UserTestUtil.createTestUser;
import static util.UserTestUtil.deleteTestUser;

public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    @DisplayName("Check getting order list for authorized user")
    @Description("Returns 200 code and body with orders array")
    public void gettingOrderListForAuthorizedUserTest() {
        User newUser = new User("test999@email.com", "12345TeSt", "nikita");
        Response createResponse = createTestUser(newUser);
        String at = createResponse.getBody().as(ResponseAuth.class).getAccessToken();
        Order testOrder = new Order(List.of("61c0c5a71d1f82001bdaaa6d"));
        createTestOrder(testOrder, at);

        Response response = getOrderList(at);

        response.then().statusCode(200).and().assertThat().body("orders", is(not(empty())));

        deleteTestUser(newUser, at);
    }

    @Test
    @DisplayName("Check getting order list for unauthorized user")
    @Description("Returns 401 code and body with description error message")
    public void gettingOrderListForUnauthorizedUserTest() {
        Response response = getOrderList("");

        response.then().statusCode(401).and().assertThat().body("message", equalTo("You should be authorised"));
    }
}

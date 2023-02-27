package util;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderTestUtil {

    private static final String CREATE_API = "/api/orders";
    private static final String ORDER_LIST_API = "/api/orders";

    @Step("Creating order. Send POST request to " + CREATE_API)
    public static Response createTestOrder(Order order, String token) {
        return given()
                .contentType(ContentType.JSON)
                .and().header(new Header("Authorization", token))
                .and()
                .body(order)
                .when()
                .post(CREATE_API);
    }

    @Step("Getting order. Send GET request to " + ORDER_LIST_API)
    public static Response getOrderList(String token) {
        return given()
                .contentType(ContentType.JSON)
                .and().header(new Header("Authorization", token))
                .when()
                .get(ORDER_LIST_API);
    }

}

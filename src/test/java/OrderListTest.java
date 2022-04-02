import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderListTest {
    Order order = new Order();

    @Test
    public void orderListGetStatuCode() {
        Assert.assertEquals(200,order.getOrdersList());
    }
    @Test
    public void checkOrderListOfNotNull() {
        ValidatableResponse response =  given()
                .header("Content-type", "application/json").when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders").then().assertThat().statusCode(200)
                .and().body("orders.id", not(""));
    }
}

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderListTest {
    OrderStep orders = new OrderStep();

    @Test
    public void orderListGetStatuCode() {
        Assert.assertEquals(200,orders.getOrdersList());
    }
    @Test
    public void checkOrderListOfNotNull() {
        ValidatableResponse response =  given()
                .header("Content-type", "application/json").when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders").then().assertThat().statusCode(200)
                .and().body("orders.id", not(""));
    }
}

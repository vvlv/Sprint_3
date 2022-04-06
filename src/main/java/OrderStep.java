import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class OrderStep {
    Faker faker = new Faker(new Locale("ru"));
    public OrderStep () {}
public OrderStep (String color){
    switch (color){
        case "GREY":
            bodyOrderGenerateGreyColors();
            break;
        case "BLACK":
            bodyOrderGenerateBlackColors();
            break;
        case "ALL":
            bodyOrderGenerateAllColors();
            break;
        case "NO":
            bodyOrderGenerateNoColors();
            break;
    }
}
    private Response response;
private Response getResponse() {
    return response;
}
private void setResponse (Response response){
        this.response = response;
        Orders order = new Orders();
        order.setStatus(response.statusCode());
    }



    @Step("Формирование тела без цветов")
    public Object bodyOrderGenerateNoColors () {
        Orders orders = new Orders(faker.name().firstName(),faker.name().lastName(),faker.address().streetAddress(),4,faker.phoneNumber().phoneNumber(),5,
                "2020-06-06","Saske, come back to Konoha","","");
        sendResponse(orders);
        return null;
    }
    @Step("Формирование тела со всеми цветами")
    public Object bodyOrderGenerateAllColors () {
        Orders orders = new Orders(faker.name().firstName(),faker.name().lastName(),faker.address().streetAddress(),4,faker.phoneNumber().phoneNumber(),5,
                "2020-06-06","Saske, come back to Konoha","BLACK","GREY");
        sendResponse(orders);
        return null;
    }
    @Step("Формирование тела только для черного цвета")
    public Object bodyOrderGenerateBlackColors () {
        Orders orders = new Orders(faker.name().firstName(),faker.name().lastName(),faker.address().streetAddress(),4,faker.phoneNumber().phoneNumber(),5,
                "2020-06-06","Saske, come back to Konoha","BLACK","");
        sendResponse(orders);
        return null;
    }
    @Step("Формирование тела только для серого цвета")
    public Object bodyOrderGenerateGreyColors () {
        Orders orders = new Orders(faker.name().firstName(),faker.name().lastName(),faker.address().streetAddress(),4,faker.phoneNumber().phoneNumber(),5,
                "2020-06-06","Saske, come back to Konoha","","GREY");
        sendResponse(orders);
        return null;
    }

    public void sendResponse(Orders orders) {
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(orders)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");

        setResponse(response);
    }

    @Step("Отправка запроса апи заказы и Проверка ответа с номером трека")
    public int loginCourierCheckMessageId () {

return getResponse().as(Orders.class).getTrack();

    }
    @Step("Отправка запроса апи заказы и Проверка ответа STATUSCODE")
    public int getStatus () {

        return getResponse().statusCode();

    }

    @Step("Отправка запроса апи заказы и получение статус кода")
    public int getOrdersList () {
       Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");

       return response.statusCode();
    }

}
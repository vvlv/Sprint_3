import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Order {
    Faker faker = new Faker(new Locale("ru"));
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String address = faker.address().streetAddress();
    private int metroStation = 4;
    private String phone = faker.phoneNumber().phoneNumber();
    private int rentTime = 5;
    private String deliveryDate = "2020-06-06";
    private String comment = "Saske, come back to Konoha";
    private String colorBlack = "BLACK";
    private String colorGrey = "GREY";
    private int status = 0;
    private int track;

    public void setTrack (int track) {
        this.track = track;
    }
    public int getTrack () {
        return track;
    }
    public void setStatus (int status) {
        this.status = status;
    }
    public int getStatus () {
        return status;
    }

 public Order (){}
    public  Order(String color) {
        String registerRequestBody = "";

        switch (color) {
            case "BLACK" :
            registerRequestBody =
                    "{" +
                            "\"firstName\":\"" + firstName + "\","
                            +
                            "\"lastName\":\"" + lastName + "\","
                            +
                            "\"address\":\"" + address + "\","
                            +
                            "\"metroStation\":\"" + metroStation + "\","
                            +
                            "\"phone\":\"" + phone + "\","
                            +
                            "\"rentTime\":\"" + rentTime + "\","
                            +
                            "\"deliveryDate\":\"" + deliveryDate + "\","
                            +
                            "\"comment\":\"" + comment + "\","
                            +
                            "\"color\": [\"" + colorBlack + "\"]"
                            + "}";
            break;
            case "GREY" :
                registerRequestBody =
                        "{" +
                                "\"firstName\":\"" + firstName + "\","
                                +
                                "\"lastName\":\"" + lastName + "\","
                                +
                                "\"address\":\"" + address + "\","
                                +
                                "\"metroStation\":\"" + metroStation + "\","
                                +
                                "\"phone\":\"" + phone + "\","
                                +
                                "\"rentTime\":\"" + rentTime + "\","
                                +
                                "\"deliveryDate\":\"" + deliveryDate + "\","
                                +
                                "\"comment\":\"" + comment + "\","
                                +
                                "\"color\": [\"" + colorGrey + "\"]"
                                + "}";
                break;
            case "BLACK_AND_GREY" :
                registerRequestBody =
                        "{" +
                                "\"firstName\":\"" + firstName + "\","
                                +
                                "\"lastName\":\"" + lastName + "\","
                                +
                                "\"address\":\"" + address + "\","
                                +
                                "\"metroStation\":\"" + metroStation + "\","
                                +
                                "\"phone\":\"" + phone + "\","
                                +
                                "\"rentTime\":\"" + rentTime + "\","
                                +
                                "\"deliveryDate\":\"" + deliveryDate + "\","
                                +
                                "\"comment\":\"" + comment + "\","
                                +
                                "\"color\": [\"" + colorBlack+ "\","+ "\""+colorGrey + "\"]"
                                + "}";
                break;
            case "NO_COLOR" :
                registerRequestBody =
                        "{" +
                                "\"firstName\":\"" + firstName + "\","
                                +
                                "\"lastName\":\"" + lastName + "\","
                                +
                                "\"address\":\"" + address + "\","
                                +
                                "\"metroStation\":\"" + metroStation + "\","
                                +
                                "\"phone\":\"" + phone + "\","
                                +
                                "\"rentTime\":\"" + rentTime + "\","
                                +
                                "\"deliveryDate\":\"" + deliveryDate + "\","
                                +
                                "\"comment\":\"" + comment + "\","
                                +
                                "\"color\": [\" \"]"
                                + "}";
                break;
        }
        sendResponse(registerRequestBody);

    }
    public void sendResponse(String fullBody) {
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(fullBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders");
        setStatus(response.statusCode());
    }

    @Step("Проверка ответа с номером трека")
    public int loginCourierCheckMessageId (String fullBody) {


        Order response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(fullBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/orders")
                .as(Order.class);

        return response.getTrack();
    }

    public int getOrdersList () {

       Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get("https://qa-scooter.praktikum-services.ru/api/v1/orders");

       return response.statusCode();
    }

}
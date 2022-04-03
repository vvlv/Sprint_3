// импортируем RestAssured
// импортируем Response
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
// импортируем библиотеку генерации строк
import org.apache.commons.lang3.RandomStringUtils;
// импортируем список
import java.util.ArrayList;
import java.util.Locale;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static io.restassured.RestAssured.*;
public class Couriers {
    Faker faker = new Faker(new Locale("ru"));
    private Response response;
    ArrayList<String> loginPass = new ArrayList<>();
    private void setResponse (Response response){
        this.response = response;
    }
    private Response getResponse (){
        return response;
    }
    private String name = faker.name().firstName();
    private String password = "123456";
    private String login = "Ivan1991";
    private int registerCouierStatusCode;

    @Step("Генерация уникальных данных курьера")
    public void courierNewBodyDataGenerate() {
        // с помощью библиотеки RandomStringUtils генерируем логин
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        String login = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String password = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        String firstName = RandomStringUtils.randomAlphabetic(10);
        CouriersCreate couriersNew = new CouriersCreate(login,password,firstName);
        requestBodyGenerate(couriersNew);
    }
    @Step("Генерация уникальных данных курьера без логина")
    public void courierNewBodyDataGenerateNotLogin() {
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        CouriersCreate couriersNew = new CouriersCreate(null,password,firstName);
        requestBodyGenerate(couriersNew);
    }
    @Step("Генерация уникальных данных курьера без пароля")
    public void courierNewBodyDataGenerateNotPass() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        CouriersCreate couriersNew = new CouriersCreate(login,null,firstName);
        requestBodyGenerate(couriersNew);
    }
    @Step("Генерация уникальных данных курьера без имени")
    public void courierNewBodyDataGenerateNotName() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        CouriersCreate couriersNew = new CouriersCreate(login,password,null);
        requestBodyGenerate(couriersNew);
    }

@Step("Генерация тела запроса для создания курьера")
public void requestBodyGenerate (CouriersCreate couriers) {
     Response response =  given()
            .header("Content-type", "application/json")
            .and()
            .body(couriers)
            .when()
            .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");
     setResponse(response);
}
@Step("Получение статус кода ответа")
public int getResponseStatusCode (Response response) {
return response.statusCode();
}
    @Step("Запись данных курьера в лист")
    public void getRegisterCouierData (Response response) {

        if (getResponseStatusCode(response) == 201) {
            loginPass.add(response.as(CouriersCreate.class).getPassword());
            loginPass.add(response.as(CouriersCreate.class).getLogin());
            registerCouierStatusCode = response.statusCode();

        } else {registerCouierStatusCode = response.statusCode();
        }
    }
    @Step("получение статус кода после создания нового курьера с уникальными данными")
    public int getRegisterCouierStatusCode () {return registerCouierStatusCode;}

    @Step("Логин курьера")
    public void loginCourier () {
        CouriersLogin login = new CouriersLogin(loginPass.get(0), loginPass.get(1));
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        setResponse(response);
    }
@Step("Проверка статуса логина курьера")
public int courierLoginStatusCheck () {
    return  response.statusCode();
}
    @Step("Проверка статуса Создания курьера")
    public int courierCreateStatusCheck () {
        return  response.statusCode();
    }
    @Step("Проверка логина курьера при нехватке поля логин")
    public void incorrectLoginCourierNotLogin () {
        loginPass.set(0,"");
        loginCourier();
    }
    @Step("Проверка логина курьера при нехватке поля пароль")
    public void incorrectLoginCourierNotPass () {
        loginPass.set(1,"");
        loginCourier();
    }
    @Step("Проверка логина курьера при неправильном значении поля логин")
    public void incorrectLoginCourierWrongLogin () {
        loginPass.set(0,RandomStringUtils.randomAlphabetic(10));
        loginCourier();
    }
    @Step("Проверка логина курьера при неправильном значении поля Пароль")
    public void incorrectLoginCourierWrongPass () {
        loginPass.set(1,RandomStringUtils.randomAlphabetic(10));
        loginCourier();
    }
    @Step("Проверка логина курьера при правильных данных")
    public void correctLoginCourier () {
        loginCourier();
    }

    @Step("Проверка система вернёт ошибку, если неправильно указать логин или пароль, не указано логин или пароль")
    public String getMessageResponseOfLoginCourier () {
        return response.as(CouriersResponse.class).getMessage();
    }

}
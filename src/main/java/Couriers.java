// импортируем RestAssured
// импортируем Response
import io.qameta.allure.Step;
import io.restassured.response.Response;
// импортируем библиотеку генерации строк
import org.apache.commons.lang3.RandomStringUtils;
// импортируем список
import java.util.ArrayList;
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()
import static io.restassured.RestAssured.*;
public class Couriers {
    private String name = "Иван";
    private String password = "123456";
    private String login = "Ivan1991";
    private int registerCouierStatusCode;
    /*
метод регистрации нового курьера
возвращает список из логина и пароля
если регистрация не удалась, возвращает пустой список
*/
    @Step("Регистрация нового курьера с уникальными данными")
    public ArrayList<String> registerCourierCreate(String requestType){
        String courierLogin = "";
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String courierPassword = "";
        // с помощью библиотеки RandomStringUtils генерируем имя курьера
        String courierFirstName = "";
        switch (requestType) {
            case "УНИКАЛЬНЫЙ":
            // с помощью библиотеки RandomStringUtils генерируем логин
            // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
            courierLogin = RandomStringUtils.randomAlphabetic(10);
            // с помощью библиотеки RandomStringUtils генерируем пароль
            courierPassword = RandomStringUtils.randomAlphabetic(10);
            // с помощью библиотеки RandomStringUtils генерируем имя курьера
            courierFirstName = RandomStringUtils.randomAlphabetic(10);
            break;
            case "СУЩЕСТВУЮЩИЙ":
                courierLogin = login;
                courierPassword = password;
                courierFirstName = name;
                break;
            case "ЗАПРОС_БЕЗ_ЛОГИНА":
                courierPassword = RandomStringUtils.randomAlphabetic(10);
                courierFirstName = RandomStringUtils.randomAlphabetic(10);
                break;
            case "ЗАПРОС_БЕЗ_ПАРОЛЯ":
                courierLogin = RandomStringUtils.randomAlphabetic(10);
                courierFirstName = RandomStringUtils.randomAlphabetic(10);
                break;
            case "ЗАПРОС_БЕЗ_ИМЕНИ":
                courierLogin = RandomStringUtils.randomAlphabetic(10);
                courierPassword = RandomStringUtils.randomAlphabetic(10);
                break;
            case "ЛОГИН_СУЩЕСТВУЕТ":
                courierLogin = login;
                courierPassword = RandomStringUtils.randomAlphabetic(10);
                courierFirstName = RandomStringUtils.randomAlphabetic(10);
                break;
        }
        // создаём список, чтобы метод мог его вернуть
        ArrayList<String> loginPass = new ArrayList<>();

        // собираем в строку тело запроса на регистрацию, подставляя в него логин, пароль и имя курьера
        String registerRequestBody = "{\"login\":\"" + courierLogin + "\","
                + "\"password\":\"" + courierPassword + "\","
                + "\"firstName\":\"" + courierFirstName + "\"}";

        // отправляем запрос на регистрацию курьера и сохраняем ответ в переменную response класса Response
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier");

        // если регистрация прошла успешно (код ответа 201), добавляем в список логин и пароль курьера
        if (response.statusCode() == 201) {
            loginPass.add(courierLogin);
            loginPass.add(courierPassword);
            registerCouierStatusCode = response.statusCode();

        } else {registerCouierStatusCode = response.statusCode();
            }

        // возвращаем список
        return loginPass;


    }
    @Step("получение статус кода после создания нового курьера с уникальными данными")
    public int getRegisterCouierStatusCode () {return registerCouierStatusCode;}

    @Step("Проверка логина курьера")
    public int loginCourier () {
        ArrayList<String> loginData = registerCourierCreate("УНИКАЛЬНЫЙ");

        String registerRequestBody =
                "{" +
                "\"login\":\"" + loginData.get(0) + "\","
                +
                "\"password\":\"" + loginData.get(1) + "\"}";

        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");

        return response.statusCode();
    }
    @Step("Проверка логина курьера при нехватке данных для логина")
    public int incorrectLoginCourier (ArrayList loginPass, String typeOfTest) {
        String registerRequestBody = "";
        switch (typeOfTest) {
            case "БЕЗ_ЛОГИНА" :
             registerRequestBody =
                    "{" +
                            "\"login\":\"" + "" + "\","
                            +
                            "\"password\":\"" + loginPass.get(1) + "\"}";
            break;
            case "БЕЗ_ПАРОЛЯ" :
                 registerRequestBody =
                        "{" +
                                "\"login\":\"" + loginPass.get(0) + "\","
                                +
                                "\"password\":\"" + "" + "\"}";
                break;
            case "НЕПРАВИЛЬНЫЙ_ЛОГИН" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + RandomStringUtils.randomAlphabetic(10) + "\","
                                +
                                "\"password\":\"" + loginPass.get(1) + "\"}";
                break;
            case "НЕПРАВИЛЬНЫЙ_ПАРОЛЬ" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + loginPass.get(0) + "\","
                                +
                                "\"password\":\"" + RandomStringUtils.randomAlphabetic(10) + "\"}";
                break;
        }
        Response response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login");
        System.out.println(response.statusLine());
        return response.statusCode();

    }
    @Step("система вернёт ошибку, если неправильно указать логин или пароль, не указано логин или пароль")
    public String getMessageResponse (ArrayList loginPass, String typeOfTest) {
        String registerRequestBody = "";
        switch (typeOfTest) {
            case "БЕЗ_ЛОГИНА" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + "" + "\","
                                +
                                "\"password\":\"" + loginPass.get(1) + "\"}";
                break;
            case "БЕЗ_ПАРОЛЯ" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + loginPass.get(0) + "\","
                                +
                                "\"password\":\"" + "" + "\"}";
                break;
            case "НЕПРАВИЛЬНЫЙ_ЛОГИН" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + RandomStringUtils.randomAlphabetic(10) + "\","
                                +
                                "\"password\":\"" + loginPass.get(1) + "\"}";
                break;
            case "НЕПРАВИЛЬНЫЙ_ПАРОЛЬ" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + loginPass.get(0) + "\","
                                +
                                "\"password\":\"" + RandomStringUtils.randomAlphabetic(10) + "\"}";
                break;
            case "КОРРЕКТНЫЙ" :
                registerRequestBody =
                        "{" +
                                "\"login\":\"" + loginPass.get(0) + "\","
                                +
                                "\"password\":\"" + loginPass.get(1) + "\"}";
                break;
        }
        CouriersResponse response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login")
        .as(CouriersResponse.class);

return response.getMessage();
    }
    @Step("Проверка логина курьера")
    public int loginCourierCheckMessageId () {


        String registerRequestBody =
                "{" +
                        "\"login\":\"" + login + "\","
                        +
                        "\"password\":\"" + password + "\"}";

        CouriersResponse response =  given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post("https://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .as(CouriersResponse.class);

        return response.getId();
    }

}

/*


        Обрати внимание: всю папку target коммитить не нужно. Чтобы добавить в коммит только отчёт, открой консоль, перейди в папку проекта и выполни команды:

        # добавляем папку с отчётом Allure к файлам. Ключ -f пригодится, если папка target указана в .gitignore
        git add -f .\target\allure-results\.
        # выполняем коммит
        git commit -m "add allure report"
        # так отправишь файлы в удалённый репозиторий
        git push
        Не забудь: тесты должны быть независимыми. Все данные нужно удалять после того, как тест выполнится. Если для проверки нужен пользователь, создай его перед тестом и удали после.
*/
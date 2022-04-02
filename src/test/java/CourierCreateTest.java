// импортируем RestAssured
// импортируем Response
// импортируем библиотеку генерации строк
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
// импортируем список
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()


public class CourierCreateTest {
/*
    Создание курьера
    Проверь:
            1-курьера можно создать;
        2-нельзя создать двух одинаковых курьеров;
        3-чтобы создать курьера, нужно передать в ручку все обязательные поля;
        4-запрос возвращает правильный код ответа;
        5-успешный запрос возвращает ok: true;
        6-если одного из полей нет, запрос возвращает ошибку;
        7-если создать пользователя с логином, который уже есть, возвращается ошибка.
*/
Couriers courier = new Couriers();

//В этом тесте проверяется что создается курьер и проверяется ответ
@Test
    public void courierCreate () {
    courier.registerCourierCreate("УНИКАЛЬНЫЙ");
    Assert.assertEquals(201,courier.getRegisterCouierStatusCode());
}
@Test
    public void courierCreateNotNew () {
    courier.registerCourierCreate("СУЩЕСТВУЮЩИЙ"); //Создается уникальный курьер
    courier.registerCourierCreate("СУЩЕСТВУЮЩИЙ"); //Создается курьер с данными с предыдущего шага
    Assert.assertEquals(409,courier.getRegisterCouierStatusCode());
}
    @Test
    public void courierCreateNoLogin () {
        courier.registerCourierCreate("ЗАПРОС_БЕЗ_ЛОГИНА");
        Assert.assertEquals(400,courier.getRegisterCouierStatusCode());
    }
    @Test
    public void courierCreateNoPassword() {
        courier.registerCourierCreate("ЗАПРОС_БЕЗ_ПАРОЛЯ");
        Assert.assertEquals(400,courier.getRegisterCouierStatusCode());
    }

    //Тут возвращается 201 хотя по логике имя дожно быть обязательны и обратного не указано в документации
    // в слаке есть обсуждение https://yandex-students.slack.com/archives/C02QSTBQJE4/p1647706761184229
    @Test
    @Description("Метод создает курьера без имени в запросе")
    public void courierCreateNoName () {
        courier.registerCourierCreate("ЗАПРОС_БЕЗ_ИМЕНИ");
        Assert.assertEquals(400,courier.getRegisterCouierStatusCode());
    }
    @Test
    public void courierCreateNotNewLogin () {
        courier.registerCourierCreate("ЛОГИН_СУЩЕСТВУЕТ");
        Assert.assertEquals(409,courier.getRegisterCouierStatusCode());
    }

    }



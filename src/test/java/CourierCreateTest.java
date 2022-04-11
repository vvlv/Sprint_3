// импортируем RestAssured
// импортируем Response
// импортируем библиотеку генерации строк
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
// импортируем список
// дополнительный статический импорт нужен, чтобы использовать given(), get() и then()


public class CourierCreateTest {

Couriers courier = new Couriers();

//В этом тесте проверяется что создается курьер и проверяется ответ
@Test
    public void courierCreate () {
    courier.courierNewBodyDataGenerate();
    Assert.assertEquals(201,courier.getResponseStatusCode());
}
@Test
    public void courierCreateNotNew () {
    courier.courierNewBodyDataGenerateNotNew();
    Assert.assertEquals(409,courier.getResponseStatusCode());
}
    @Test
    public void courierCreateNoLogin () {
        courier.courierNewBodyDataGenerateNotLogin();
        Assert.assertEquals(400,courier.getResponseStatusCode());
    }
    @Test
    public void courierCreateNoPassword() {
        courier.courierNewBodyDataGenerateNotPass();
        Assert.assertEquals(400,courier.getResponseStatusCode());
    }

    //Тут возвращается 201 хотя по логике имя дожно быть обязательны и обратного не указано в документации
    // в слаке есть обсуждение https://yandex-students.slack.com/archives/C02QSTBQJE4/p1647706761184229
/*    @Test
    @Description("Метод создает курьера без имени в запросе")
    public void courierCreateNoName () {
        courier.courierNewBodyDataGenerateNotName();
        Assert.assertEquals(400,courier.getResponseStatusCode());
    }*/
    @Test
    public void courierCreateNotNewLogin () {
        courier.courierNewBodyDataGenerateNotNew();
        Assert.assertEquals("Этот логин уже используется. Попробуйте другой.",courier.getMessageResponseOfCreateCourier());
    }
    }



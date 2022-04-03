import org.junit.Assert;
import org.junit.Test;

public class CourierLoginTest {
/*    Логин курьера
    Проверь:
            1-курьер может авторизоваться;
        2-для авторизации нужно передать все обязательные поля;
        3-система вернёт ошибку, если неправильно указать логин или пароль;
        4-если какого-то поля нет, запрос возвращает ошибку;
        5-если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
        6-успешный запрос возвращает id.*/
Couriers courier = new Couriers();

    @Test
    public void courierLogin () {
        courier.courierNewBodyDataGenerate();
        courier.correctLoginCourier();
        Assert.assertEquals(200,courier.getResponseStatusCode());
    }    @Test
    public void courierNoLoginTest () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierNotLogin();
        Assert.assertEquals(400,courier.getResponseStatusCode());
    }
    @Test
    public void courierNoPasswordTest () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierNotPass();
        Assert.assertEquals(400,courier.getResponseStatusCode());
    }
    @Test
    public void courierIncorrectLoginTest () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierWrongLogin();
        Assert.assertEquals(404,courier.getResponseStatusCode());
    }
    @Test
    public void courierIncorrectPasswordTest () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierWrongPass();
        Assert.assertEquals(404,courier.getResponseStatusCode());    }
    @Test
    public void courierLoginErrorMessageCheck () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierWrongLogin();
        Assert.assertEquals("Учетная запись не найдена",courier.getMessageResponseOfLoginCourier());
    }
    @Test
    public void courierPasswordErrorMessageCheck () {
courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierWrongPass();
        Assert.assertEquals("Учетная запись не найдена",courier.getMessageResponseOfLoginCourier());
    }
    @Test
    public void courierNoLoginErrorMessageCheck () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierNotLogin();
        Assert.assertEquals("Недостаточно данных для входа",courier.getMessageResponseOfLoginCourier());
    }
    @Test
    public void courierNoPasswordErrorMessageCheck () {
        courier.courierNewBodyDataGenerate();
        courier.incorrectLoginCourierNotPass();
        Assert.assertEquals("Недостаточно данных для входа",courier.getMessageResponseOfLoginCourier());
    }
    @Test
    public void courierCorrectLoginMessageCheck () {
        courier.courierNewBodyDataGenerateNotNew();
        courier.correctLoginCourier();
        Assert.assertEquals(45046,courier.getIdCourierAfterLogin());
    }
}

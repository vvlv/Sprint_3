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
        Assert.assertEquals(200,courier.loginCourier());
    }
    @Test
    public void courierNoLoginTest () {
        Assert.assertEquals(400,courier.incorrectLoginCourier(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"БЕЗ_ЛОГИНА"));
    }
    @Test
    public void courierNoPasswordTest () {
        Assert.assertEquals(400,courier.incorrectLoginCourier(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"БЕЗ_ПАРОЛЯ"));
    }
    @Test
    public void courierIncorrectLoginTest () {
        Assert.assertEquals(404,courier.incorrectLoginCourier(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"НЕПРАВИЛЬНЫЙ_ЛОГИН"));
    }
    @Test
    public void courierIncorrectPasswordTest () {
        Assert.assertEquals(404,courier.incorrectLoginCourier(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"НЕПРАВИЛЬНЫЙ_ПАРОЛЬ"));
    }
    @Test
    public void courierLoginErrorMessageCheck () {
        Assert.assertEquals("Учетная запись не найдена",courier.getMessageResponse(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"НЕПРАВИЛЬНЫЙ_ЛОГИН"));
    }
    @Test
    public void courierPasswordErrorMessageCheck () {
        Assert.assertEquals("Учетная запись не найдена",courier.getMessageResponse(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"НЕПРАВИЛЬНЫЙ_ПАРОЛЬ"));
    }
    @Test
    public void courierNoLoginErrorMessageCheck () {
        Assert.assertEquals("Недостаточно данных для входа",courier.getMessageResponse(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"БЕЗ_ЛОГИНА"));
    }
    @Test
    public void courierNoPasswordErrorMessageCheck () {
        Assert.assertEquals("Недостаточно данных для входа",courier.getMessageResponse(courier.registerCourierCreate("УНИКАЛЬНЫЙ"),"БЕЗ_ПАРОЛЯ"));
    }
    @Test
    public void courierCorrectLoginMessageCheck () {
        Assert.assertEquals(45046,courier.loginCourierCheckMessageId());
    }
}

import io.restassured.internal.common.assertion.Assertion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"BLACK", 201},
                {"GREY", 201},
                {"BLACK_AND_GREY", 201},
                {"NO_COLOR", 201},
        });
    }

    private String color;
    private int expected;

    public OrderTest(String color, int expected) {
        this.color = color;
        this.expected = expected;
    }

    @Test
    public void createOrderTest() {
        Order order = new Order(color);
        int actual = order.getStatus();
        assertEquals("Проверка создания заказа с разными цветами",expected, actual);
    }

}

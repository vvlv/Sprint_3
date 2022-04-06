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
                {"GREY", 201},
                {"BLACK", 201},
                {"NO", 201},
                {"ALL", 201},
        });
    }

    private String color;
    private int expected;

    public OrderTest(String  color, int expected) {
        this.color = color;
        this.expected = expected;
    }

    @Test
    public void createOrderTest() {
        OrderStep order = new OrderStep(color);
        int actual = order.getStatus();
        assertEquals("Проверка создания заказа с разными цветами",expected, actual);
    }

}

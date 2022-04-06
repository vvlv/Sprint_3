
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class OrderTrackNumberTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"GREY"},
                {"BLACK"},
                {"NO"},
                {"ALL"},
        });
    }

    private String color;
    public OrderTrackNumberTest(String color) {
        this.color = color;
    }
    @Test
    public void createOrderAndCheckTrackNumberTest() {
        OrderStep order = new OrderStep(color);
        int actual = order.loginCourierCheckMessageId();
        assertNotNull("Проверка  получения трека",actual);
    }
}


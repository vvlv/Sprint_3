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
                {"BLACK"},
                {"GREY"},
                {"BLACK_AND_GREY"},
                {"NO_COLOR"},
        });
    }

    private String color;
    public OrderTrackNumberTest(String color) {
        this.color = color;
    }
    @Test
    public void createOrderAndCheckTrackNumberTest() {
        Order order = new Order(color);
        int actual = order.getTrack();
        assertNotNull("Проверка  получения трека",actual);
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Port;

public class TestPort extends Port{
    public TestPort() {
        super();
    }
    @Test
    void constructionTest() {
        TestPort port = new TestPort();
        assertNotNull(port.getId());
        assertTrue(port.getId().length() > 0);

        List<TestPort> portList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            portList.add(new TestPort());
        }

        for (int i = 0; i < 10; i++) {
            TestPort port1 = portList.get(i);
            for (int j = i+1; j < 10; j++) {
                if(port1.getId().equals(portList.get(j).getId())) {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    void nameTest() {
        final String name = "first name";
        final String name2 = "first name";

        TestPort port = new TestPort();
        port.setName(name);

        assertEquals(name2, port.getName());
    }
}

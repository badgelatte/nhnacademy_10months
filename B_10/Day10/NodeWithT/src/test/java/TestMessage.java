import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Message;

public class TestMessage extends Message {
    @Test
    public void constructionTest() {
        TestMessage message = new TestMessage();

        assertNotNull(message.getId());
        assertNotNull(message.getId().length() > 0);

        List<TestMessage> messageList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            messageList.add(new TestMessage());
        }

        for (int i = 0; i < 10; i++) {
            TestMessage message1 = messageList.get(i);
            for (int j = i+1; j < 10; j++) {
                if(message1.getId().equals(messageList.get(j).getId())){
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void nameTest() {
        final String name = "first name";
        final String name2 = "first name";

        TestMessage message = new TestMessage();
        message.setName(name);

        assertEquals(name2, message.getName());
    }
}

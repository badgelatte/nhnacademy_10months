import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.nhnacademy.InputOutputNode;

public class TestInputOutputNode extends InputOutputNode{

    public TestInputOutputNode(int inputCount, int outputCount) {
        super(inputCount, outputCount);
    }

    @Test
    void constructorTest(){
        TestInputOutputNode node = new TestInputOutputNode(3, 3);

        assertTrue(node.getInputPortCount() >= 0);
    }
    
}

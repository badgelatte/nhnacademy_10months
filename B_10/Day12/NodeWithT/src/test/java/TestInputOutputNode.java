import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.nhnacademy.InputOutputNode;

public class TestInputOutputNode{



    @Test
    void constructorTest(){
        InputOutputNode node = new InputOutputNode(3,3){
            
        };

        assertTrue(node.getInputPortCount() >= 0);
    }
    
}

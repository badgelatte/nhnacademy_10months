import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.nhnacademy.ActiveNode;
import com.nhnacademy.Node;



 class TestActiveNode extends ActiveNode {

    public TestActiveNode() {
         super();
    }

    @Test
    void constructionTest() {
        TestActiveNode node = new TestActiveNode();

        // 만들어진게 node인가
        assertTrue(node instanceof Node);

        assertTrue(node instanceof ActiveNode);

        // 첨에 만들때는 isAlive는 false니까
        assertFalse(node.isAlive());
    }
}

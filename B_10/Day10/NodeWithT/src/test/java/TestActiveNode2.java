import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.nhnacademy.ActiveNode;
import com.nhnacademy.Node;


public class TestActiveNode2 extends ActiveNode {
    long loopCount = 0;

    public TestActiveNode2() {
        super.main();
        loopCount++;
    }

    public long getloopCount() {
        return loopCount++;
    }

    @Override
    public void main() {

    }

    @Test
    void constructionTest() {
        TestActiveNode2 node = new TestActiveNode2();

        // 만들어진게 node인가
        assertTrue(node instanceof Node);

        assertTrue(node instanceof ActiveNode);

        // 첨에 만들때는 isAlive는 false니까
        assertFalse(node.isAlive());

        // node.start();

        // await().atLeast(2, TimeUnit.SECONDS);
        // assertTrue(node.isAlive());
        // assertEquals("main", node.getStatus());
        // await().atLeast(4, TimeUnit.SECONDS);
        // node.stop();
        // // atMost - 최대 ?초 / atLeast 최소 ?초
        // await().atLeast(5, TimeUnit.SECONDS);
        // assertEquals("postprocess", node.getStatus());
        // assertFalse(node.isAlive());

        // assertTrue(node.getloopCount() > 2000);
    }
}


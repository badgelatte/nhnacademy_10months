import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nhnacademy.Node;

class TestNode extends Node{
    @Test
    void constructionTest() {
        TestNode node = new TestNode();

        // 노드가 생겼는지 알아보기 위한 문자
        assertNotNull(node.getId());   // -> reference type = null
        assertTrue(node.getId().length() > 0);

        List<TestNode> nodeList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            nodeList.add(new TestNode());
        }

        for (int i = 0; i < 10; i++) {
            TestNode node1 = nodeList.get(i);
            for (int j = i+1; j < 10; j++) {
                // reference는 equals를 써서 비교해야한다
                if(node1.getId().equals(nodeList.get(j).getId())) {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    void nameTest() {
        final String name = "first name";
        // final String name2 = "first name";           // string 하나 더 만들어서 비교하기
        StringBuilder builder = new StringBuilder();    // Stringbuilder로 비교하기
        
        TestNode node = new TestNode();
        builder.append(name);
        
        node.setName(name);
        // assertEquals(name2, node.getName());
        assertEquals(builder.toString(), node.getName());
    }

    public static void main(String[] args) {
        List<TestNode> nodeList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            nodeList.add(new TestNode());
        }

        System.out.println("Node Count : " + Node.getTotalCount());

        for (int i = 0; i < 10; i++) {
            TestNode node1 = nodeList.get(i);
            for (int j = i+1; j < 10; j++) {
                // reference는 equals를 써서 비교해야한다
                if(node1.getId().equals(nodeList.get(j).getId())) {
                    System.out.printf(String.format("%d : %s, %d : %s", i, node1.getId(), j, nodeList.get(j).getId()));
                }
                System.out.println(String.format("%d : %s, %d : %s", i, node1.getId(), j, nodeList.get(j).getId()));
            }
        }
    }
}

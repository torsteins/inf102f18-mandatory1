package no.uib.ii.inf102.f18.mandatory1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Torstein Strømme
 */
public class BSTTest {

    private BinarySearchTree<String, Integer> bst;

    @BeforeEach
    void setup() {
        this.bst = new BinarySearchTree<>();
    }

    @Test
    void sanityTest() {

        assertEquals(0, bst.size());
        bst.put("B", 4);
        bst.put("C", 5);
        bst.put("A", 3);
        assertEquals(3, bst.size());

        assertTrue(bst.containsKey("A"));
        assertTrue(bst.containsKey("B"));
        assertTrue(bst.containsKey("C"));
        assertFalse(bst.containsKey("D"));

        assertEquals((Integer) 4, bst.get("B"));


        bst.delete("A");
        assertEquals(2, bst.size());
        assertFalse(bst.containsKey("A"));
        assertNull(bst.get("A"));

        assertTrue(bst.containsKey("B"));
        assertTrue(bst.containsKey("C"));

        assertEquals((Integer) 5, bst.get("C"));

        bst.delete("D");
        assertEquals(2, bst.size());
        assertFalse(bst.containsKey("D"));

        bst.put("A", 32);
        assertEquals((Integer) 32, bst.get("A"));
        assertEquals(3, bst.size());

        String all = StreamSupport
                .stream(bst.keys().spliterator(), false)
                .collect(Collectors.joining(", "));
        assertEquals("A, B, C", all);
    }
}

package no.uib.ii.inf102.f18.mandatory1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Torstein Strømme
 */
public class GradeBSTTest {

    private BinarySearchTree<String, Integer> bst;
    private static SplittableRandom random;

    @BeforeAll
    static void beforeAll() {
        random = new SplittableRandom(0);
    }

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

    @Test
    void tinyRandomTest() {
        String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};
        shuffle(words);

        int i = 0;
        for (String w : words) {
            bst.put(w, i++);
        }

        for (int j = 0; j < words.length; j++) {
            i = bst.get(words[j]);
            assertEquals(j, i);
        }

    }

    private static void shuffle(Comparable[] arr) {
        SplittableRandom r = random;
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(arr.length-i) + i;
            swap(i, j, arr);
        }
    }

    private static void swap(int i, int j, Comparable[] arr) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

package no.uib.ii.inf102.f18.mandatory1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.SplittableRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Torstein Strømme
 */
public class IndexMinPQTest {
    private static SplittableRandom r;
    private final int MAX_N = 100_000;

    private IIndexPQ<Integer> pq;

    @BeforeAll
    static void setupAll() {
        r = new SplittableRandom(0);
    }

    @BeforeEach
    void setup() {
        this.pq = new IndexMinPQ<>(MAX_N);
    }

    @Test
    void sanityTest() {
        pq.add(0, (int) 'A');
        pq.add(3, (int) 'K');
        pq.add(6, (int) 'E');
        pq.add(2, (int) 'J');
        assertEquals(4, pq.size());
        assertEquals(0, pq.peek());
        assertEquals((Integer) (int) 'A', pq.peekKey());
        assertEquals((Integer) (int) 'K', pq.getKey(3));
        assertEquals((Integer) (int) 'A', pq.getKey(0));
        assertEquals((Integer) (int) 'J', pq.getKey(2));

        assertTrue(pq.contains(0));
        assertEquals(0, pq.poll());
        assertFalse(pq.contains(0));
        assertEquals(6, pq.poll());

        pq.changeKey(3, (int) 'B');
        assertEquals((Integer) (int) 'B', pq.peekKey());
        assertEquals(3, pq.poll());
        assertEquals((Integer) (int) 'J', pq.peekKey());
        assertEquals(2, pq.poll());
        assertEquals(0, pq.size());
    }

    @Test
    void sortTest() {
        Integer[] arr = new Integer[MAX_N];
        for (int i = 0; i < MAX_N; i++) { arr[i] = MAX_N - i - 1; }
        shuffle(arr);

        for (int num : arr) {
            pq.add(num, num);
        }

        for (int i = 0; i < MAX_N; i++) {
            assertEquals(i, pq.poll());
        }
    }

    @Test
    void sortTest2() {
        Integer[] keys = new Integer[MAX_N];
        for (int i = 0; i < MAX_N; i++) { keys[i] = i; }
        shuffle(keys);

        Integer[] order = new Integer[MAX_N];
        for (int i = 0; i < MAX_N; i++) { order[i] = MAX_N - i - 1; }
        shuffle(order);

        Pair[] pairs = new Pair[MAX_N];
        for (int i = 0; i < MAX_N; i++) {
            assertEquals(i, pq.size());
            pq.add(order[i], keys[order[i]]);
            pairs[i] = new Pair(keys[order[i]], order[i]);
        }

        assertEquals(MAX_N, pq.size());
        IterativeQuick.sort(pairs);
        for (int i = 0; i < MAX_N; i++) {
            assertEquals(MAX_N - i, pq.size());
            assertEquals(pairs[i].key, pq.peekKey());
            assertEquals(pairs[i].index, pq.poll());
        }
    }

    @Test
    void changeTestWide() {
        Integer[] keys = new Integer[MAX_N];
        for (int i = 0; i < MAX_N; i++) {
            int key = r.nextInt();
            keys[i] = key;
            pq.add(i, key);
        }

        for (int i = 0; i < 2*MAX_N; i++) {
            int key = r.nextInt();
            int idx = r.nextInt(MAX_N);
            keys[idx] = key;
            pq.changeKey(idx, key);
        }

        int prevKey = pq.peekKey();
        for (int i = 0; i < MAX_N; i++) {
            assertTrue(prevKey <= pq.peekKey());
            assertEquals(pq.getKey(pq.peek()), pq.peekKey());
            prevKey = pq.peekKey();
            assertEquals((Integer) prevKey, keys[pq.poll()]);
        }
    }

    @Test
    void changeTestNarrow() {
        Integer[] keys = new Integer[MAX_N];
        for (int i = 0; i < MAX_N; i++) {
            int key = r.nextInt(2);
            keys[i] = key;
            pq.add(i, key);
        }

        for (int i = 0; i < 2*MAX_N; i++) {
            int key = r.nextInt(2);
            int idx = r.nextInt(MAX_N);
            keys[idx] = key;
            pq.changeKey(idx, key);
        }

        int prevKey = pq.peekKey();
        for (int i = 0; i < MAX_N; i++) {
            assertTrue(prevKey <= pq.peekKey());
            assertEquals(pq.getKey(pq.peek()), pq.peekKey());
            prevKey = pq.peekKey();
            assertEquals((Integer) prevKey, keys[pq.poll()]);
        }
    }

    @Test
    void removeTest() {
        Integer[] keys = new Integer[MAX_N];
        boolean[] isin = new boolean[MAX_N];
        int size = 0;

        for (int i = 0; i < 4*MAX_N; i++) {
            int idx = r.nextInt(MAX_N);
            assertEquals(size, pq.size());
            if (!isin[idx]) {
                assertFalse(pq.contains(idx));
                int key = r.nextInt();
                keys[idx] = key;
                isin[idx] = true;
                pq.add(idx, key);
                size++;
            }
            else {
                assertTrue(pq.contains(idx));
                assertEquals(pq.getKey(idx), keys[idx]);
                pq.delete(idx);
                isin[idx] = false;
                size--;
            }
        }

        int prevKey = pq.peekKey();
        while (!pq.isEmpty()) {
            assertTrue(prevKey <= pq.peekKey());
            prevKey = pq.peekKey();
            assertEquals((Integer) prevKey, keys[pq.poll()]);
        }
    }


    /*
        Helper functions/classes
     */

    private class Pair implements Comparable<Pair> {
        private final Integer key;
        private final int index;

        Pair(Integer key, int index) {
            this.key = key;
            this.index = index;
        }

        @Override
        public int compareTo(Pair that) {
            if (this.key.compareTo(that.key) < 0) return -1;
            if (this.key.compareTo(that.key) > 0) return 1;
            return ((Integer) this.index).compareTo(that.index);
        }
    }

    private static void shuffle(Comparable[] arr) {
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

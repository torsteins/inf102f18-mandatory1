package no.uib.ii.inf102.f18.mandatory1;

import java.util.SplittableRandom; // A (much) faster random generator than java.util.Random

/**
 *  Quicksort is the preferred sorting method when stability is not a concern - such as when
 *  sorting primitives (int, long, char, double etc.). Quicksort has an expected runtime of
 *  O(n lg n), and a guaranteed runtime of O(n^2). However, the probability that the runtime is
 *  quadratic is exponentially small as a function of n. In practice, quick-sort is widely used
 *  (for instance in Arrays.sort() for primitives and in the Python and C++ standard libraries).
 */
@SuppressWarnings("unchecked")
public class Quick {
    private static final int CUTOFF = 0;

    /**
     * Sort the array using quicksort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length);
    }

    /**
     * Sort the sub-array between lowerbound and upperbound using quicksort
     *
     * @param arr array to be sorted
     * @param lb lowerbound (inclusive)
     * @param ub upperbound (exclusive)
     */
    public static void sort(Comparable[] arr, int lb, int ub) {
        shuffle(arr);
        quickSort(arr, lb, ub);
    }

    private static void quickSort(Comparable[] arr, int lb, int ub) {
        /*
        If size of array is small -- use insertion sort.
        If cutoff is 0, then this basically just returns
        without doing anything (it is already sorted)
        */
        if (lb + 1 + CUTOFF >= ub) {
            Insertion.sort(arr, lb, ub);
            return;
        }

        /*
        Tiny optimization: Find the median of three
        You may skip this part entirely - it makes it
        more likely to chose a pivot closer to the
        median. We basically swap the median of the
        three first elements into the first position.
        */
        if (ub - lb > 3) {
            int cmp01 = arr[lb].compareTo(arr[lb + 1]);
            int cmp02 = arr[lb].compareTo(arr[lb + 2]);
            if (cmp01 < 0 && cmp02 < 0) {
                if (arr[lb + 1].compareTo(arr[lb + 2]) < 0)
                    swap(lb, lb + 1, arr);
                else
                    swap(lb, lb + 2, arr);
            }
            else if (cmp01 > 0 && cmp02 > 0) {
                if (arr[lb + 1].compareTo(arr[lb + 2]) > 0)
                    swap(lb, lb + 1, arr);
                else
                    swap(lb, lb + 2, arr);
            }
        }

        Comparable pivot = arr[lb];
        int i = lb, j = ub;
        /*
        Mentally, divide array into three (four) parts based on pointers lb, i, j and ub
        At the end of every iteration of the while-loop below, maintain these invariants:
          (0)  arr[lb] contains pivot element
          (1)  arr[lb+1:i+1] values are less than or equal to pivot
          (2)  arr[i+1:j] values are unchecked
          (3)  arr[j:ub] values are at least pivot value or greater
           #   before the swap at the end of loop, j is always in range of the array,
               pointing to a value less than or equal to pivot value

        Note: arr[x:y] denotes the sub-array starting at x (inclusive) ending at y (exclusive)
        */

        while (true) {
            /*
            First while loop: Find next index i where arr[i] >= pivot (incrementing i)
            Second while loop: Find next j where arr[j] <= pivot (decrementing j)
            */
            while (++i < ub && pivot.compareTo(arr[i]) > 0);
            while (pivot.compareTo(arr[--j]) < 0); // && j > lb ); // commented check is redundant (why?)

            /*
            At this point i points to the an element >= pivot, and j points to an element <= pivot.
            We swap them; now our invariants 0-3 hold.

            However, if i >= j, then the array [lb+1:ub] has already been partitioned!
            */
            if (i >= j) break;
            swap(i, j, arr);
        }

        /*
        The array [lb+1:ub] has been partitioned, and by invariants 3 and #, we know
        that swapping the pivot into position j will yield a complete partition of [lb:ub].
        */
        swap(lb, j, arr);

        // Recursively sort the two partitions.
        quickSort(arr, lb, j);
        quickSort(arr, j+1, ub);
    }

    /**
     * Simple shuffling procedure (Fisher-Yates/Durstenfeld/Knuth)
     * Resulting permutation is chosen uniformly at random
     *
     * @param arr array to be shuffled
     */
    private static void shuffle(Comparable[] arr) {
        SplittableRandom r = new SplittableRandom();
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
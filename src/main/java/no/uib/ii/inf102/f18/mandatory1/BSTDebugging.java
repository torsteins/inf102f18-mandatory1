package no.uib.ii.inf102.f18.mandatory1;

/**
 * @author Torstein Strømme
 */
public class BSTDebugging {

    /**
     * Solve the problem uib.bstdebugging (https://uib.kattis.com/problems/uib.bstdebugging)
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        int search = io.getInt();

        // Keep track of the range of keys we expect to encounter in a valid BST search.
        // Both bounds are inclusive (in order to accommodate extremal values)
        int upperBound = Integer.MAX_VALUE;
        int lowerBound = Integer.MIN_VALUE;
        boolean isFound = false;

        for (int i = 0; i < n; i++) {
            int key = io.getInt();
            // Two things that can make the search trace invalid: the key is outside the range set
            // by earlier keys, or the search continued despite having found the search query
            if (isFound || key < lowerBound || key > upperBound) {
                System.out.println("invalid");
                System.exit(0);
            }
            if      (search < key) { upperBound = key - 1; }
            else if (search > key) { lowerBound = key + 1; }
            else                   { isFound = true; }
        }
        System.out.println("valid");
    }

}

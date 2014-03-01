package mymap;

import java.util.Random;

import mymap.MyMap;

/**
 * Testing Assignment 7 hashCode probability
 * 
 * Modified from initial version by Will Clinger
 * 
 * @author Jessica Young Schmidt jschmidt
 * @version 2014-02-25
 */
public class TestMyMapHashCode {

    /** tests run so far */
    private int totalTests = 0;
    /** errors so far */
    private int totalErrors = 0;

    /**
     * Probabilistic test for distribution of hash codes.
     */
    public void probabilisticTests() {
        System.out.println("probabilisticTests");
        probabilisticTests(400, 20);
        base = -2;
        probabilisticTests(400, 20);
        base = 412686306;
        probabilisticTests(400, 20);
    }

    /** random number generator, initialized by probabilisticTests() */
    Random rng;

    /** base for Frob hash codes */
    int base = 0;

    /**
     * random number
     */
    private void initializeRNGrandomly() {
        rng = new Random(System.nanoTime());
    }

    /**
     * Generate n random pairs of unequal MyMap<K,V> values. If k or more have
     * the same hash code, then report failure.
     * 
     * @param n
     *            number of random pairs
     * @param k
     *            number to report failure
     */
    private void probabilisticTests(int n, int k) {
        initializeRNGrandomly();
        int sameHash = 0;
        int i = 0;
        while (i < n) {
            MyMap<Frob, Frob> frob1 = randomMyMap();
            MyMap<Frob, Frob> frob2 = randomMyMap();
            if (!(frob1.equals(frob2))) {
                i = i + 1;
                if (frob1.hashCode() == frob2.hashCode()) {
                    sameHash = sameHash + 1;
                }
            }
        }
        System.out.println("Same Hash: " + sameHash + " / " + n);
        assertTrue("hashCode quality", k > sameHash);
    }

    /**
     * Returns a randomly selected MyMap<Frob,Frob>.
     */
    private MyMap<Frob, Frob> randomMyMap() {
        // First pick the size.
        double x = rng.nextDouble();
        double y = 0.5;
        int size = 0;
        // Size limited to 5 based on randomFrob values
        while (y > x && size < 5) {
            size = size + 1;
            y = y / 2.0;
        }
        MyMap<Frob, Frob> f = MyMap.empty();
        while (f.size() < size) {
            f = f.include(randomFrob(), randomFrob());
        }
        return f;
    }

    /**
     * Returns a randomly selected Frob.
     */
    private Frob randomFrob() {
        int h = base + rng.nextInt(5);
        return new Frob(h);
    }

    /**
     * Frob
     */
    private static class Frob {
        /** hash */
        int theHash;

        /**
         * Constructor
         * 
         * @param h
         *            int
         */
        Frob(int h) {
            theHash = h;
        }

        /**
         * hashCode for Frob
         * 
         * @return hashCode
         */
        @Override
        public int hashCode() {
            return theHash;
        }

        /**
         * @param o
         *            Object to compare to this
         * 
         * @return whether this and o are equal
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof Frob) {
                Frob that = (Frob) o;
                return (theHash == that.getTheHash());
            }
            return false;
        }

        /**
         * @return the value of theHash field
         */
        int getTheHash() {
            return theHash;
        }
    }

    /**
     * main test from Clinger
     */
    public static void main(String[] args) {
        TestMyMapHashCode test = new TestMyMapHashCode();

        test.probabilisticTests();

        test.summarize();
    }

    /**
     * Prints failure report if the result is not true.
     * 
     * @param name
     *            name of test
     * @param result
     *            result to test
     */
    private void assertTrue(String name, boolean result) {
        if (!result) {
            System.out.println();
            System.out.println("***** Test failed ***** " + name
                    + ": " + totalTests);
            totalErrors = totalErrors + 1;
        }
        else {
            System.out.println("---- Test passed ---- " + name + ": "
                    + totalTests);
        }

        totalTests = totalTests + 1;
    }

    /**
     * Prints a summary of the tests.
     */
    private void summarize() {
        System.out.println();
        System.out.println(totalErrors + " errors found in "
                + totalTests + " tests.");
    }

}
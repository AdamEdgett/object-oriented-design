package mymap;

import java.util.Comparator;
import java.util.Iterator;

import mymap.MyMap;

/**
 * Testing performance of MyMap for A05: Binary Search Tree (BST) implementation
 * 
 * Modified from initial version by Will Clinger
 * 
 * @author Jessica Young Schmidt jschmidt
 */
public class TestMyMapPerformance {

    /**
     * Runs the tests
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String args[]) {
        /** instance of TestMyMapA07Performance */
        TestMyMapPerformance test = new TestMyMapPerformance();

        try {
            test.performance();
        }
        catch (ClassCastException e) {
            System.out
                    .println("Exception thrown during performance tests:");
            System.out.println(e);
            test.assertTrue(
                    "performance - Exception thrown during performance tests",
                    false);
        }

        test.summarize();
    }

    /** tests run so far */
    private int totalTests = 0;
    /** errors so far */
    private int totalErrors = 0;

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
     * Prints failure report if the result is not false.
     * 
     * @param name
     *            name of test
     * @param result
     *            result to test
     */
    private void assertFalse(String name, boolean result) {
        assertTrue(name, !result);
    }

    /**
     * Prints a summary of the tests.
     */
    private void summarize() {
        System.out.println();
        System.out.println(totalErrors + " errors found in "
                + totalTests + " tests.");
    }

    /**
     * Tests the asymptotic amortized performance for the average case of five
     * public operations.
     * 
     * Strategy:
     * 
     * For each operation, double the size and/or iteration parameters until the
     * benchmark takes at least half a second to run. Run the benchmark to make
     * sure. Multiply the size by 4 and run the benchmark again. Compare with
     * the expected increase in timing.
     */
    private static abstract class Benchmark {
        /** size of a benchmark that takes at least 1 second */
        int n;
        /** number of iterations for that benchmark */
        long iterations;
        /** time (in milliseconds) for that benchmark */
        long t1n;
        /** time for that same benchmark with size 4n */
        long t4n;

        /** size of smallest benchmark to be tried */
        int n0;
        /** number of iterations for smallest benchmark */
        long iterations0;

        /** milliseconds per second */
        static final long SECOND = 1000;

        /**
         * Uses n0 and iterations0 to compute n, iterations, t1n, t4n.
         */
        boolean run() {
            n = n0;
            iterations = iterations0;
            t1n = 0;
            while (t1n < SECOND / 4) {
                t1n = run(n, iterations);
                if (t1n >= SECOND / 4) {
                    t1n = run(n, iterations);
                }
                else {
                    n = 2 * n;
                    iterations = 2 * iterations;
                }
            }
            t4n = run(4 * n, iterations);
            return compareToExpected();
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        abstract long run(int n, long iters);

        /**
         * Uses n, t1n, and t4n to determine whether the asymptotic performance
         * is acceptable.
         */
        abstract boolean compareToExpected();

        /**
         * Helper method - for lg
         */
        double lg(double x) {
            return Math.log(x) / Math.log(2.0);
        }
    }

    /**
     * Timing m.include(k,v)
     */
    private static class TimeInclude extends Benchmark {
        /** MyMap for testing */
        private final MyMap<Foo, Double> m0;

        TimeInclude(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j + n), (double) j + n);
            }
            MyMap<Foo, Double> m1 = m;
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                m = m1;
                for (int j = 0; j < n; j = j + 1) {
                    m = m.include(new Foo(j), (double) j);
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(lg n) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.include(k,v) benchmark ("
                    + iterations + " iterations): O(lg n)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            double lgn = lg(n);
            double lg4n = lg((double) 4 * n);
            double slope = 1.5;
            return (t4n) < slope * 4.0 * (lg4n / lgn) * (t1n);
        }
    }

    /**
     * Timing m.isEmpty().
     */
    private static class TimeIsEmpty extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeIsEmpty(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (m.isEmpty()) {
                    throw new RuntimeException(
                            "incorrect isEmpty() method");
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(1) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.isEmpty() benchmark (" + iterations
                    + " iterations): O(1)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            return (t4n) < 1.5 * (t1n);
        }
    }

    /**
     * Timing m.size().
     */
    private static class TimeSize extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeSize(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (m.size() == 0) {
                    throw new RuntimeException(
                            "incorrect size() method");
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(1) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.size() benchmark (" + iterations
                    + " iterations): O(1)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            return (t4n) < 1.5 * (t1n);
        }
    }

    /**
     * Timing m.containsKey(k)
     */
    private static class TimeContainsKey extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeContainsKey(MyMap<Foo, Double> m0, int n0,
                long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            Foo f0 = new Foo(0);
            MyMap<Foo, Double> m = m0.include(f0, 0.0);
            for (int j = 1; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (!m.containsKey(new Foo(n - 1))) {
                    // if (!m.containsKey(f0)) {
                    throw new RuntimeException(
                            "incorrect containsKey() method");
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(lg n) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.containsKey(k) benchmark ("
                    + iterations + " iterations): O(lg n)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            // System.out.println(Foo.counter);
            double lgn = lg(n);
            double lg4n = lg((double) 4 * n);
            return (t4n) < 1.25 * (lg4n / lgn) * (t1n);
        }
    }

    /**
     * Timing m.get(k)
     */
    private static class TimeGet extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeGet(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            Foo f0 = new Foo(0);
            MyMap<Foo, Double> m = m0.include(f0, 0.0);
            for (int j = 1; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (m.get(new Foo(n - 1)) != (n - 1)) {
                    // if (m.get(f0) != 0.0) {
                    throw new RuntimeException(
                            "incorrect get() method");
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(lg n) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.get(k) benchmark (" + iterations
                    + " iterations): O(lg n)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            // System.out.println(Foo.counter);
            double lgn = lg(n);
            double lg4n = lg((double) 4 * n);
            return (t4n) < 1.25 * (lg4n / lgn) * (t1n);
        }
    }

    /**
     * Timing m.set(k)
     */
    private static class TimeSet extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeSet(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j + n), (double) j + n);
            }
            MyMap<Foo, Double> m1 = m;
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                m = m1;
                m = m.set(new Foo(n), (double) i);
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(lg n) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.set(k,v) benchmark (" + iterations
                    + " iterations): O(lg n)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            double lgn = lg(n);
            double lg4n = lg((double) 4 * n);
            double slope = 1.25;
            return (t4n) < slope * (lg4n / lgn) * (t1n);
        }
    }

    /**
     * Timing m.iterator().
     */
    private static class TimeIterator extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeIterator(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                Iterator<Foo> it = m.iterator();
                if (!(it.hasNext())) {
                    throw new RuntimeException(
                            "incorrect hasNext() method");
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(n) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("m.iterator() benchmark ("
                    + iterations + " iterations): O(n)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            return (t4n) < 6 * ((double) t1n);
        }
    }

    /**
     * Timing it.hasNext().
     */
    private static class TimeHasNext extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeHasNext(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            Iterator<Foo> it = m.iterator();
            Foo whatever = null;
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (it.hasNext()) {
                    whatever = it.next();
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(1) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("it.hasNext() benchmark ("
                    + iterations + " iterations): O(1)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            return (t4n) < 1.5 * (t1n);
        }
    }

    /**
     * Timing it.next().
     */
    private static class TimeNext extends Benchmark {

        private final MyMap<Foo, Double> m0;

        TimeNext(MyMap<Foo, Double> m0, int n0, long iterations0) {
            this.m0 = m0;
            this.n0 = n0;
            this.iterations0 = iterations0;
        }

        /**
         * Returns the time (in milliseconds) to run this Benchmark with size n
         * and iters iterations.
         */
        @Override
        long run(int n, long iters) {
            MyMap<Foo, Double> m = m0;
            for (int j = 0; j < n; j = j + 1) {
                m = m.include(new Foo(j), (double) j);
            }
            long callToNext = iters;
            if (callToNext > n) {
                callToNext = n;
            }
            Iterator<Foo> it = m.iterator();
            Foo whatever = null;
            long tStart = System.currentTimeMillis();
            for (long i = 0; i < iters; i = i + 1) {
                if (i < n) {
                    if (it.hasNext()) {
                        whatever = it.next();
                    }
                    else {
                        throw new RuntimeException(
                                "incorrect hasNext() method");
                    }
                }
                else {
                    if (it.hasNext()) {
                        throw new RuntimeException(
                                "incorrect hasNext() method - over n");
                    }
                }
            }
            long tFinish = System.currentTimeMillis();
            return tFinish - tStart;
        }

        /**
         * Should run in O(1) time.
         */
        @Override
        boolean compareToExpected() {
            System.out.println();
            System.out.println("it.next() benchmark (" + iterations
                    + " iterations): O(1)");
            System.out.println("    n=" + n + " in " + t1n + "ms");
            System.out.println("    n=" + (4 * n) + " in " + t4n
                    + "ms");
            return (t4n) < 1.5 * (t1n);
        }
    }

    private static class Foo {
        /** primary state of Foo; determines equality of Foo objects */
        long j;
        /** low order bits of j; used by FooComparator */
        long j0;
        /** high order bits of j; used by FooComparator */
        long j1;
        /** some standard permutation of the bits of j */
        long k;

        // k is used by RandomFooComparator; for the worst case, j == k

        // true means generate a worst case.
        // false means generate an average case.
        //
        // Warning!
        // Changing the value of Foo.worstCase invalidates all extant
        // Foo objects (because instances of RandomFooComparator no
        // longer compare old objects correctly)

        static boolean worstCase = false;

        public static long counter = 0; // to disable compiler optimizations

        Foo(int j) {
            this.j = j;
            this.j0 = j % 1024;
            this.j1 = j / 1024;
            this.k = randomize(j);
            // System.out.println ("Foo " + this.k);
            counter = counter + 1;
        }

        /**
         * Perfect hash function on int values.
         */
        private static long randomize(int j) {
            if (worstCase) {
                return j;
            }
            long jj = j;
            jj = jj & 0xffffffff; // convert to unsigned
            long j0 = jj % p; // chop to pieces: j0, j1, j2, j3, j4
            jj = jj / p;
            long j1 = jj % p;
            jj = jj / p;
            long j2 = jj % p;
            jj = jj / p;
            long j3 = jj % p;
            jj = jj / p;
            long j4 = jj % p;
            jj = jj / p;
            assert jj == 0;
            /*
             * j0 = (m * j0 + a) % p; // scramble each piece j1 = (m * j1 + a) %
             * p; j2 = (m * j2 + a) % p; j3 = (m * j3 + a) % p; j4 = (m * j4 +
             * a) % p;
             */
            j0 = j0 ^ 101; // FIXME
            jj = jj + j0; // assemble the pieces in reverse order
            jj = p * jj;
            jj = jj + j1;
            jj = p * jj;
            jj = jj + j2;
            jj = p * jj;
            jj = jj + j3;
            jj = p * jj;
            jj = jj + j4;
            return jj;
        }

        private static final int p = 1001; // some convenient prime
        private static final int m = 731; // some convenient multiplier
        private static final int a = 678; // some convenient constant

        @Override
        public String toString() {
            return "Foo(" + j + ", " + k + ")";
        }

        @Override
        public boolean equals(Object x) {
            if (x instanceof Foo) {
                Foo f = (Foo) x;
                return j == f.j;
            }
            else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (int) j;
        }
    }

    private static class FooComparator implements Comparator<Foo> {
        @Override
        public int compare(Foo f1, Foo f2) {
            long f1j1 = f1.j1;
            long f2j1 = f2.j1;
            long f1j0 = f1.j0;
            long f2j0 = f2.j0;
            if (f1j1 < f2j1) {
                return -1;
            }
            else
                if (f1j1 == f2j1) {
                    if (f1j0 < f2j0) {
                        return -1;
                    }
                    else
                        if (f1j0 == f2j0) {
                            return 0;
                        }
                        else {
                            return +1;
                        }
                }
                else {
                    return +1;
                }
        }
    }

    private static class RandomFooComparator implements
            Comparator<Foo> {
        @Override
        public int compare(Foo f1, Foo f2) {
            long direction = f1.k - f2.k;
            // System.out.println ("compare(Foo,Foo) " + direction);
            if (direction < 0) {
                return -1;
            }
            else
                if (direction == 0) {
                    return 0;
                }
                else {
                    return +1;
                }
        }
    }

    // private static Comparator<Foo> fooComparator = new FooComparator();

    private static Comparator<Foo> fooComparator = new RandomFooComparator();

    private void performance() {
        System.out.println();
        System.out.println("Timing public operations...");
        MyMap<Foo, Double> f0 = MyMap.empty();
        MyMap<Foo, Double> f0c = MyMap.empty(fooComparator);

        /**
         * Tests that use 0-argument emptyMap() can be enabled by changing false
         * to true in the next line.
         */
        if (false) {
            assertTrue("include(k,v) is O(lg n)", new TimeInclude(f0,
                    1024, 1024).run());
            assertTrue("isEmpty() is O(1)", new TimeIsEmpty(f0, 1,
                    1024 * 1024).run());
            assertTrue("size() is O(1)", new TimeSize(f0, 1,
                    1024 * 1024).run());
            assertTrue("containsKey(k) is O(lg n)",
                    new TimeContainsKey(f0, 1, 1024 * 1024).run());
            assertTrue("get(k) is O(lg n)", new TimeGet(f0, 1,
                    1024 * 1024).run());
            assertTrue("set(k,v) is O(lg n)", new TimeSet(f0, 1,
                    1024 * 1024).run());
            assertTrue("iterator() is O(n)", new TimeIterator(f0, 64,
                    32).run());
            assertTrue("hasNext() is O(1)", new TimeHasNext(f0, 64,
                    64 * 1024 * 1024).run());
            assertTrue("next() is O(1)", new TimeNext(f0, 64,
                    64 * 1024 * 1024).run());
        }

        System.out.println("\nAverage case:");

        assertTrue("include(k,v) is O(lg n)", new TimeInclude(f0c,
                1024, 1024).run());
        assertTrue("isEmpty() is O(1)", new TimeIsEmpty(f0c, 1,
                1024 * 1024).run());
        assertTrue("size() is O(1)",
                new TimeSize(f0c, 1, 1024 * 1024).run());
        assertTrue("containsKey(k) is O(lg n)", new TimeContainsKey(
                f0c, 1, 1024 * 1024).run());
        assertTrue("get(k) is O(lg n)", new TimeGet(f0c, 1,
                1024 * 1024).run());
        assertTrue("set(k,v) is O(lg n)", new TimeSet(f0c, 1,
                1024 * 1024).run());
        assertTrue("iterator() is O(n)",
                new TimeIterator(f0c, 64, 32).run());
        assertTrue("hasNext() is O(1)", new TimeHasNext(f0c, 64,
                64 * 1024 * 1024).run());
        assertTrue("next() is O(1)", new TimeNext(f0, 64,
                64 * 1024 * 1024).run());

        System.out.println("\nWorst case:");

        Foo.worstCase = true; // invalidates all Foo objects

        assertTrue("include(k,v) is O(lg n)", new TimeInclude(f0c,
                1024, 1024).run());
        assertTrue("isEmpty() is O(1)", new TimeIsEmpty(f0c, 1,
                1024 * 1024).run());
        assertTrue("size() is O(1)",
                new TimeSize(f0c, 1, 1024 * 1024).run());
        assertTrue("containsKey(k) is O(lg n)", new TimeContainsKey(
                f0c, 1, 1024 * 1024).run());
        assertTrue("get(k) is O(lg n)", new TimeGet(f0c, 1,
                1024 * 1024).run());
        assertTrue("set(k,v) is O(lg n)", new TimeSet(f0c, 1,
                1024 * 1024).run());
        assertTrue("iterator() is O(n)",
                new TimeIterator(f0c, 64, 32).run());
        assertTrue("hasNext() is O(1)", new TimeHasNext(f0c, 64,
                64 * 1024 * 1024).run());
        assertTrue("next() is O(1)", new TimeNext(f0, 64,
                64 * 1024 * 1024).run());
    }

}

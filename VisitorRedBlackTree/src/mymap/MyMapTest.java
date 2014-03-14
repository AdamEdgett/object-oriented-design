package mymap;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Tests MyMap
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 */
public class MyMapTest {
    private MyMap<String, Integer> empty;
    private MyMap<String, Integer> map1;
    private MyMap<String, Integer> map2;
    private MyMap<String, Integer> map2copy;
    private MyMap<String, Integer> map3;
    private MyMap<String, Integer> map4;
    private MyMap<String, Integer> map5;
    private MyMap<String, Integer> map5plus;


    /**
     * Does initial setup before running tests
     */
    @Before
    public void setUp() {
        empty = MyMap.empty();
        map1 = empty.include("one", 1);
        map2 = map1.include("two", 2);
        map2copy = map1.include("two", 2);
        map3 = map1.include("two", 3);
        map4 = map3.include("three", 4);
        map4 = map4.include("three", 3);
        map5 = map3.include("three", 3);
        map5plus = empty.include("one", 2);
        map5plus = map5plus.include("two", 4);
        map5plus = map5plus.include("three", 4);
    }

    /**
     * Tests MyMap.isEmpty()
     * @throws Exception
     */
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(empty.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse(map2.isEmpty());
    }

    /**
     * Tests MyMap.size()
     * @throws Exception
     */
    @Test
    public void testSize() throws Exception {
        assertEquals(empty.size(), 0);
        assertEquals(map1.size(), 1);
        assertEquals(map2.size(), 2);
        assertEquals(map4.size(), 3);
    }

    /**
     * Tests MyMap.containsKey()
     * @throws Exception
     */
    @Test
    public void testContainsKey() throws Exception {
        assertFalse(empty.containsKey("one"));
        assertTrue(map1.containsKey("one"));
        assertTrue(map2.containsKey("one"));
        assertTrue(map2.containsKey("two"));
        assertFalse(map1.containsKey("two"));
    }

    /**
     * Tests MyMap.get()
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        assertEquals(map1.get("one"), new Integer(1));
        assertEquals(map1.get("one"), map2.get("one"));
        assertEquals(map2.get("two"), new Integer(2));
        try {
            empty.get("one");
        }
        catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests MyMap.set()
     * @throws Exception
     */
    @Test
    public void testSet() throws Exception {
        assertTrue(map3.set("two", 2).equals(map2));
        assertEquals(map1.set("one", 12).get("one"), new Integer(12));
        assertTrue(empty.set("one", 1).equals(map1));
        assertEquals(map4.set("one", 11).get("one"), new Integer(11));
    }

    /**
     * Test MyMap.toString()
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals(empty.toString(), "{...(0 key(s) mapped to value(s))...}");
        assertEquals(map1.toString(), "{...(1 key(s) mapped to value(s))...}");
        assertEquals(map2.toString(), "{...(2 key(s) mapped to value(s))...}");
    }

    /**
     * Tests MyMap.equals()
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {
        assertTrue(empty.equals(empty));
        assertFalse(empty.equals(null));
        assertFalse(empty.equals("empty"));
        assertFalse(empty.equals(map1));
        assertTrue(map1.equals(map1));
        assertTrue(map2.equals(map2copy));
        assertFalse(map2.equals(map3));
        assertFalse(map3.equals(map2));
        assertTrue(map5.equals(map4));
    }

    /**
     * Tests MyMap.hashCode()
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals(empty.hashCode(), 0);
        assertNotSame(map1.hashCode(), map2.hashCode());
        assertEquals(map2.hashCode(), map2copy.hashCode());
        assertEquals(map4.hashCode(), map5.hashCode());
    }

    /**
     * Tests MyMap.iterator()
     * @throws Exception
     */
    @Test
    public void testIterator() throws Exception {
        Iterator<String> map1Iterator = map1.iterator();
        Iterator<String> emptyIterator = empty.iterator();
        assertTrue(map1Iterator.hasNext());
        assertEquals(map1Iterator.next(), "one");
        try {
            map1Iterator.remove();
        }
        catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }
        try {
            emptyIterator.next();
        }
        catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        Iterator<String> compIterator = map2.iterator(new TestComparator());
        compIterator.hasNext();
    }

    /**
     * Tests accept and acceptRBT
     */
    @Test
    @SuppressWarnings(value = "unchecked")
    public void testAccept() throws Exception {
        TestMyMapVisitor testMyMapVisitor = new TestMyMapVisitor();
        assertEquals(((MyMap) empty).accept(testMyMapVisitor), empty);
        assertEquals(((MyMap) map2).accept(testMyMapVisitor),
                ((MyMap) map2copy).accept(testMyMapVisitor));
        assertEquals(((MyMap) map5).accept(testMyMapVisitor),
                map5plus);

        TestRBTVisitor<String, Integer> testRBTVisitor = new TestRBTVisitor
                <String, Integer>();
        try {
            empty.acceptRBT(testRBTVisitor);
        }
        catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }
        try {
            map2.acceptRBT(testRBTVisitor);
        }
        catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test MyMapVisitor
     * adds 1 to each value
     */
    private class TestMyMapVisitor implements MyMapVisitor<String, Integer> {
        /**
         * @param k
         *            given key
         * @param v
         *            given value
         * @return a suitable new value
         */
        public Integer visit(String k, Integer v) {
            return v + 1;
        }
    }

    /**
     * Test RBTVisitor
     * Returns size
     */
    private class TestRBTVisitor<K, V> implements RBTVisitor<K, V, Integer> {
        /**
         * The method for the empty tree
         *
         * @param comp
         *            the Comparator for the whole tree
         * @param color
         *            the color of the node, which should be "RED" or "BLACK"
         * @return some value of the type R
         */
        public Integer visitEmpty(Comparator<? super K> comp, String color) {
            return 0;
        }

        /**
         * The method for the node of the tree
         *
         * @param comp
         *            the Comparator for the whole tree
         * @param color
         *            the color of the node, which should be "RED" or "BLACK"
         * @param k
         *            the key for the node
         * @param v
         *            the value for the node
         * @param left
         *            the left subtree of the node
         * @param right
         *            the right subtree of the node
         * @return some value of the type R
         */
        public Integer visitNode(Comparator<? super K> comp, String color,
                                 K k, V v,
                                 MyMap<K, V> left, MyMap<K, V> right) {
            return 1 + left.acceptRBT(this) + right.acceptRBT(this);
        }
    }

    /**
     * Test comparator
     */
    private class TestComparator implements Comparator<String> {
        /**
         * Compares two strings
         * @param str1
         * @param str2
         * @return the comparison result
         */
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Tests MyTreeMap
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/11/14
 */
public class MyTreeMapTest {
    private MyMap<String, Integer> empty;
    private MyMap<String, Integer> map1;
    private MyMap<String, Integer> map2;
    private MyMap<String, Integer> map2copy;
    private MyMap<String, Integer> map3;
    private MyMap<String, Integer> map4;
    private MyMap<String, Integer> map5;
    private MyMap<String, Integer> map6;


    /**
     * Does initial setup before running tests
     */
    @Before
    public void setUp() {
        empty = MyMap.empty(new TestComparator());
        map1 = empty.include("one", 1);
        map2 = map1.include("two", 2);
        map2copy = map1.include("two", 2);
        map3 = map1.include("two", 3);
        map4 = map3.include("three", 4);
        map4 = map4.include("three", 3);
        map5 = map3.include("three", 3);
        map6 = empty.include("four", 4);
        map6 = map6.include("eight", 8);
        map6 = map6.include("seven", 7);
    }

    /**
     * Tests MyTreeMap.isEmpty()
     * @throws Exception
     */
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(empty.isEmpty());
        assertFalse(map1.isEmpty());
        assertFalse(map2.isEmpty());
    }

    /**
     * Tests MyTreeMap.size()
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
     * Tests MyTreeMap.containsKey()
     * @throws Exception
     */
    @Test
    public void testContainsKey() throws Exception {
        assertFalse(empty.containsKey("one"));
        assertTrue(map1.containsKey("one"));
        assertTrue(map2.containsKey("one"));
        assertTrue(map2.containsKey("two"));
        assertFalse(map1.containsKey("two"));
        assertTrue(map6.containsKey("eight"));
    }

    /**
     * Tests MyTreeMap.get()
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        assertEquals(map1.get("one"), new Integer(1));
        assertEquals(map1.get("one"), map2.get("one"));
        assertEquals(map2.get("two"), new Integer(2));
        assertEquals(map6.get("eight"), new Integer(8));
        try {
            empty.get("one");
        }
        catch (RuntimeException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests MyTreeMap.set()
     * @throws Exception
     */
    @Test
    public void testSet() throws Exception {
        assertTrue(map3.set("two", 2).equals(map2));
        assertEquals(map1.set("one", 12).get("one"), new Integer(12));
        assertTrue(empty.set("one", 1).equals(map1));
        assertEquals(map4.set("one", 11).get("one"), new Integer(11));
        assertEquals(map6.set("eight", 6).get("eight"), new Integer(6));
    }

    /**
     * Test MyTreeMap.toString()
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals(empty.toString(), "{...(0 key(s) mapped to value(s))...}");
        assertEquals(map1.toString(), "{...(1 key(s) mapped to value(s))...}");
        assertEquals(map2.toString(), "{...(2 key(s) mapped to value(s))...}");
        assertEquals(map6.toString(), "{...(3 key(s) mapped to value(s))...}");
    }

    /**
     * Tests MyTreeMap.equals()
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
     * Tests MyTreeMap.hashCode()
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals(empty.hashCode(), 0);
        assertNotSame(map1.hashCode(), map2.hashCode());
        assertEquals(map2.hashCode(), map2copy.hashCode());
        assertEquals(map4.hashCode(), map5.hashCode());
        assertNotNull(map6.hashCode());
    }

    /**
     * Tests MyTreeMap.iterator()
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
            if (str1.compareTo(str2) < 0) {
                return -1;
            }
            else if (str1.compareTo(str2) == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }
}

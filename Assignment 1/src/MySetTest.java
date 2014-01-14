import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tester class for the MySet class
 * Tests functionality of MySet
 *
 * @author Adam Edgett
 * @version 1/14/14.
 */
public class MySetTest {
    private MySet empty;
    private MySet set1;
    private MySet set2;
    private MySet set2copy;
    private MySet set3;
    private MySet set4;
    private MySet set5;

    /**
     * Does initial setup before running the tests
     */
    @Before
    public void setUp() {
        empty = MySet.empty();

        set1 = MySet.insert(MySet.empty(), new Long(1));

        set2 = MySet.insert(MySet.empty(), new Long(4));
        set2 = MySet.insert(set2, new Long(1));
        set2 = MySet.insert(set2, new Long(6));

        set2copy = MySet.insert(MySet.empty(), new Long(4));
        set2copy = MySet.insert(set2copy, new Long(1));
        set2copy = MySet.insert(set2copy, new Long(6));

        set3 = MySet.insert(MySet.empty(), new Long(5));
        set3 = MySet.insert(set3, new Long(3));

        set4 = MySet.insert(MySet.empty(), new Long(1));
        set4 = MySet.insert(set4, new Long(3));
        set4 = MySet.insert(set4, new Long(5));

        set5 = MySet.insert(MySet.empty(), new Long(1));
        set5 = MySet.insert(set5, new Long(5));

    }

    /**
     * Tests MySet.insert()
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        assertTrue(MySet.insert(set1, new Long(5)).equals(set5));
        assertTrue(MySet.insert(set1, new Long(1)).equals(set1));
    }

    /**
     * Tests MySet.size()
     * @throws Exception
     */
    @Test
    public void testSize() throws Exception {
        assertEquals(MySet.size(empty), 0);
        assertEquals(MySet.size(set1), 1);
        assertEquals(MySet.size(set2), 3);
    }

    /**
     * Tests MySet.isEmpty()
     * @throws Exception
     */
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(MySet.isEmpty(empty));
        assertFalse(MySet.isEmpty(set1));
        assertFalse(MySet.isEmpty(set2));
    }

    /**
     * Tests MySet.contains()
     * @throws Exception
     */
    @Test
    public void testContains() throws Exception {
        assertFalse(MySet.contains(empty, new Long(5)));

        assertTrue(MySet.contains(set1, new Long(1)));

        assertTrue(MySet.contains(set2, new Long(1)));
        assertTrue(MySet.contains(set2, new Long(4)));
        assertTrue(MySet.contains(set2, new Long(6)));
    }

    /**
     * Tests MySet.isSubset()
     * @throws Exception
     */
    @Test
    public void testIsSubset() throws Exception {
        assertTrue(MySet.isSubset(empty, empty));
        assertTrue(MySet.isSubset(empty, set1));
        assertTrue(MySet.isSubset(set1, set2));
        assertFalse(MySet.isSubset(set2, set1));
    }

    /**
     * Tests MySet.remove()
     * @throws Exception
     */
    @Test
    public void testRemove() throws Exception {
        assertEquals(MySet.remove(empty, new Long(1)), MySet.empty());
        assertEquals(MySet.size(MySet.remove(set2, new Long(4))), 2);
    }

    /**
     * Tests MySet.join()
     * @throws Exception
     */
    @Test
    public void testJoin() throws Exception {
        assertTrue(MySet.join(empty, empty).equals(MySet.empty()));
        assertTrue(MySet.join(empty, set1).equals(set1));
        assertTrue(MySet.join(set1, set2).equals(set2));
        assertTrue(MySet.join(set1, set3).equals(set4));
    }

    /**
     * Tests MySet.intersect()
     * @throws Exception
     */
    @Test
    public void testIntersect() throws Exception {
        assertTrue(MySet.intersect(empty, empty).equals(MySet.empty()));
        assertTrue(MySet.intersect(set1, empty).equals(MySet.empty()));
        assertTrue(MySet.intersect(set1, set1).equals(set1));
        assertTrue(MySet.intersect(set2, set4).equals(set1));
    }

    /**
     * Tests MySet.replace()
     * @throws Exception
     */
    @Test
    public void testReplace() throws Exception {
        assertTrue(MySet.replace(empty, new Long(1), new Long(2))
                .equals(MySet.empty()));
        assertTrue(MySet.replace(set3, new Long(3), new Long(1))
                .equals(set5));
        MySet set4copy = MySet.replace(set2, new Long(4), new Long(3));
        set4copy = MySet.replace(set4copy, new Long(6), new Long(5));
        assertEquals(set4copy, set4);
    }

    /**
     * Tests MySet.toString()
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        assertEquals(empty.toString(),
                "{...(This set contains 0 elements)...}");
        assertEquals(set1.toString(),
                "{...(This set contains 1 elements)...}");
        assertEquals(set4.toString(),
                "{...(This set contains 3 elements)...}");
    }

    /**
     * Tests MySet.equals()
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {
        assertTrue(empty.equals(empty));
        assertTrue(set1.equals(set1));
        assertTrue(set2.equals(set2copy));
        assertFalse(set3.equals(null));
        assertFalse(set4.equals("string"));
    }

    /**
     * Tests MySet.hashCode()
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals(empty.hashCode(), empty.hashCode());
        assertEquals(set1.hashCode(), set1.hashCode());
        assertEquals(set2.hashCode(), set2copy.hashCode());
    }
}

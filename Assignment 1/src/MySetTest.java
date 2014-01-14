import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Adam on 1/13/14.
 */
public class MySetTest {
    @Test
    public void testEmpty() throws Exception {
        MySet empty = MySet.empty();
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testInsert() throws Exception {

    }

    @Test
    public void testSize() throws Exception {
        MySet set = MySet.empty();
        assertEquals(MySet.size(set), 0);

        set = MySet.insert(set, new Long(1));
        assertEquals(MySet.size(set), 1);

        set = MySet.insert(set, new Long(12));
        set = MySet.insert(set, new Long(8));
        assertEquals(MySet.size(set), 3);
    }

    @Test
    public void testIsEmpty() throws Exception {
        MySet set = MySet.empty();
        assertTrue(MySet.isEmpty(set));

        set = MySet.insert(set, new Long(5));
        assertFalse(MySet.isEmpty(set));

        set = MySet.remove(set, new Long(5));
        assertTrue(MySet.isEmpty(set));
    }

    @Test
    public void testContains() throws Exception {
        MySet set = MySet.empty();
        assertFalse(MySet.contains(set, new Long(5)));

        set = MySet.insert(set, new Long(5));
        assertTrue(MySet.contains(set, new Long(5)));

        set = MySet.insert(MySet.insert(set, new Long(1)), new Long(3));
        set = MySet.insert(set, new Long(12));
        assertTrue(MySet.contains(set, new Long(12)));
        assertTrue(MySet.contains(set, new Long(3)));
        assertTrue(MySet.contains(set, new Long(1)));
    }

    @Test
    public void testIsSubset() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testJoin() throws Exception {

    }

    @Test
    public void testIntersect() throws Exception {

    }

    @Test
    public void testReplace() throws Exception {

    }
}

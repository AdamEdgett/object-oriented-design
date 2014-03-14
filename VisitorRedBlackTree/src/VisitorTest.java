import mymap.MyMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Tests visitor implementations
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 */
public class VisitorTest {
    private MyMap<Integer, Integer> emptyGcdTreeMap;
    private MyMap<Integer, Integer> gcdTreeMap;
    private MyMap<Integer, Integer> emptyGcdMap;
    private MyMap<Integer, Integer> gcdMap;

    private MyMap<Integer, String> emptyTree;
    private MyMap<Integer, String> map1;
    private MyMap<Integer, String> map2;
    private MyMap<Integer, String> map3;
    private MyMap<Integer, String> treeMap3;


    // Visitors
    private GCD gcd = new GCD();
    private StringWithNumber stringWithNumber = new StringWithNumber();
    private PathLengths<Integer, String> pathLengths
        = new PathLengths<Integer, String>();
    private BlackHeight<Integer, String> blackHeight
        = new BlackHeight<Integer, String>();

    /**
     * Does initial setup before running tests
     */
    @Before
    public void setUp() {
        emptyGcdTreeMap = MyMap.empty(new IntComparator());
        gcdTreeMap = emptyGcdTreeMap.include(3, 9);
        gcdTreeMap = gcdTreeMap.include(4, 8);
        gcdTreeMap = gcdTreeMap.include(2, 9);

        emptyGcdMap = MyMap.empty();
        gcdMap = emptyGcdMap.include(3, 9);
        gcdMap = gcdMap.include(4, 8);
        gcdMap = gcdMap.include(2, 9);

        map1 = MyMap.empty();
        map1 = map1.include(1, "one");
        map2 = map1.include(2, "two");
        map3 = map2.include(3, "three");
        emptyTree = MyMap.empty(new IntComparator());
        treeMap3 = emptyTree.include(1, "one");
        treeMap3 = treeMap3.include(2, "two");
        treeMap3 = treeMap3.include(3, "three");
    }

    /**
     * Tests GCD
     * @throws Exception
     */
    @Test
    public void testGCD() throws Exception {
        MyMap<Integer, Integer> resultMyMap = MyMap.empty(new IntComparator());
        resultMyMap = resultMyMap.include(3, 3);
        resultMyMap = resultMyMap.include(4, 4);
        resultMyMap = resultMyMap.include(2, 1);
        assertEquals(emptyGcdTreeMap.accept(gcd), emptyGcdTreeMap);
        assertEquals(gcdTreeMap.accept(gcd), resultMyMap);
        assertSame(gcdTreeMap.accept(gcd).get(4), 4);

        assertTrue(emptyGcdMap.accept(gcd).equals(emptyGcdMap));
        assertSame(gcdMap.accept(gcd).get(3), 3);
        assertSame(gcdMap.accept(gcd).get(2), gcdTreeMap.accept(gcd).get(2));
    }

    /**
     * Tests StringWithNumber
     * @throws Exception
     */
    @Test
    public void testStringWithNumber() throws Exception {
        MyMap<Integer, String> resultMyMap = MyMap.empty(new IntComparator());
        resultMyMap = resultMyMap.include(1, "one 1");
        resultMyMap = resultMyMap.include(2, "two 2");
        resultMyMap = resultMyMap.include(3, "three 3");
        assertTrue(treeMap3.accept(stringWithNumber).equals(resultMyMap));
        assertTrue(map3.accept(stringWithNumber).equals(resultMyMap));
        assertEquals(treeMap3.accept(stringWithNumber).get(3),
                resultMyMap.get(3));
        assertEquals(treeMap3.accept(stringWithNumber).get(2),
                map3.accept(stringWithNumber).get(2));
        assertEquals(map1.accept(stringWithNumber).get(1),
                map2.accept(stringWithNumber).get(1));
    }

    /**
     * Tests PathLengths
     * @throws Exception
     */
    @Test
    public void testPathLengths() throws Exception {
        MyMap<Integer, String> resultMyMap = MyMap.empty(new IntComparator());
        resultMyMap = resultMyMap.include(1, "one 1");
        resultMyMap = resultMyMap.include(2, "two 2");
        resultMyMap = resultMyMap.include(3, "three 3");

        assertTrue(emptyTree.acceptRBT(pathLengths).equals(
                new ArrayList<Integer>() { { add(0); } } )
        );

        assertTrue(treeMap3.acceptRBT(pathLengths).equals(
                resultMyMap.acceptRBT(pathLengths)
        ));

        for (int path : treeMap3.acceptRBT(pathLengths)) {
            assertTrue(path >= 0 &&
                path < treeMap3.size());
        }

        try {
            map3.acceptRBT(pathLengths);
        }
        catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }
    }

    /**
     * Tests BlackHeight
     * @throws Exception
     */
    @Test
    public void testBlackHeight() throws Exception {
        MyMap<Integer, String> resultMyMap = MyMap.empty(new IntComparator());
        resultMyMap = resultMyMap.include(1, "one 1");
        resultMyMap = resultMyMap.include(2, "two 2");
        resultMyMap = resultMyMap.include(3, "three 3");

        assertTrue(emptyTree.acceptRBT(blackHeight).equals(0));

        assertTrue(treeMap3.acceptRBT(blackHeight).equals(
                resultMyMap.acceptRBT(blackHeight)
        ));
        assertTrue(treeMap3.acceptRBT(blackHeight) > 0 &&
                treeMap3.acceptRBT(blackHeight) < treeMap3.size());

        try {
            map3.acceptRBT(blackHeight);
        }
        catch (UnsupportedOperationException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test comparator
     */
    private class IntComparator implements Comparator<Integer> {
        /**
         * Compares two strings
         * @param int1
         * @param int2
         * @return the comparison result
         */
        public int compare(Integer int1, Integer int2) {
            if (int1.compareTo(int2) < 0) {
                return -1;
            }
            else if (int1.compareTo(int2) == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }
}

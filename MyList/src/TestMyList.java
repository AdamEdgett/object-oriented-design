/**
 * Tests MyList
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public class TestMyList {
    /**
     * Runs the tests
     * @param args default args for main
     */
    public static void main(String[] args) {
        TestMyList testMyList = new TestMyList();
        testMyList.runTests();
        testMyList.printResults();
    }

    private int testsPassed;
    private int testsFailed;

    private MyList empty;
    private MyList classList;
    private MyList languageList;
    private MyList languageList2;
    private MyList fruitList;

    /**
     * Constructor for this test class
     */
    public TestMyList() {
        testsPassed = 0;
        testsFailed = 0;

        empty = MyList.emptyList();
        classList = MyList.add(empty, "CS3500");
        languageList = MyList.add(empty, "Java");
        languageList = MyList.add(languageList, "Python");
        languageList2 = MyList.add(empty, "Java");
        languageList2 = MyList.add(languageList2, "C++");
        fruitList = MyList.add(MyList.add(empty, "apple"), "banana");
    }

    /**
     * Checks if the test evaluates to true
     * Stores the number of passed tests in testsPassed
     * and the number of failed tests in testsFailed
     * @param test the test boolean
     */
    private void assertTrue(boolean test) {
        if (test) {
            testsPassed++;
        }
        if (!test) {
            testsFailed++;
        }
    }

    /**
     * Checks if the test evaluates to false
     * Stores the number of passed tests in testsPassed
     * and the number of failed tests in testsFailed
     * @param test the test boolean
     */
    private void assertFalse(boolean test) {
        assertTrue(!test);
    }

    /**
     * Runs a series of tests on MyList
     */
    private void runTests() {
        //Test isEmpty
        assertTrue(MyList.isEmpty(empty));
        assertFalse(MyList.isEmpty(classList));

        //Test get
        assertTrue(MyList.get(classList, 0).equals("CS3500"));
        assertTrue(MyList.get(fruitList, 0).equals("banana"));
        assertTrue(MyList.get(fruitList, 1).equals("apple"));
        assertTrue(MyList.get(languageList2, 0).equals("C++"));

        //Test set
        assertTrue(MyList.set(fruitList, 1, "pineapple")
                .get(1).equals("pineapple"));
        assertTrue(MyList.set(languageList2, 0, "Python").equals(languageList));

        //Test size
        assertTrue(MyList.size(empty) == 0);
        assertTrue(MyList.size(classList) == 1);
        assertTrue(MyList.size(fruitList) == 2);
        assertTrue(MyList.size(languageList) == MyList.size(languageList2));

        //Test contains
        assertTrue(MyList.contains(fruitList, "banana"));
        assertFalse(MyList.contains(languageList, "C++"));
        assertFalse(MyList.contains(empty, "test"));

        //Test toString
        assertTrue(empty.toString().equals("[]"));
        assertTrue(classList.toString().equals("[CS3500]"));
        assertTrue(fruitList.toString().equals(("[banana, pineapple]")));
        assertTrue(languageList.toString().equals("[Python, Java]"));
        assertTrue(languageList.toString().equals(languageList2.toString()));

        //Test equals
        assertTrue(empty.equals(empty));
        assertFalse(empty.equals(null));
        assertFalse(classList.equals(fruitList));
        assertTrue(languageList.equals(languageList2));

        //Test hashCode
        assertTrue(empty.hashCode() == empty.hashCode());
        assertTrue(languageList.hashCode() == languageList2.hashCode());
        assertFalse(classList.hashCode() == fruitList.hashCode());
    }

    /**
     * Prints the test results
     * indicating how many tests passed and how many failed
     */
    private void printResults() {
        int totalTests = testsPassed + testsFailed;
        System.out.println(String.format(
                "%s out of %s tests passed", testsPassed, totalTests
        ));
        System.out.println(String.format(
                "%s out of %s tests failed", testsFailed, totalTests
        ));
    }
}
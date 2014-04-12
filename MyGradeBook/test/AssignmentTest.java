import gradebook.Assignment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the assignment class
 * @author Adam Edgett Edgett.a@husky.neu.edu
 * @version 4/12/14
 */
public class AssignmentTest {
    private Assignment a1 = new Assignment("a1", 100.0, 10.0);
    private Assignment a2 = new Assignment("a2", 100.0, 10.0);

    /**
     * Initializes the tests
     */
    @Before
    public void initialize() {
        a2.addGradeForStudent("bob", 80.0);
        a2.addGradeForStudent("tina", 100.0);
        a2.addGradeForStudent("john", 73.0);
        a2.addGradeForStudent("tim", 56.0);
    }

    /**
     * Tests change grade
     */
    @Test
    public void testChangeGrade() {
        assertFalse(a1.changeGrade("bob", 40.0));
        a1.addGradeForStudent("bob", 20.0);
        assertTrue(a1.changeGrade("bob", 60.0));
    }

    /**
     * Tests add grade
     */
    @Test
    public void testAddGradeForStudent() {
        a1.addGradeForStudent("bob", 90.0);
        assertEquals(90.0, a1.getGrade("bob"), 0.01);
    }

    /**
     * Tests get grade
     */
    @Test
    public void testGetGrade() {
        a2.getGrades().put("tom", null);
        assertEquals(77.25, a2.average(), 0.01);
    }

    /**
     * Tests the average function
     */
    @Test
    public void testAverage()  {
        assertEquals(77.25, a2.average(), 0.01);
    }

    /**
     * Tests the median function
     */
    @Test
    public void testMedian()  {
        assertEquals(76.5, a2.median(), 0.01);
    }

    /**
     * Tests the min function
     */
    @Test
    public void testMin() {
        assertEquals(56.0, a2.min(), 0.01);
    }

    /**
     * Tests the max function
     */
    @Test
    public void testMax() {
        assertEquals(100.0, a2.max(), 0.01);
    }
}

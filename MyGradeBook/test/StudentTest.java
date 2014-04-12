import gradebook.Student;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the student class
 * @author Adam Edgett Edgett.a@husky.neu.edu
 * @version 4/12/14
 */
public class StudentTest {
    private Student s1 = new Student("aedgett", "adam", "edgett", 2015, "bob");

    /**
     * Tests set first name
     */
    @Test
    public void testSetFirstName() {
        s1.setFirstName("rob");
        assertEquals("rob", s1.getFirstName());
    }

    /**
     * Tests set last name
     */
    @Test
    public void testSetLastName() {
        s1.setLastName("johnson");
        assertEquals("johnson", s1.getLastName());
    }

    /**
     * Tests set grad year
     */
    @Test
    public void testSetGradYear() {
        s1.setGradYear(2014);
        assertEquals(2014, s1.getGradYear());
    }

    /**
     * Tests set advisor
     */
    @Test
    public void testSetAdvisor() {
        s1.setAdvisor("john");
        assertEquals("john", s1.getAdvisor());
    }
}

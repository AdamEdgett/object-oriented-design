
import java.util.ArrayList;
import java.util.HashMap;

import gradebook.Assignment;
import gradebook.MyGradeBook;
import gradebook.Student;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the MyGradeBook class
 * @author Andrew Krischer
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2014-4-10
 */
public class MyGradeBookTest {
    //Students
    private Student s1 = 
            new Student("DiggityDan", "Dan", "Nordness", 2017, "Wendy");

    private Student s2 = 
            new Student("KrischerA", "Andrew", "Krischer", 2017, "Ted");


    private Student s3 =
            new Student("Aedgett", "Adam", "Edgett", 2017, "Phil");


    private Student s4 =
            new Student("Tykam993", "Tyler", "Kaminsky", 2017, "Wendy");

    private Student s5 =
            new Student("Tester", "Test", "Testy", 2017, "Wendy");


    //Hashmaps
    private HashMap<String, Double> grades1;
    private HashMap<String, Double> grades2;
    private HashMap<String, Double> grades3;
    private HashMap<String, Double> grades4;
    private HashMap<String, Double> grades5;
    private HashMap<String, Double> grades6;

    //Assignments
    private Assignment a1;
    private Assignment a2;
    private Assignment a3;
    private Assignment a4;
    private Assignment a5;

    //ArrayLists
    private ArrayList<Student> al1;
    private ArrayList<Assignment> al2;
    private ArrayList<Assignment> al3;

    //Gradebook
    private MyGradeBook gb1;
    private MyGradeBook gb2;

    /**
     * Initialize HashMaps for testing
     */
    @Before
    public void initHashMaps() {

        this.grades1 = new HashMap<String, Double>();

        grades1.put("DiggityDan", 100.00);
        grades1.put("KrischerA", 85.00);
        grades1.put("Aedgett", 80.00);
        grades1.put("Tykam993", 63.00);
        grades1.put("Tester", 88.00);

        this.grades2 = new HashMap<String, Double>();

        grades2.put("DiggityDan", 90.00);
        grades2.put("KrischerA", 66.00);
        grades2.put("Aedgett", 11.00);
        grades2.put("Tykam993", 3.00);
        grades2.put("Tester", 88.00);

        this.grades3 = new HashMap<String, Double>();

        grades3.put("DiggityDan", 40.00);
        grades3.put("KrischerA", 13.00);
        grades3.put("Aedgett", 98.00);
        grades3.put("Tykam993", 76.00);
        grades3.put("Tester", 88.00);

        this.grades4 = new HashMap<String, Double>();

        grades4.put("DiggityDan", 7.5);
        grades4.put("KrischerA", 7.5);
        grades4.put("Aedgett", 9.9);
        grades4.put("Tykam993", 10.0);
        grades4.put("Tester", 8.8);

        this.grades5 = new HashMap<String, Double>();

        grades5.put("DiggityDan", 100.00);
        grades5.put("KrischerA", 85.00);
        grades5.put("Aedgett", 80.00);
        grades5.put("Tykam993", 30.00);
        grades5.put("Tester", 88.00);
        
        this.grades6 = new HashMap<String, Double>();

        grades6.put("DiggityDan", 81.5);
        grades6.put("KrischerA", 62.0);
        grades6.put("Aedgett", 57.9);
        grades6.put("Tykam993", 45.30);
        grades6.put("Tester", 88.00);

        this.a1 = new Assignment("Test1", grades1, 100.0, 30.0);

        this.a2 = new Assignment("HW1", grades2, 100.0, 40.0);

        this.a3 = new Assignment("Quiz1", grades3, 100.0, 20.0);

        this.a4 = new Assignment("Participation1", grades4, 10.0, 10.0);

        this.a5 = new Assignment("Test1", grades5, 100.0, 30.0);

        this.al1 = new ArrayList<Student>();
        al1.add(s1);
        al1.add(s2);
        al1.add(s3);
        al1.add(s4);
        al1.add(s5);

        this.al2 = new ArrayList<Assignment>();
        al2.add(a1);
        al2.add(a2);
        al2.add(a3);
        al2.add(a4);

        this.al3 = new ArrayList<Assignment>();
        al3.add(a5);
        al3.add(a2);
        al3.add(a3);
        al3.add(a4);

        this.gb1 = new MyGradeBook(al1, al2);
        this.gb2 = new MyGradeBook(al1, al3);
    }

    /**
     * Initialize assignments for testing
     */
    @Before
    public void initAssignments() {
        this.a1 = new Assignment("Test1", grades1, 100.0, 30.0);
        this.a2 = new Assignment("HW1", grades2, 50.0, 40.0);
        this.a3 = new Assignment("Quiz1", grades3, 30.0, 20.0);
        this.a4 = new Assignment("Participation1", grades4, 10.0, 10.0);
        this.a5 = new Assignment("Test1", grades5, 100.0, 30.0);
    }

    /**
     * Initialize ArrayLists for testing
     */
    @Before
    public void initArrayLists() {
        this.al1 = new ArrayList<Student>();
        al1.add(s1);
        al1.add(s2);
        al1.add(s3);
        al1.add(s4);
        al1.add(s5);

        this.al2 = new ArrayList<Assignment>();
        al2.add(a1);
        al2.add(a2);
        al2.add(a3);
        al2.add(a4);

        this.al3 = new ArrayList<Assignment>();
        al3.add(a5);
        al3.add(a2);
        al3.add(a3);
        al3.add(a4);
    }


    /**
     * Initialize Gradebook for testing
     */
    @Before
    public void initGradebook() {
        this.gb1 = new MyGradeBook(al1, al2);
        this.gb2 = new MyGradeBook(al1, al3);
    }

    /**
     * Test the changeGrade method
     */
    @Test
    public void testChangeGrade() {
        gb1.changeGrade("Test1", "Tykam993", 30.00);
        assertEquals(gb1.currentGrade("Tykam993"),
                gb2.currentGrade("Tykam993"), .01);
    }

    /**
     * Test the add grade for student method
     */
    @Test
    public void testAddGradeForStudent() {
        gb2.addGradeForStudent("Test1", "RyHop", 80.00);
        assertEquals(80.0, gb2.assignmentGrade("Test1", "RyHop"), 0.01);
    }

    /**
     * Test the average method
     */
    @Test
    public void testAverage() {
        assertEquals(gb1.average(a1.getName()), 83.20, 0.01);
        assertEquals(gb1.average(a2.getName()), 51.60, 0.01);
        assertEquals(gb1.average(a3.getName()), 63.00, 0.01);
    }

    /**
     * Test the median method
     */
    @Test
    public void testMedian() {
        assertEquals(gb1.median(a1.getName()), 85.00, 0.01);
        assertEquals(gb1.median(a2.getName()), 66.00, 0.01);
        assertEquals(gb1.median(a3.getName()), 76.00, 0.01);
    }

    /**
     * Test the min method
     */
    @Test
    public void testMin() {
        assertEquals(gb1.min(a1.getName()), 63.00, 0.01);
        assertEquals(gb1.min(a2.getName()), 3.00, 0.01);
        assertEquals(gb1.min(a3.getName()), 13.00, 0.01);
    }

    /**
     * Test the max method
     */
    @Test
    public void testMax() {
        assertEquals(gb1.max(a1.getName()), 100.00, 0.01);
        assertEquals(gb1.max(a2.getName()), 90.00, 0.01);
        assertEquals(gb1.max(a3.getName()), 98.00, 0.01);
    }

    /**
     * Test the currentGrade method
     */
    @Test
    public void testCurrentGrade() {
        assertEquals(gb1.currentGrade("Tykam993"), 45.3, 0.01);
        assertEquals(gb1.currentGrade("Tester"), 88.0, 0.01);
        assertEquals(gb1.currentGrade("DiggityDan"), 81.5, 0.01);
    }

    /**
     * Test the currentGrades method
     */
    @Test
    public void testCurrentGrades() {
        assertEquals(gb1.currentGrades(),
                grades6);
    }

    /**
     * Test the assignmentGrade method
     */
    @Test
    public void testAssignmentGrade() {
        assertEquals(gb1.assignmentGrade("Test1", "Tester"), 88.0, 0.01);
        assertEquals(gb1.assignmentGrade("Test1", "Tykam993"), 63.0, 0.01);
    }

    /**
     * Test the outputCurrentGrades method
     */
    @Test
    public void testOutputCurrentGrades() {
        assertEquals(gb1.outputCurrentGrades(),
                "CURRENT_GRADES" + "\n"
                        + "DiggityDan\t" + gb1.currentGrade("DiggityDan") + "\n"
                        + "KrischerA\t" + gb1.currentGrade("KrischerA") + "\n"
                        + "Aedgett\t" + gb1.currentGrade("Aedgett") + "\n"
                        + "Tykam993\t" + gb1.currentGrade("Tykam993") + "\n"
                        + "Tester\t" + gb1.currentGrade("Tester") + "\n");
    }

    /**
     * Test the outputStudentGrades method
     */
    @Test
    public void testOutputStudentGrades() {
        assertEquals(gb1.outputStudentGrades("Aedgett"),
                "STUDENT_GRADES" + "\n"
                        + "Aedgett" + "\n"
                        + "Adam" + "\n"
                        + "Edgett" + "\n"
                        + "Phil" + "\n"
                        + "2017" + "\n"
                        + "----" + "\n"
                        + "Test1" + "\t" + "80.0" + "\n"
                        + "HW1" + "\t" + "11.0" + "\n"
                        + "Quiz1" + "\t" + "98.0" + "\n"
                        + "Participation1" + "\t" + "9.9" + "\n"
                        + "----" + "\n"
                        + "CURRENT GRADE" + "\t" + "57.9\n");
    }

    /**
     * Test the outputAssignmentGrades method
     */
    @Test
    public void testOutputAssignmentGrades() {
        assertEquals(gb1.outputAssignmentGrades("Test1"),
                "ASSIGNMENT_GRADES" + "\n"
                        + "Test1" + "\n"
                        + "100.0" + "\n"
                        + "30.0" + "\n"
                        + "----" + "\n"
                        + "Tykam993" + "\t" + "63.0" + "\n"
                        + "KrischerA" + "\t" + "85.0" + "\n"
                        + "DiggityDan" + "\t" + "100.0" + "\n"
                        + "Tester" + "\t" + "88.0" + "\n"
                        + "Aedgett" + "\t" + "80.0" + "\n"
                        + "----" + "\n"
                        + "STATS" + "\n"
                        + "Average" + "\t" + "83.2" + "\n"
                        + "Median" + "\t" + "85.0" + "\n"
                        + "Max" + "\t" + "100.0" + "\n"
                        + "Min" + "\t" + "63.0\n");
    }

    /**
     * Test the outputGradebook method
     */
    @Test
    public void testOutputGradebook() {
        assertEquals(gb1.outputGradebook(),
                "GRADEBOOK" + "\n"
                        + "Test1" + "\t" + "HW1" + "\t" + "Quiz1" + "\t"
                        + "Participation1\t"
                        + "\n"
                + "100.0\t" + "100.0" + "\t" + "100.0" + "\t" +
                "10.0\t" + "\n"
                + "30.0" + "\t" + "40.0" + "\t" + "20.0" + "\t" + "10.0\t"
                + "\n"
                + "DiggityDan" + "\t" + "Dan" + "\t" +
                "Nordness" + "\t" + "Wendy" + "\t" + "2017" + "\t" +
                "100.0" + "\t"
                + "90.0" + "\t" + "40.0" + "\t" + "7.5\t"
                + "\n"
                + "KrischerA" + "\t" + "Andrew" + "\t" +
                "Krischer" + "\t" + "Ted" + "\t" + "2017" + "\t" +
                "85.0" + "\t"
                + "66.0" + "\t" + "13.0" + "\t" + "7.5\t" + "\n"
                + "Aedgett" + "\t" + "Adam" + "\t" +
                "Edgett" + "\t" + "Phil" + "\t" + "2017" + "\t" +
                "80.0" + "\t"
                + "11.0" + "\t" + "98.0" + "\t" + "9.9\t" + "\n"
                + "Tykam993" + "\t" + "Tyler" + "\t" +
                "Kaminsky" + "\t" + "Wendy" + "\t" + "2017" + "\t" +
                "63.0" + "\t"
                + "3.0" + "\t" + "76.0" + "\t" + "10.0\t" + "\n"
                + "Tester" + "\t" + "Test" + "\t" +
                "Testy" + "\t" + "Wendy" + "\t" + "2017" + "\t" +
                "88.0" + "\t"
                + "88.0" + "\t" + "88.0" + "\t" + "8.8\t" + "\n");
    }

    /**
     * Test the initialize method
     */
    @Test
    public void testInitialize() {
        assertEquals(MyGradeBook.initialize().getAssignments(),
                new ArrayList<Assignment>());
        assertEquals(MyGradeBook.initialize().getStudents(),
                new ArrayList<Student>());
    }

    /**
     * Test the initializeWithFile method
     */
    @Test
    public void testInitializeWithFile() {
        MyGradeBook gb3 = MyGradeBook.initializeWithFile("data/initial.txt");
        assertNotNull(gb3.getStudent("onon"));
        assertNotNull(gb3.getAssignment("A2"));
        assertEquals(89.0, gb3.assignmentGrade("A2", "thms"), 0.01);
        assertEquals(1.0, gb3.getAssignment("Opening Assignment").getWeight(),
                0.01);
        assertEquals(100.0, gb3.getAssignment("A2").getTotalPoints(), 0.01);
        assertEquals("Green", gb3.getStudent("ydenavi").getAdvisor());
    }

    /**
     * Test the processFile method
     */
    @Test
    public void testProcessFile() {
        gb2.processFile("data/addStudents.txt");
        assertEquals("Alexander", gb2.getStudent("xaod").getFirstName());

        gb2.processFile("data/addAssignments.txt");
        assertEquals(150.0,
                gb2.getAssignment("First Group Project").getTotalPoints(),
                0.01);

        gb2.addAssignment("Opening Assignment", 10.0, 5.0);
        gb2.addAssignment("A2", 100.0, 5.0);
        gb2.processFile("data/gradesForAssignment1.txt");
        assertEquals(9.0,
                gb2.assignmentGrade("Opening Assignment", "illines"),
                0.01);

        gb2.processFile("data/gradesForStudent.txt");
        assertEquals(51.0, gb2.assignmentGrade("A2", "iaartinez"), 0.01);
    }
}
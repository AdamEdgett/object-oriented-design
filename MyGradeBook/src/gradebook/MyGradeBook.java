package gradebook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a gradebook
 * @author Adam Edgett Edgett.a@husky.neu.edu
 * @version 4/12/14
 */
public class MyGradeBook {
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;

    /**
     * Empty constructor for gradebook
     */
    public MyGradeBook() {
        this.students = new ArrayList<Student>();
        this.assignments = new ArrayList<Assignment>();
    }

    /**
     * Constructor for gradebook
     * @param students the students to initialize with
     * @param assignments the assignments to initialize with
     */
    public MyGradeBook(
            ArrayList<Student> students,
            ArrayList<Assignment> assignments
    ) {
        this.students = students;
        this.assignments = assignments;
    }

    /**
     * @param username the username to get
     * @return the Student for the given username
     */
    public Student getStudent(String username) {
        for (Student student : students) {
            if (username.equals(student.getUsername())) {
                return student;
            }
        }
        return null;
    }

    /**
     * @param assignmentName the assignment name to get
     * @return the Assignment for the given assignmentName
     */
    public Assignment getAssignment(String assignmentName) {
        for (Assignment assignment : assignments) {
            if (assignment.getName().equals(assignmentName)) {
                return assignment;
            }
        }
        return null;
    }

    /**
     * Factory method to construct an empty MyGradebook
     *
     * @return an empty MyGradeBook
     */
    public static MyGradeBook initialize() {
        MyGradeBook gradeBook = new MyGradeBook();
        gradeBook.students = new ArrayList<Student>();
        gradeBook.assignments = new ArrayList<Assignment>();
        return gradeBook;
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from filename
     *
     * @param filename
     *        the filename for the file that contains the initial grade
     *        book, which is formatted like initial.txt
     * @return a MyGradebook that contains the grade book from filename
     */
    public static MyGradeBook initializeWithFile(String filename) {
        try {
            String fileContent = new Scanner(new File(filename))
                    .useDelimiter("\\A").next();
            return initializeWithString(fileContent);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Factory method to construct a MyGradebook that contains the grade book
     * from startingString
     *
     * @param startingString
     *        String that contains the initial grade book, which is
     *        formatted like initial.txt
     * @return a MyGradebook that contains the grade book from startingString
     */
    public static MyGradeBook initializeWithString(String startingString) {
        return MyGradeBookFactory.gradebookFromString(startingString);
    }

    /**
     * Add to the state of this grade book---new assignments, new students,
     * new grades---by processing filename
     *
     * @param filename
     *        the filename for a file that contains information that will be
     *        added to the grade book. The file could contain several
     *        different types of information---new assignments, new
     *        students, new grades. The file will be formatted like
     *        addAssignments.txt, addStudents.txt, gradesForAssignment1.txt,
     *        and gradesForStudent.txt.
     */
    public void processFile(String filename) {
        try {
            String fileContent = new Scanner(new File(filename))
                    .useDelimiter("\\A").next();
            processString(fileContent);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Add to the state of this grade book---new assignments, new students,
     * new grades---by processing additionalString
     *
     * @param additionalString
     *        String that contains information that will be added to the
     *        grade book. The String could contain several different types
     *        of information---new assignments, new students, new grades.
     *        The String will be formatted like addAssignments.txt,
     *        addStudents.txt, gradesForAssignment1.txt, and
     *        gradesForStudent.txt.
     */
    public void processString(String additionalString) {
        //Break String into lines
        Scanner lines = new Scanner(additionalString)
                .useDelimiter("\\s*\\n\\s*");
        int lineCount = 0;
        ArrayList<String> validProcesses = new ArrayList<String>();
        validProcesses.add("ASSIGNMENT");
        validProcesses.add("STUDENT");
        validProcesses.add("GRADES_FOR_ASSIGNMENT");
        validProcesses.add("GRADES_FOR_STUDENT");
        String line = null;
        while (lines.hasNext()) {
            if (line == null || !validProcesses.contains(line)) {
                line = lines.next();
            }

            //Next 3 Lines, Name, Total, Weight
            if (line.equals("ASSIGNMENT")) {
                int startCount = lineCount;
                String assignmentName = null;
                Double assignmentTotal = null;
                Double assignmentWeight = null;
                while (lines.hasNext()) {
                    line = lines.next();
                    int diff = lineCount - startCount;
                    if (diff == 0) {
                        assignmentName = line;
                    }
                    else if (diff == 1) {
                        assignmentTotal = Double.valueOf(line);
                    }
                    else if (diff == 2) {
                        assignmentWeight = Double.valueOf(line);
                        this.addAssignment(
                                assignmentName,
                                assignmentTotal,
                                assignmentWeight
                        );
                        lineCount++;
                        break;
                    }
                    lineCount++;
                }
            }
            //Next 5 Lines, username, firstName, lastName, advisor, year
            else if (line.equals("STUDENT")) {
                String username = null;
                String firstName = null;
                String lastName = null;
                String advisor = null;
                int year;
                int startCount = lineCount;
                while (lines.hasNext()) {
                    line = lines.next();
                    int diff = lineCount - startCount;
                    if (diff == 0) {
                        username = line;
                    }
                    else if (diff == 1) {
                        firstName = line;
                    }
                    else if (diff == 2) {
                        lastName = line;
                    }
                    else if (diff == 3) {
                        advisor = line;
                    }
                    else if (diff == 4) {
                        //Add Student
                        year = Integer.valueOf(line);
                        this.addStudent(
                                username,
                                firstName,
                                lastName,
                                year,
                                advisor
                        );
                        lineCount++;
                        break;
                    }
                    lineCount++;
                }
            }
            //Pattern next line is assignmentName, then it's student, grade REP
            else if (line.equals("GRADES_FOR_ASSIGNMENT")) {
                int startCount = lineCount;
                String assignmentName = null;
                while (lines.hasNext()) {
                    line = lines.next();
                    //For these pattern ones, we know we're at the end when we
                    //see the next kind of processing that needs to be done
                    if (validProcesses.contains(line)) {
                        break;
                    }
                    int difference = lineCount - startCount;
                    //First line is assignmentName
                    if (difference ==  0) {
                        assignmentName = line;
                    }
                    else {
                        //If it's odd it's the name of student if you
                        //don't believe me look in the gradesForAssign.txt
                        if (difference % 2 == 1) {
                            this.getAssignment(assignmentName)
                                    .addGradeForStudent(
                                            line,
                                            Double.valueOf(lines.next())
                                );
                            lineCount++;
                        }
                    }
                    lineCount++;
                }
            }
            //Pattern next line is username then assignmentName, grade REP
            else if (line.equals("GRADES_FOR_STUDENT")) {
                int startCount = lineCount;
                String username = null;
                while (lines.hasNext()) {
                    line = lines.next();
                    //For these pattern ones, we know we're at the end when we
                    //see the next kind of processing that needs to be done
                    if (validProcesses.contains(line)) {
                        break;
                    }
                    int difference = lineCount - startCount;
                    //First line is username
                    if (difference ==  0) {
                        username = line;
                    }
                    else {
                        //If it's odd it's the name of assignment if you
                        //don't believe me look in the gradesForStudent.txt
                        if (difference % 2 == 1) {
                            this.getAssignment(line).addGradeForStudent(
                                    username,
                                    Double.valueOf(lines.next())
                            );
                            lineCount++;
                        }
                    }
                    lineCount++;
                }
            }
        }
    }

    /**
     * Changes the assignment (named assignmentName) grade for student (whose
     * username is equal to username) to newGrade
     *
     * @param assignmentName
     *        name of the assignment
     * @param username
     *        username for the student
     * @param newGrade
     *        the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *     assignment/student combination exists, returns false otherwise
     */
    public boolean changeGrade(String assignmentName,
                               String username, Double newGrade) {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.changeGrade(username, newGrade);
        }
        else {
            return false;
        }
    }

    /**
     * Changes the assignment (named assignmentName) grade for student (whose
     * username is equal to username) to newGrade
     *
     * @param assignmentName
     *        name of the assignment
     * @param username
     *        username for the student
     * @param newGrade
     *        the new grade for the given assignment and student
     * @return whether there was a grade to change. Returns true if the given
     *     assignment/student combination exists, returns false otherwise
     */
    public boolean changeGrade(String assignmentName,
                               String username, Integer newGrade) {
        return changeGrade(assignmentName, username, (double) newGrade);
    }

    /**
     * Adds a grade for the given student
     * @param assignmentName the assignment name
     * @param username the username to add for
     * @param newGrade the new grade
     */
    public void addGradeForStudent(
            String assignmentName, String username, Double newGrade) {
        Assignment assignment = getAssignment(assignmentName);
        assignment.addGradeForStudent(username, newGrade);
    }

    /**
     * Calculates the average across all students for a given assignment
     *
     * @param assignmentName
     *        name of the assignment
     * @return the average across all students for assignmentName
     */
    public double average(String assignmentName) {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.average();
        }
        throw new RuntimeException("Assignment not found");
    }

    /**
     * Calculates the median across all students for a given assignment
     *
     * @param assignmentName
     *        name of the assignment
     * @return the median across all students for assignmentName
     */
    public double median(String assignmentName) {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.median();
        }
        throw new RuntimeException("Assignment not found");
    }

    /**
     * Calculates the min across all students for a given assignment
     *
     * @param assignmentName
     *        name of the assignment
     * @return the min across all students for assignmentName
     */
    public double min(String assignmentName)
    {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.min();
        }
        throw new RuntimeException("Assignment not found");
    }

    /**
     * Calculates the max across all students for a given assignment
     *
     * @param assignmentName
     *        name of the assignment
     * @return the max across all students for assignmentName
     */
    public double max(String assignmentName) {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.max();
        }
        throw new RuntimeException("Assignment not found");
    }

    /**
     * Calculates the current grade for the given student
     *
     * @param username
     *        username for the student
     * @return the current grade for student with username. The current grade is
     *     calculated based on the current assignment grades, assignment
     *     total points, assignment percent of semester. The current grade
     *     for a student is the sum of the relative assignment grades
     *     divided by the current percent of semester time 100. Since all
     *     grades may not currently be entered, we have to divide by the
     *     current percent. The relative assignment grade is the student's
     *     assignment grade divide by total point value for the assignment
     *     times the percent of semester.
     */
    public double currentGrade(String username) {
        Double totalGrade = 0.0;
        Double totalWeight = 0.0;
        for (Assignment assn : assignments) {
            Double percent = assn.getPercent(username);
            // Ignore unset grades (= -1)
            if (percent >= 0) {
                totalGrade += (percent * 100) * assn.getWeight();
                totalWeight += assn.getWeight();
            }
        }
        return (totalGrade / totalWeight);
    }

    /**
     * Calculates the current grade for all students
     *
     * @return HashMap of the current grades for all students. The key of the
     *     HashMap is the username of the student. The value is the current
     *     grade for the associated student. The current grade is calculated
     *     based on the current assignment grades, assignment total points,
     *     assignment percent of semester. The current grade for a student
     *     is the sum of the relative assignment grades divided by the
     *     current percent of semester time 100. Since all grades may not
     *     currently be entered, we have to divide by the current percent.
     *     The relative assignment grade is the student's assignment grade
     *     divide by total point value for the assignment times the percent
     *     of semester.
     */
    public HashMap<String, Double> currentGrades() {
        HashMap<String, Double> currentGrades = new HashMap<String, Double>();
        for (Student student : students) {
            currentGrades.put(student.getUsername(),
                    currentGrade(student.getUsername()));
        }
        return currentGrades;
    }

    /**
     * Provides the grade earned by the given student for the given assignment
     *
     * @param assignmentName
     *        name of the assignment
     * @param username
     *        username for the student
     * @return the grade earned by username for assignmentName
     */
    public double assignmentGrade(String assignmentName,
                                  String username) {
        Assignment assignment = getAssignment(assignmentName);
        if (assignment != null) {
            return assignment.getGrade(username);
        }
        throw new RuntimeException("Assignment not found");
    }

    /**
     * Provide a String that contains the current grades of all students in the
     * course
     *
     * @return a String that contains the current grades of all students in the
     *     course. The String should be formatted like
     *     currentGrades.txt---CURRENT_GRADES heading and each row: username
     *     followed by tab and current grade. The usernames will be listed
     *     alphabetically.
     */
    public String outputCurrentGrades() {
        return MyGradeBookFactory.writeCurrentGradesToFile(
                this, "currentGrades.txt"
        );
    }

    /**
     * Provide a String that contains the current grades of the given student
     *
     * @param username
     *        username for student
     * @return a String that contains the current grades of username. The String
     *     should be formatted like studentGrades.txt---STUDENT_GRADES
     *     heading, student info, dividers, each assignment (assignment name
     *     followed by tab and assignment grade), and current grade.
     *     Assignments are to remain in the same order as given.
     */
    public String outputStudentGrades(String username) {
        return MyGradeBookFactory.writeStudentGradesToFile(
                this, username, "studentGrades.txt"
        );
    }

    /**
     * Provide a String that contains the assignment grades of all students in
     * the course for the given assignment
     *
     * @param assignName
     *        name of the assignment
     * @return a String that contains the assignment grades of all students in
     *     the course for assignName. The String should be formatted like
     *     assignmentGrade.txt---ASSIGNMENT_GRADES heading, assignment info,
     *     dividers, each student (username followed by tab and assignment
     *     grade), and assignment stats. The usernames will be listed
     *     alphabetically while assignments are to remain in the same
     *     order as given.
     */
    public String outputAssignmentGrades(String assignName) {
        return MyGradeBookFactory.writeAssignmentGradesToFile(
                this, assignName, "assignmentGrades.txt"
        );
    }

    /**
     * Provide a String that contains the current grade book. This String could
     * be used to initialize a new grade book.
     *
     * @return a String that contains the current grade book. This String could
     *     be used to initialize a new grade book. The String should be
     *     formatted like gradebook.txt. The usernames will be listed
     *     alphabetically.
     */
    public String outputGradebook()  {
        return MyGradeBookFactory.writeGradebookToFile(this, "gradebook.txt");
    }

    /**
     * Provide a String that contains the current grade book. This String could
     * be used to initialize a new grade book.
     * @param filePath the filepath to write to
     * @return a String that contains the current grade book. This String could
     *     be used to initialize a new grade book. The String should be
     *     formatted like gradebook.txt. The usernames will be listed
     *     alphabetically.
     */
    public String outputGradebook(String filePath)  {
        return MyGradeBookFactory.writeGradebookToFile(this, filePath);
    }

    /*
     * CRUD Assignment
     */

    /**
     * Getter for assignments
     * @return assignments
     */
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Adds an assignment to the assignment list
     * @param assignment the assignment to add
     */
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Adds an assignment with the given parameters
     * @param name the name of the assignment
     * @param totalPoints the total points
     * @param weight the weight
     */
    public void addAssignment(String name, Double totalPoints, Double weight) {
        if (getAssignment(name) != null) {
            throw new IllegalArgumentException(
                    "Assignment name must be unique"
            );
        }
        assignments.add(new Assignment(name, totalPoints, weight));
    }

    /*
     * CRUD Student
     */

    /**
     * Getter for students
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Adds a student to the students list
     * @param student the student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Adds a student with the given parameters
     * @param username the new username
     * @param firstName the first name
     * @param lastName the last name
     * @param gradYear the year of graduation
     * @param advisor the advisor
     */
    public void addStudent(String username, String firstName, String lastName,
                           int gradYear, String advisor) {
        if (getStudent(username) != null) {
            throw new IllegalArgumentException("Username must be unique");
        }
        students.add(new Student(username, firstName, lastName,
                gradYear, advisor));
    }

    /**
     * Changes the first name of the given username
     * @param username the username to change
     * @param newFirstName the new first name
     */
    public void changeStudentFirstName(String username, String newFirstName) {
        Student student = getStudent(username);
        if (student != null) {
            student.setFirstName(newFirstName);
        }
        else {
            throw new RuntimeException("Student not found");
        }
    }

    /**
     * Changes the last name of the given username
     * @param username the username to change
     * @param newLastName the new last name
     */
    public void changeStudentLastName(String username, String newLastName) {
        Student student = getStudent(username);
        if (student != null) {
            student.setLastName(newLastName);
        }
        else {
            throw new RuntimeException("Student not found");
        }
    }

    /**
     * Changes the year of graduation for the given username
     * @param username the username to change
     * @param newGradYear the new grad year
     */
    public void changeStudentGradYear(String username, int newGradYear) {
        Student student = getStudent(username);
        if (student != null) {
            student.setGradYear(newGradYear);
        }
        else {
            throw new RuntimeException("Student not found");
        }
    }

    /**
     * Changes the advisor for the given username
     * @param username the username to change
     * @param newAdvisor the new advisor
     */
    public void changeStudentAdvisor(String username, String newAdvisor) {
        Student student = getStudent(username);
        if (student != null) {
            student.setAdvisor(newAdvisor);
        }
        else {
            throw new RuntimeException("Student not found");
        }
    }
}

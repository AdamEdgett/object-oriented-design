package gradebook;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for creating gradebook from file
 * @author Dan Nordness nordness.d@husky.neu.edu
 * @version 4/12/14
 */
public class MyGradeBookFactory {
    /**
     * Determines whether string is a number or not
     * @param str string
     * @return true is number
     */
    static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Instatiates a new isntance of MyGradebook from a string
     * @param fileContents the string the is what the file contained
     * @return new instance of Gradebook
     */
    public static final MyGradeBook gradebookFromString(String fileContents) {
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        ArrayList<Student> students = new ArrayList<Student>();

        int lineCount = 0;
        //To Be Used to Construct Assignment
        ArrayList<String> assignmentNames = new ArrayList<String>();
        ArrayList<Double> assignmentWeights = new ArrayList<Double>();
        ArrayList<Double> assignmentTotals = new ArrayList<Double>();
        Scanner lines = new Scanner(fileContents).useDelimiter("\\s*\\n\\s*");
        while (lines.hasNext()) {
            String line = lines.next();
            //Tab Delimited
            Scanner s = new Scanner(line).useDelimiter("\\s*\\t\\s*");
            //Check that the first line is GRADEBOOK
            if (lineCount == 0) {
                String st = s.next();
                //If the file doesn't start with GRADEBOOK return
                //empty gradebook
                if (!st.equals("GRADEBOOK")) {
                    return new MyGradeBook(
                            new ArrayList<Student>(),
                            new ArrayList<Assignment>()
                    );
                }
            }
            //Establish the assignment info
            //Names
            else if (lineCount == 1) {
                while (s.hasNext()) {
                    String st = s.next();
                    assignmentNames.add(st);
                }
            }
            //Totals
            else if (lineCount == 2) {
                while (s.hasNext()) {
                    String st = s.next();
                    assignmentTotals.add(Double.valueOf(st));
                }
            }
            //Weights
            else if (lineCount == 3) {
                while (s.hasNext()) {
                    String st = s.next();
                    assignmentWeights.add(Double.valueOf(st));
                }
                //Construct Assignments
                for (int i = 0; i < assignmentNames.size(); i++) {
                    Assignment toAdd = new Assignment(
                            assignmentNames.get(i),
                            assignmentTotals.get(i),
                            assignmentWeights.get(i)
                    );
                    assignments.add(toAdd);
                }
            }
            //Students and their grades
            else {
                int stringCount = 0;
                String userName = null;
                String firstName = null;
                String lastName = null;
                String advisor = null;
                int year;
                Student toAdd = null;
                while (s.hasNext()) {
                    String st = s.next();
                    //While we're only looking at student information
                    if (stringCount == 0) {
                        userName = st;
                    }
                    else if (stringCount == 1) {
                        firstName = st;
                    }
                    else if (stringCount == 2) {
                        lastName = st;
                    }
                    else if (stringCount == 3) {
                        advisor = st;
                    }
                    else if (stringCount == 4) {
                        year = Integer.valueOf(st);
                        //Construct Student
                        toAdd = new Student(
                                userName,
                                firstName,
                                lastName,
                                year,
                                advisor
                        );
                        students.add(toAdd);
                    }
                    else {
                        //Get the assignment and put the student's grade
                        //in it
                        assignments
                                .get(stringCount - 5)
                                .addGradeForStudent(
                                        toAdd.getUsername(),
                                        Double.valueOf(st)
                            );
                    }
                    stringCount++;
                }

            }
            lineCount++;
        }
        return new MyGradeBook(students, assignments);

    }


    /**
     * Writes a gradeook to a file as specified
     * @param gradeBook the gradebook
     * @param filename the file to be written to with its path include
     * @return a string
     *
     */
    public static final String writeCurrentGradesToFile(
            MyGradeBook gradeBook,
            String filename
    ) {
        try {
            //Line 0
            String gradeBookString = "CURRENT_GRADES\n";
            //Pattern
            //userName| currentGrade
            for (Student student : gradeBook.getStudents()) {
                gradeBookString += student.getUsername()
                                + "\t"
                                + gradeBook.currentGrade(student.getUsername())
                                + "\n";
            }

            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(gradeBookString);
            writer.close( );
            return gradeBookString;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Exception";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception";
        }
    }


    /**
     * Writes a gradeook to a file as specified
     * @param gradeBook the gradebook
     * @param assignmentName the name of the assignment
     * @param filename the file to be written to with its path include
     * @return a string
     *
     */
    public static final String writeAssignmentGradesToFile(
            MyGradeBook gradeBook,
            String assignmentName,
            String filename
    ) {
        try {
            //Line 0
            String gradeBookString = "ASSIGNMENT_GRADES\n";
            //Line 1
            //Name
            gradeBookString +=
                    gradeBook.getAssignment(assignmentName).getName() + "\n";
            //Line 2
            //Total Points
            gradeBookString +=
                    gradeBook.getAssignment(assignmentName).getTotalPoints()
                            + "\n";
            //Line 3
            //Weight
            gradeBookString +=
                    gradeBook.getAssignment(assignmentName).getWeight() + "\n";
            gradeBookString += "----\n";
            //Lines
            //Student | Grade
            for (String username :
                    gradeBook
                    .getAssignment(assignmentName)
                    .getGrades()
                    .keySet()
            ) {
                gradeBookString += username + "\t"
                        + gradeBook.getAssignment(assignmentName)
                            .getGrade(username)
                        + "\n";
            }
            //Stats
            gradeBookString += "----\n";
            gradeBookString += "STATS\n";
            //Average
            gradeBookString += "Average" + "\t"
                    + gradeBook.getAssignment(assignmentName).average() + "\n";
            //Median
            gradeBookString += "Median" + "\t"
                    + gradeBook.getAssignment(assignmentName).median() + "\n";
            //Max
            gradeBookString += "Max" + "\t"
                    + gradeBook.getAssignment(assignmentName).max() + "\n";
            //Min
            gradeBookString += "Min" + "\t"
                    + gradeBook.getAssignment(assignmentName).min() + "\n";

            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(gradeBookString);
            writer.close( );
            return gradeBookString;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Exception";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception";
        }
    }

    /**
     * Writes a gradeook to a file as specified
     * @param gradeBook the gradebook
     * @param username the username
     * @param filename the file to be written to with its path include
     * @return a string
     *
     */
    public static final String writeStudentGradesToFile(
            MyGradeBook gradeBook,
            String username,
            String filename
    ) {
        try {
            //Line 0
            String gradeBookString = "STUDENT_GRADES\n";
            //Line 1 - 5
            //Student info
            gradeBookString +=
                    gradeBook.getStudent(username).getUsername() + "\n";
            gradeBookString +=
                    gradeBook.getStudent(username).getFirstName() + "\n";
            gradeBookString +=
                    gradeBook.getStudent(username).getLastName() + "\n";
            gradeBookString +=
                    gradeBook.getStudent(username).getAdvisor() + "\n";
            gradeBookString +=
                    String.valueOf(
                            gradeBook.getStudent(username).getGradYear()
                ) + "\n";
            gradeBookString += "----\n";
            //Pattern
            //Assignment Grade
            for (Assignment ass : gradeBook.getAssignments()) {
                gradeBookString += ass.getName() + "\t"
                                + ass.getGrade(username) + "\n";
            }
            gradeBookString += "----\n";
            //Last Line
            gradeBookString += "CURRENT GRADE" + "\t"
                            + String.valueOf(
                                gradeBook.currentGrade(username)
                            )
                            + "\n";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(gradeBookString);
            writer.close( );
            return gradeBookString;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Exception";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception";
        }
    }


    /**
     * Writes a gradeook to a file as specified
     * @param gradeBook the gradebook
     * @param filename the file to be written to with its path include
     * @return a string
     *
     */
    public static final String writeGradebookToFile(
            MyGradeBook gradeBook, String filename) {
        try {
            //Line 0
            String gradeBookString = "GRADEBOOK\n";
            //Line 1
            for (Assignment ass : gradeBook.getAssignments()) {
                gradeBookString += ass.getName() + "\t";
            }
            gradeBookString += "\n";
            //Line 2
            //Total Points
            for (Assignment ass : gradeBook.getAssignments()) {
                gradeBookString += String.valueOf(ass.getTotalPoints()) + "\t";
            }
            gradeBookString += "\n";
            //Line 3
            //Weight
            for (Assignment ass : gradeBook.getAssignments()) {
                gradeBookString += String.valueOf(ass.getWeight()) + "\t";
            }
            gradeBookString += "\n";
            //Lines
            //Student {0 : 5 INFO} | grades
            for (Student student : gradeBook.getStudents()) {
                //Student info
                gradeBookString +=
                        student.getUsername() + "\t";
                gradeBookString +=
                        student.getFirstName() + "\t";
                gradeBookString +=
                        student.getLastName() + "\t";
                gradeBookString +=
                        student.getAdvisor() + "\t";
                gradeBookString +=
                        String.valueOf(
                                student.getGradYear()
                    ) + "\t";
                for (Assignment ass : gradeBook.getAssignments()) {
                    //Check if user has grade.
                    String grade;
                    Double numberGrade = ass.getGrade(student.getUsername());
                    if (numberGrade == null) {
                        grade = "NULL";
                    }
                    else {
                        grade = String.valueOf(numberGrade);
                    }
                    gradeBookString += grade + "\t";
                }
                gradeBookString += "\n";
            }

            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(gradeBookString);
            writer.close( );
            return gradeBookString;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Exception";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}

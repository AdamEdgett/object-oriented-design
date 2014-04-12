package gradebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represents an Assignment
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 4/11/14
 */
public class Assignment {
    private String name;
    private HashMap<String, Double> grades;
    private Double totalPoints;
    private Double weight;

    /**
     * Constructor for assignment
     * @param name the assignment name
     * @param totalPoints the total points
     * @param weight the assignment weight
     */
    public Assignment(String name, Double totalPoints, Double weight) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.weight = weight;
        this.grades = new HashMap<String, Double>();
    }

    /**
     * Constructor for assignment
     * Initialized with existing grades
     * @param name the assignment name
     * @param grades the map of grades to students
     * @param totalPoints the total points
     * @param weight the assignment weight
     */
    public Assignment(String name, HashMap<String, Double> grades,
                      Double totalPoints, Double weight) {
        this.name = name;
        this.grades = grades;
        this.totalPoints = totalPoints;
        this.weight = weight;
    }

    /**
     * @param username the username to get a grade for
     * @return the grade for the given username
     */
    public Double getGrade(String username) {
        if (grades.get(username) != null) {
            return grades.get(username);
        }
        else {
            return -1.0;
        }
    }

    /**
     * @param username the username to get a percent for
     * @return the percent grade for the given username
     */
    public Double getPercent(String username) {
        return getGrade(username) / totalPoints;
    }

    /**
     * Updates the grade for the given username
     * @param username the username to update
     * @param newGrade the new grade
     * @return if the grade was successfully changed
     */
    public boolean changeGrade(String username, double newGrade) {
        if (grades.containsKey(username)) {
            grades.put(username, newGrade);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds news grade for username
     * @param username the username to add a grade for
     * @param grade the grade
     */
    public void addGradeForStudent(String username, Double grade) {
        grades.put(username, grade);
    }

    /**
     * @return the average for this assignment
     */
    public Double average() {
        double totalGrade = 0;
        int numGrades = 0;
        for (String student : grades.keySet()) {
            double grade = getGrade(student);
            if (grade >= 0) {
                totalGrade += grade;
                numGrades++;
            }
        }
        return totalGrade / numGrades;
    }

    /**
     * @return the median for this assignment
     */
    public Double median() {
        ArrayList<Double> gradesList = new ArrayList<Double>(grades.values());
        Collections.sort(gradesList);
        if (gradesList.size() % 2 == 0) {
            return (gradesList.get(gradesList.size() / 2)
                    + gradesList.get(gradesList.size() / 2 - 1)) / 2;
        }
        else {
            return gradesList.get(gradesList.size() / 2);
        }
    }

    /**
     * @return the max for this assignment
     */
    public Double max() {
        return Collections.max(grades.values());
    }

    /**
     * @return the min for this assignment
     */
    public Double min() {
        return Collections.min(grades.values());
    }

    /**
     * Getter for assignment name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for grade map
     * @return grades
     */
    public HashMap<String, Double> getGrades() {
        return grades;
    }

    /**
     * Getter for total points
     * @return totalPoints
     */
    public Double getTotalPoints() {
        return totalPoints;
    }

    /**
     * Getter for weight
     * @return weight
     */
    public Double getWeight() {
        return weight;
    }
}

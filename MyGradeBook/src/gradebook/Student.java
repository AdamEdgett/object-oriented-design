package gradebook;

/**
 * Represents a student
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 4/11/14
 */
public class Student {
    private String username;
    private String firstName;
    private String lastName;
    private int gradYear;
    private String advisor;

    /**
     * Constructor for student
     * @param username the username
     * @param firstName the fist name
     * @param lastName the last name
     * @param gradYear the year of graduation
     * @param advisor the advisor name
     */
    public Student(String username, String firstName, String lastName,
                   int gradYear, String advisor) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradYear = gradYear;
        this.advisor = advisor;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for gradYear
     * @return gradYear
     */
    public int getGradYear() {
        return gradYear;
    }

    /**
     * Setter for gradYear
     * @param gradYear the new grad year
     */
    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    /**
     * Getter for advisor
     * @return advisor
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * Setter for advisor
     * @param advisor the new advisor
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }
}

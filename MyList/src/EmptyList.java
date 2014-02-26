/**
 * EmptyLists represents an empty MyList
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public class EmptyList extends MyList {
    /**
     * Constructor for Empty
     */
    EmptyList() {
    }

    /**
     *Checks to see if the MyList is empty
     * @return if the MyList is empty
     */
    boolean isEmpty() {
        return true;
    }

    /**
     * Gets the element at the specified index
     * @param index the index to get from
     * @return the String at that index
     */
    String get(int index) {
        throw new RuntimeException("Cannot get from empty list");
    }

    /**
     * Sets the element at the specified index
     * @param index the index to set
     * @param setVal the value to set
     * @return the modified MyList
     */
    MyList set(int index, String setVal) {
        throw new RuntimeException("Cannot set in empty list");
    }

    /**
     * Returns the size of the MyList
     * @return the size of the MyList
     */
    int size() {
        return 0;
    }

    /**
     * Checks if the MyList contains the given String
     * @param contVal the String to check for
     * @return if the MyList contains the String
     */
    boolean contains(String contVal) {
        return false;
    }

    /**
     * Returns the MyList in String format
     * @return the MyList in String format
     */
    public String toString() {
        return "[]";
    }

    /**
     * Returns the hashcode of the MyList
     * @return the hashcode of the MyList
     */
    public int hashCode() {
        return 0;
    }
}
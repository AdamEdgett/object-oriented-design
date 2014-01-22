/**
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public class EmptyList extends MyList {
    EmptyList() { }

    boolean isEmpty() {
        return true;
    }

    String get(int index) {
        throw new RuntimeException("Cannot get from empty list");
    }

    MyList set(int index, String value) {
        throw new RuntimeException("Cannot set in empty list");
    }

    int size() {
        return 0;
    }

    boolean contains(String value) {
        return false;
    }

    public String toString() {
        return "[]";
    }

    public int hashCode() {
        return 0;
    }
}
import java.util.Comparator;

/**
 * Comparator implementation that compares the reverse of the 
 * Strings by lexicographical ordering
 * 
 * @author Jessica Young Schmidt jschmidt
 * @version 2013-10-07
 */
public class StringReverseByLex implements Comparator<String> {

    /**
     * compares o1 and o2
     * 
     * @param o1
     *            first String in comparison
     * @param o2
     *            second String in comparison
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    public int compare(String o1, String o2) {
        String s1 = new StringBuffer(o1).reverse().toString();
        String s2 = new StringBuffer(o2).reverse().toString();
        return s1.compareTo(s2);
    }

    /**
     * Is this <code>Comparator</code> same as the given object
     * 
     * @param o
     *            the given object
     * @return true if the given object is an instance of this class
     */
    public boolean equals(Object o) {
        return (o instanceof StringReverseByLex);
    }

    /**
     * There should be only one instance of this class = all are equal
     * 
     * @return the hash code same for all instances
     */
    public int hashCode() {
        return (this.toString().hashCode());
    }

    /**
     * @return name of class
     */
    public String toString() {
        return "StringReverseByLex";
    }

}
/**
 * Empty represents a MySet with no Longs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/14/14
 */
class Empty extends MySet {
    /**
     * Constructor for Empty
     */
    Empty() {

    }

    /**
     * Returns the size of this MySet
     *
     * @return the size of the MySet
     */
    int size() {
        return 0;
    }

    /**
     * Checks to see if the MySet is empty
     *
     * @return if the MySet is empty
     */
    boolean isEmpty() {
        return true;
    }

    /**
     * Checks to see if the MySet contains the given Long
     *
     * @param num the Long to check for
     * @return if the MySet contains the Long
     */
    boolean contains(Long num) {
        return false;
    }

    /**
     * Checks to see if a MySet is a subset of the other
     *
     * @param myset the subset to check for
     * @return if the set contains the subset
     */
    boolean isSubset(MySet myset) {
        return true;
    }

    /**
     * Removes the given Long from the MySet
     *
     * @param num the number to remove
     * @return the modified MySet
     */
    MySet remove(Long num) {
        return MySet.empty();
    }

    /**
     * Joins the two MySets
     *
     * @param myset the other MySet
     * @return the joined MySets
     */
    MySet join(MySet myset) {
        return myset;
    }

    /**
     * Returns the intersection of the two MySets
     *
     * @param myset the other MySet
     * @return the resulting intersecting MySet
     */
    MySet intersect(MySet myset) {
        return this;
    }

    /**
     * Replaces a number with another number in the MySet
     *
     * @param orig the number to be replaced
     * @param repl the number to replace
     * @return the modified MySet
     */
    MySet replace(Long orig, Long repl) {
        return MySet.empty();
    }
}

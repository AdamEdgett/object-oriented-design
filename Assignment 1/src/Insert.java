/**
 * Insert represents a non-empty MySet
 *
 * @author Adam Edgett
 * @date 1/14/14
 * @version 1.0
 */
class Insert extends MySet {
    MySet myset;
    Long value;

    //Constructor for Insert
    Insert(MySet myset, Long value) {
        this.myset = myset;
        this.value = value;
    }

    /**
     * Returns the size of this MySet
     *
     * @return the size of the MySet
     */
    int size() {
        return 1 + MySet.size(myset);
    }

    /**
     * Checks to see if the MySet is empty
     *
     * @return if the MySet is empty
     */
    boolean isEmpty() {
        return false;
    }

    /**
     * Checks to see if the MySet contains the given Long
     *
     * @param num the Long to check for
     * @return if the MySet contains the Long
     */
    boolean contains(Long num) {
        return value.equals(num) || MySet.contains(myset, num);
    }

    /**
     * Checks to see if a MySet is a subset of the other
     *
     * @param myset the subset to check for
     * @return if the set contains the subset
     */
    boolean isSubset(MySet myset) {
        return false;
    }

    /**
     * Removes the given Long from the MySet
     *
     * @param num the number to remove
     * @return the modified MySet
     */
    MySet remove(Long num) {
        if(value.equals(num)) {
            return myset;
        }
        else {
            return MySet.insert(MySet.remove(myset, num), value);
        }
    }

    /**
     * Joins the two MySets
     *
     * @param myset the other MySet
     * @return the joined MySets
     */
    MySet join(MySet myset) {
        if(!MySet.contains(myset, value)) {
            MySet.insert(myset, value);
        }
        return MySet.join(this.myset, myset);
    }

    /**
     * Returns the intersection of the two MySets
     *
     * @param myset the other MySet
     * @return the resulting intersecting MySet
     */
    MySet intersect(MySet myset) {
        if(MySet.contains(myset, value)) {
            MySet.insert(myset, value);
        }
        return MySet.intersect(this.myset, myset);
    }

    /**
     * Replaces a number with another number in the MySet
     *
     * @param orig the number to be replaced
     * @param repl the number to replace
     * @return the modified MySet
     */
    MySet replace(Long orig, Long repl) {
        if(this.value.equals(orig)) {
            return MySet.insert(myset, repl);
        }
        else {
            return MySet.insert(MySet.replace(myset, orig, repl), this.value);
        }
    }
}

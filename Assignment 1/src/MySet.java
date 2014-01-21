/**
 * MySet represents a set of Longs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/14/14
 */
public abstract class MySet {
    /**
     * Returns the size of this MySet
     *
     * @return the size of this MySet
     */
    abstract int size();

    /**
     * Returns if this MySet is empty
     *
     * @return if this MySet is empty
     */
    abstract boolean isEmpty();

    /**
     * Returns if this MySet contains the given Long
     *
     * @param num the Long to check for
     * @return if this MySet contains the given Long
     */
    abstract boolean contains(Long num);

    /**
     * Returns if the given MySet is a subset of this MySet
     *
     * @param myset the subset to check for
     * @return if the given MySet is a subset of this MySet
     */
    abstract boolean isSubset(MySet myset);

    /**
     * Removes the given Long from this MySet
     * @param num the Long to remove
     * @return the MySet with the Long removed
     */
    abstract MySet remove(Long num);

    /**
     * Joins the given MySet with this MySet
     *
     * @param myset the MySet to join
     * @return the resulting MySet
     */
    abstract MySet join(MySet myset);

    /**
     * Returns the intersection of the this MySet with the given MySet
     *
     * @param myset the MySet to intersect
     * @return the resulting intersecting MySet
     */
    abstract MySet intersect(MySet myset);

    /**
     * Replaces a Long with another in this MySet
     *
     * @param orig the number to be replaced
     * @param repl the number to replace
     * @return the modified MySet
     */
    abstract MySet replace(Long orig, Long repl);

    /**
     * Returns an empty set
     *
     * @return MySet
     */
    public static MySet empty() {
        return new Empty();
    }

    /**
     * Inserts the given Long into the given MySet
     *
     * @param myset the MySet to be added to
     * @param num the Long to be inserted
     * @return the modified MySet with the new Long added
     */
    public static MySet insert(MySet myset, Long num) {
        if (!MySet.contains(myset, num)) {
            return new Insert(myset, num);
        }
        else {
            return myset;
        }
    }

    /**
     * Returns the size of the given MySet
     *
     * @param myset the MySet to check the size of
     * @return the size of the MySet
     */
    public static int size(MySet myset) {
        return myset.size();
    }

    /**
     * Checks to see if the MySet is empty
     *
     * @param myset the MySet to check if empty
     * @return if the MySet is empty
     */
    public static boolean isEmpty(MySet myset) {
        return myset.isEmpty();
    }

    /**
     * Checks to see if the given MySet contains the given Long
     *
     * @param myset the MySet to check
     * @param num the Long to check for
     * @return if the MySet contains the Long
     */
    public static boolean contains(MySet myset, Long num) {
        return myset.contains(num);
    }

    /**
     * Checks to see if a MySet is a subset of the other
     *
     * @param set the set to check
     * @param subset the subset to check for
     * @return if the set contains the subset
     */
    public static boolean isSubset(MySet subset, MySet set) {
        return subset.isSubset(set);
    }

    /**
     * Removes the given Long from the MySet
     *
     * @param myset the MySet to remove from
     * @param num the number to remove
     * @return the modified MySet
     */
    public static MySet remove(MySet myset, Long num) {
        return myset.remove(num);
    }

    /**
     * Joins the two MySets
     *
     * @param set1 the first MySet
     * @param set2 the second MySet
     * @return the joined MySets
     */
    public static MySet join(MySet set1, MySet set2) {
        return set1.join(set2);
    }

    /**
     * Returns the intersection of the two MySets
     *
     * @param set1 the first MySet
     * @param set2 the second MySet
     * @return the resulting intersecting MySet
     */
    public static MySet intersect(MySet set1, MySet set2) {
        return set1.intersect(set2);
    }

    /**
     * Replaces a number with another number in the given MySet
     *
     * @param myset the MySet to do the replacement
     * @param orig the number to be replaced
     * @param repl the number to replace
     * @return the modified MySet
     */
    public static MySet replace(MySet myset, Long orig, Long repl) {
        return myset.replace(orig, repl);
    }

    /**
     * Converts the MySet to a string
     *
     * @return the string representation of the MySet
     */
    public String toString() {
        return "{...(This set contains "
                + MySet.size(this)
                + " elements)...}";
    }

    /**
     * Checks the equivalence of another object
     *
     * @param obj the object to check equivalence with
     * @return whether the given object equals this MySet
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MySet) {
            MySet objSet = (MySet) obj;
            return this.hashCode() == obj.hashCode();
        }
        else {
            return false;
        }
    }

    /**
     * Returns a hash code of this MySet
     *
     * @return the hash code of this MySet
     */
    public int hashCode() {
        return MySet.size(this);
    }
}


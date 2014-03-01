/**
 * MyList represents a list of Strings
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public abstract class MyList {

    /**
     *Checks to see if the MyList is empty
     * @return if the MyList is empty
     */
    abstract boolean isEmpty();

    /**
     * Gets the element at the specified index
     * @param index the index to get from
     * @return the String at that index
     */
    abstract String get(int index);

    /**
     * Sets the element at the specified index
     * @param index the index to set
     * @param setVal the value to set
     * @return the modified MyList
     */
    abstract MyList set(int index, String setVal);

    /**
     * Returns the size of the MyList
     * @return the size of the MyList
     */
    abstract int size();

    /**
     * Checks if the MyList contains the given String
     * @param contVal the String to check for
     * @return if the MyList contains the String
     */
    abstract boolean contains(String contVal);

    /**
     * Returns an empty list
     * @return an EmptyList
     */
    public static MyList emptyList() {
        return new EmptyList();
    }

    /**
     * Appends the given string to the given MyList
     * @param list the MyList to append to
     * @param str the String to add
     * @return the new MyList
     */
    public static MyList add(MyList list, String str) {
        return new AddList(list, str);
    }

    /**
     * Checks to see if the MyList is empty
     * @param list the MyList to check
     * @return if the MyList is empty
     */
    public static boolean isEmpty(MyList list) {
        return list.isEmpty();
    }

    /**
     * Gets the element at the specified index
     * @param list the MyList to get from
     * @param index the index to get from
     * @return the String at that index
     */
    public static String get(MyList list, int index) {
        return list.get(index);
    }

    /**
     * Sets the element at the specified index
     * @param list the MyList to set
     * @param index the index to set
     * @param setVal the value to set
     * @return the modified MyList
     */
    public static MyList set(MyList list, int index, String setVal) {
        return list.set(index, setVal);
    }

    /**
     * Returns the size of the MyList
     * @param list the MyList to check the size of
     * @return the size of the MyList
     */
    public static int size(MyList list) {
        return list.size();
    }

    /**
     * Checks if the MyList contains the given String
     * @param list the MyList to check
     * @param contVal the String to check for
     * @return if the MyList contains the String
     */
    public static boolean contains(MyList list, String contVal) {
        return list.contains(contVal);
    }

    /**
     * Checks the equivalence of another object
     * @param obj the object to check equivalence with
     * @return whether the given object equals this MySet
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MyList)) {
            return false;
        }
        MyList objList = (MyList) obj;
        if (MyList.size(objList) == MyList.size(this)) {
            for (int i = 0; i < MyList.size(objList); i++) {
                if (!MyList.get(this, i).equals(MyList.get(objList, i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
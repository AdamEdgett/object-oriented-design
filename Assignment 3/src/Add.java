/**
 * Add represents a non-empty MyList
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 1/21/14
 */
public class Add extends MyList {
    private MyList list;
    private String value;

    /**
     * Constructor for Add
     *
     * @param list the nested MyList
     * @param value the top-most value
     */
    Add(MyList list, String value) {
        this.list = list;
        this.value = value;
    }

    /**
     * Checks to see if the MyList is empty
     * @return if the MyList is empty
     */
    boolean isEmpty() {
        return false;
    }

    /**
     * Gets the element at the specified index
     * @param index the index to get from
     * @return the String at that index
     */
    String get(int index) {
        if (index == 0) {
            return this.value;
        }
        else {
            return MyList.get(this.list, index - 1);
        }
    }

    /**
     * Sets the element at the specified index
     * @param index the index to set
     * @param setVal the value to set
     * @return the modified MyList
     */
    MyList set(int index, String setVal) {
        if (index == 0) {
            return new Add(this.list, setVal);
        }
        else {
            MyList setList =  MyList.set(this.list, index - 1, setVal);
            return new Add(setList, this.value);
        }
    }

    /**
     * Returns the size of the MyList
     * @return the size of the MyList
     */
    int size() {
        return MyList.size(this.list) + 1;
    }

    /**
     * Checks if the MyList contains the given String
     * @param contVal the String to check for
     * @return if the MyList contains the String
     */
    boolean contains(String contVal) {
        if (this.value == contVal) {
            return true;
        }
        else {
            return MyList.contains(this.list, contVal);
        }
    }

    /**
     * Returns the MyList in String format
     * @return the MyList in String format
     */
    public String toString() {
        String ret = "[" + this.value.toString();
        if (!MyList.isEmpty(this.list)) {
            ret += ", ";
            ret += this.list.toString()
                    .replace("[", "")
                    .replace("]", "");
        }
        ret += "]";
        return ret;
    }

    /**
     * Returns the hashcode of the MyList
     * @return the hashcode of the MyList
     */
    public int hashCode() {
        return this.value.hashCode() + this.list.hashCode();
    }
}
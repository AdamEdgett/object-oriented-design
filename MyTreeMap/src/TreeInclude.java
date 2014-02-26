import java.util.*;

/**
 * TreeInclude represents an non-empty list of key-value pairs
 * in a tree format for increased performance
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/11/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class TreeInclude<K, V> extends MyTreeMap<K, V> {
    private MyMap<K, V> left;
    private MyMap<K, V> right;
    private K key;
    private V value;
    private Comparator<? super K> comparator;
    private int size;

    /**
     * Constructor for an Include
     * Includes the new key value pair to the given MyTreeMap
     * @param left the left side of the MyTreeMap
     * @param right the right side of the MyTreeMap
     * @param key the key to include
     * @param value the value to include
     * @param comparator the comparator to use to create the MyTreeMap
     */
    public TreeInclude(MyMap<K, V> left, MyMap<K, V> right,
                       K key, V value, Comparator<? super K> comparator) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.value = value;
        this.comparator = comparator;
        this.size = 1 + this.left.size() + this.right.size();
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    public MyTreeMap<K, V> include(K inclKey, V inclValue) {
        int c = comparator.compare(this.key, inclKey);
        if (c == 0) {
            return new TreeInclude<K, V>(this.left, this.right,
                this.key, inclValue, comparator);
        }
        else if (c <= -1) {
            return new TreeInclude<K, V>(
                this.left.include(inclKey, inclValue),
                this.right, this.key, this.value, this.comparator);
        }
        else {
            return new TreeInclude<K, V>(this.left,
                this.right.include(inclKey, inclValue),
                this.key, this.value, this.comparator);
        }
    }

    /**
     * Checks to see if the MyTreeMap is empty
     * @return if the MyTreeMap is empty
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the size of the MyTreeMap
     * @return the size of the MyTreeMap
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the MyTreeMap contains the given key
     * @param contKey the key to check for
     * @return if the MyTreeMap contains the key
     */
    public boolean containsKey(K contKey) {
        int c = comparator.compare(this.key, contKey);
        if (c == 0) {
            return true;
        }
        else if (c <= -1) {
            return this.left.containsKey(contKey);
        }
        else {
            return this.right.containsKey(contKey);
        }
    }

    /**
     * Gets the value at the given key in the MyTreeMap
     * @param getKey the key to get the value for
     * @return the value
     */
    public V get(K getKey) {
        int c = comparator.compare(this.key, getKey);
        if (c == 0) {
            return this.value;
        }
        else if (c <= -1) {
            return this.left.get(getKey);
        }
        else {
            return this.right.get(getKey);
        }
    }

    /**
     * Sets the value at the given key in the MyTreeMap
     * @param setKey the key to set the value for
     * @param setVal the value to set
     * @return the modified MyTreeMap
     */
    public MyTreeMap<K, V> set(K setKey, V setVal) {
        int c = comparator.compare(this.key, setKey);
        if (c == 0) {
            return new TreeInclude<K, V>(this.left, this.right,
                this.key, setVal, this.comparator);
        }
        else if (c <= -1) {
            return new TreeInclude<K, V>(this.left.set(setKey, setVal),
                this.right, this.key, this.value, this.comparator);
        }
        else {
            return new TreeInclude<K, V>(this.left,
                this.right.set(setKey, setVal),
                this.key, this.value, this.comparator);
        }
    }

    /**
     * Gets the keys for this MyTreeMap
     * @return the keys
     */
    ArrayList<K> getKeys() {
        ArrayList<K> keys = new ArrayList<K>();
        keys.addAll(this.left.getKeys());
        keys.addAll(this.right.getKeys());
        keys.add(0, this.key);
        return keys;
    }

    /**
     * Returns the hash code for this MyTreeMap
     * @return the hash code for this MyTreeMap
     */
    public int hashCode() {
        int hashCode = 0;
        for (K subkey : this.getKeys()) {
            hashCode += subkey.hashCode()  * this.get(subkey).hashCode();
        }
        return hashCode;
    }
}

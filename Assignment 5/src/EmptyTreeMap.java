import java.util.ArrayList;
import java.util.Comparator;

/**
 * EmptyMap represents an empty list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/11/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class EmptyTreeMap<K, V> extends MyTreeMap<K, V> {
    private Comparator<? super K> comparator;

    /**
     * Constructor for EmptyMap
     * @param comparator the comparator to use
     */
    public EmptyTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    public MyTreeMap<K, V> include(K inclKey, V inclValue) {
        return new TreeInclude<K, V>(this, this,
                inclKey, inclValue, this.comparator);
    }

    /**
     * Checks to see if the MyTreeMap is empty
     * @return if the MyTreeMap is empty
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns the size of the MyTreeMap
     * @return the size of the MyTreeMap
     */
    public int size() {
        return 0;
    }

    /**
     * Checks if the MyTreeMap contains the given key
     * @param contKey the key to check for
     * @return if the MyTreeMap contains the key
     */
    public boolean containsKey(K contKey) {
        return false;
    }

    /**
     * Gets the value at the given key in the MyTreeMap
     * @param getKey the key to get the value for
     * @return the value
     */
    public V get(K getKey) {
        throw new RuntimeException("Cannot get from empty");
    }

    /**
     * Sets the value at the given key in the MyTreeMap
     * @param setKey the key to set the value for
     * @param setVal the value to set
     * @return the modified MyTreeMap
     */
    public MyTreeMap<K, V> set(K setKey, V setVal) {
        return new TreeInclude<K, V>(this, this,
                setKey, setVal, this.comparator);
    }

    /**
     * Gets the keys for this MyTreeMap
     * @return the keys
     */
    public ArrayList<K> getKeys() {
        return new ArrayList<K>();
    }

    /**
     * Returns the hash code for this MyTreeMap
     * @return the hash code for this MyTreeMap
     */
    public int hashCode() {
        return 0;
    }
}

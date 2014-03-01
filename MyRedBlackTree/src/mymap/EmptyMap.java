package mymap;

import java.util.ArrayList;

/**
 * EmptyMap represents an empty list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class EmptyMap<K, V> extends MyMap<K, V> {

    /**
     * Includes the given key value pair to this MyMap
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyMap
     */
    public MyMap<K, V> include(K inclKey, V inclValue) {
        return new Include<K, V>(this, inclKey, inclValue);
    }

    /**
     * Checks to see if the MyMap is empty
     * @return if the MyMap is empty
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns the size of the MyMap
     * @return the size of the MyMap
     */
    public int size() {
        return 0;
    }

    /**
     * Checks if the MyMap contains the given key
     * @param contKey the key to check for
     * @return if the MyMap contains the key
     */
    public boolean containsKey(K contKey) {
        return false;
    }

    /**
     * Gets the value at the given key in the MyMap
     * @param getKey the key to get the value for
     * @return the value
     */
    public V get(K getKey) {
        throw new RuntimeException("Cannot get from empty");
    }

    /**
     * Sets the value at the given key in the MyMap
     * @param setKey the key to set the value for
     * @param setVal the value to set
     * @return the modified MyMap
     */
    public MyMap<K, V> set(K setKey, V setVal) {
        return new Include<K, V>(this, setKey, setVal);
    }

    /**
     * Gets the keys for this MyMap
     * @return the keys
     */
    public ArrayList<K> getKeys() {
        return new ArrayList<K>();
    }

    /**
     * Returns the hash code for this MyMap
     * @return the hash code for this MyMap
     */
    public int hashCode() {
        return 0;
    }
}

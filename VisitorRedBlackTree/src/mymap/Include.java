package mymap;

import java.util.ArrayList;

/**
 * Include represents an non-empty list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class Include<K, V> extends MyMap<K, V> {
    private MyMap<K, V> map;
    private K key;
    private V value;

    /**
     * Constructor for an Include
     * Includes the new key value pair to the given MyMap
     * @param map the MyMap to append to
     * @param key the key to include
     * @param value the value to include
     */
    public Include(MyMap<K, V> map, K key, V value) {
        this.map = map;
        this.key = key;
        this.value = value;
    }

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
        return false;
    }

    /**
     * Returns the size of the MyMap
     * @return the size of the MyMap
     */
    public int size() {
        if (this.map.containsKey(this.key)) {
            return this.map.size();
        }
        else {
            return 1 + this.map.size();
        }
    }

    /**
     * Checks if the MyMap contains the given key
     * @param contKey the key to check for
     * @return if the MyMap contains the key
     */
    public boolean containsKey(K contKey) {
        if (this.key.equals(contKey)) {
            return true;
        }
        else {
            return this.map.containsKey(contKey);
        }
    }

    /**
     * Gets the value at the given key in the MyMap
     * @param getKey the key to get the value for
     * @return the value
     */
    public V get(K getKey) {
        if (this.key.equals(getKey)) {
            return value;
        }
        else {
            return this.map.get(getKey);
        }
    }

    /**
     * Sets the value at the given key in the MyMap
     * @param setKey the key to set the value for
     * @param setVal the value to set
     * @return the modified MyMap
     */
    public MyMap<K, V> set(K setKey, V setVal) {
        if (this.key.equals(setKey)) {
            return new Include<K, V>(this.map, setKey, setVal);
        }
        else {
            return new Include<K, V>(this.map.set(setKey, setVal),
                    this.key, this.value);
        }
    }

    /**
     * Gets the keys for this MyMap
     * @return the keys
     */
    public ArrayList<K> getKeys() {
        ArrayList<K> keys = this.map.getKeys();
        if (keys.contains(this.key)) {
            // prevent duplicates deeper into the map
            keys.remove(this.key);
        }
        keys.add(0, this.key);
        return keys;
    }

    /**
     * Returns the hash code for this MyMap
     * @return the hash code for this MyMap
     */
    public int hashCode() {
        int hashCode = 0;
        for (K subkey : this.getKeys()) {
            hashCode += 3 + (5 * subkey.hashCode() ^ 11)
                    * (3 * this.get(subkey).hashCode() ^ 13);
        }
        return hashCode;
    }

    public MyMap<K, V> accept(MyMapVisitor mapVisitor) {
        MyMap<K,V> visitMap = MyMap.empty();
        for (K subKey : this.getKeys()) {
            V visitValue = (V) mapVisitor.visit(subKey, this.get(subKey));
            visitMap = visitMap.include(subKey, visitValue);
        }
        return visitMap;
    }

    /**
     * Method that accepts a visitor that produces a value of
     * the type R
     *
     * @param rbtVisitor
     *            the given visitor
     * @param <R>
     *            the type of elements returned by method
     * @return the result of calling the appropriate visit
     *            method from the given the visitor
     *
     * @throws UnsupportedOperationException
     *             if this is not implemented as a Red-Black
     *             Tree
     */
    public <R> R acceptRBT(RBTVisitor<K, V, R> rbtVisitor) {
        throw new UnsupportedOperationException("Not supported in MyMap");
    }
}

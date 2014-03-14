package mymap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * MyMap represents a list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public abstract class MyMap<K, V> implements Iterable<K> {

    /**
     * Checks to see if the MyMap is empty
     * @return if the MyMap is empty
     */
    public abstract boolean isEmpty();

    /**
     * Returns the size of the MyMap
     * @return the size of the MyMap
     */
    public abstract int size();

    /**
     * Checks if the MyMap contains the given key
     * @param contKey the key to check for
     * @return if the MyMap contains the key
     */
    public abstract boolean containsKey(K contKey);

    /**
     * Gets the value at the given key in the MyMap
     * @param getKey the key to get the value for
     * @return the value
     */
    public abstract V get(K getKey);

    /**
     * Sets the value at the given key in the MyMap
     * @param setKey the key to set the value for
     * @param setVal the value to set
     * @return the modified MyMap
     */
    public abstract MyMap<K, V> set(K setKey, V setVal);

    /**
     * Gets the keys for this MyMap
     * @return the keys
     */
    abstract ArrayList<K> getKeys();

    /**
     * Returns an empty MyMap
     * @param <K> the type of the keys
     * @param <V> the type of the values
     * @return an empty MyMap
     */
    public static <K, V> MyMap<K, V> empty() {
        return new EmptyMap<K, V>();
    }

    /**
     * Returns an empty MyMap
     * @param comparator the comparator to use
     * @param <K> the type of the keys
     * @param <V> the type of the values
     * @return an empty MyMap
     */
    public static <K, V> MyMap<K, V> empty(Comparator<? super K> comparator) {
        return MyTreeMap.empty(comparator);
    }

    /**
     * Includes the given key value pair to this MyMap
     * @param key the new key
     * @param value the new value
     * @return the new MyMap
     */
    public abstract MyMap<K, V> include(K key, V value);

    /**
     * Returns the MyMap in String format
     * @return the MyMap in String format
     */
    public String toString() {
        return "{...(" + this.size() + " key(s) mapped to value(s))...}";
    }

    /**
     * Checks the equivalence of another object
     * @param obj the object to check equivalence with
     * @return whether the given object equals this MyMap
     */
    @SuppressWarnings(value = "unchecked")
    public boolean equals(Object obj) {
        if (!(obj instanceof MyMap)) {
            return false;
        }
        MyMap<K, V> objMap = (MyMap<K, V>) obj;
        if (this.size() == objMap.size()) {
            for (K key : this) {
                if (!objMap.containsKey(key)
                        || !this.get(key).equals(objMap.get(key))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the iterator for this MyMap
     * @return the iterator for this MyMap
     */
    public Iterator<K> iterator() {
        return new MyMapIterator<K>(this.getKeys());
    }

    /**
     * Returns the iterator for this MyMap
     * @param comparator the comparator to use to create the iterator
     * @return the iterator for this MyMap
     */
    public Iterator<K> iterator(Comparator<? super K> comparator) {
        ArrayList<K> keys = this.getKeys();
        Collections.sort(keys, comparator);
        return new MyMapIterator<K>(keys);
    }

    public abstract MyMap<K, V> accept(MyMapVisitor mapVisitor);

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
    public abstract <R> R acceptRBT(RBTVisitor<K, V, R> rbtVisitor);
}

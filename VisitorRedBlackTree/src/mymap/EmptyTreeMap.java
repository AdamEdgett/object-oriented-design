package mymap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * EmptyMap represents an empty list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class EmptyTreeMap<K, V> extends MyTreeMap<K, V> {
    /**
     * Constructor for EmptyMap
     * @param comparator the comparator to use
     */
    public EmptyTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
        this.color = Color.BLACK;
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * and then balances the tree
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    protected MyTreeMap<K, V> includeAndBalance(K inclKey, V inclValue) {
        return new TreeInclude<K, V>(this, this,
                inclKey, inclValue, this.comparator,
                Color.RED);
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
                setKey, setVal, this.comparator,
                Color.RED);
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

    /**
     * @return the left side of the tree
     */
    protected MyTreeMap<K, V> getLeft() {
        return null;
    }

    /**
     * @return the right side of the tree
     */
    protected MyTreeMap<K, V> getRight() {
        return null;
    }

    /**
     * @return the key
     */
    protected K getKey() {
        throw new RuntimeException("Cannot get key of empty");
    }

    /**
     * @return the value
     */
    protected V getValue() {
        throw new RuntimeException("Cannot get value of empty");
    }

    /**
     * @return if the node color is red
     */
    public boolean isRed() {
        return false;
    }

    /**
     * @return if the node color is black
     */
    public boolean isBlack() {
        return true;
    }

    /**
     * Sets the node color
     * @param color the node color to set
     * @return the modified TreeInclude
     */
    public MyTreeMap<K, V> setColor(Color color) {
        throw new RuntimeException("Can't change empty color");
    }

    /**
     * Accepts a visitor to a MyMap
     * @param mapVisitor the given visitor
     * @return the result of the visit operation
     */
    public MyMap<K, V> accept(MyMapVisitor mapVisitor) {
        return this;
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
        return rbtVisitor.visitEmpty(this.comparator, this.color.toString());
    }
}

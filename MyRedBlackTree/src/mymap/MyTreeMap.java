package mymap;

import java.util.Comparator;

/**
 * MyTreeMap represents a list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public abstract class MyTreeMap<K, V> extends MyMap<K, V> {
    /**
     * Comparator to use to create the bst
     */
    protected Comparator<? super K> comparator;
    /**
     * Node color for creating red-black tree
     */
    protected Color color;

    /**
     * @return the left side of the tree
     */
    public abstract MyTreeMap<K, V> getLeft();

    /**
     * @return the right side of the tree
     */
    public abstract MyTreeMap<K, V> getRight();

    /**
     * @return the key
     */
    public abstract K getKey();

    /**
     * @return the value
     */
    public abstract V getValue();

    /**
     * @return if the node color is red
     */
    public boolean isRed() {
        return this.color  == Color.RED;
    }

    /**
     * @return if the node color is black
     */
    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    /**
     * Sets the node color
     * @param color the node color to set
     * @return the modified TreeInclude
     */
    public abstract MyTreeMap<K, V> setColor(Color color);

    /**
     * Returns an empty MyTreeMap
     * @param comparator the comparator to use
     * @param <K> the type of the keys
     * @param <V> the type of the values
     * @return an empty MyTreeMap
     */
    public static <K, V> MyTreeMap<K, V> empty(
            Comparator<? super K> comparator) {
        return new EmptyTreeMap<K, V>(comparator);
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    public MyTreeMap<K, V> include(K inclKey, V inclValue) {
        return this.includeAndBalance(inclKey, inclValue).setColor(Color.BLACK);
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * and then balances the tree
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    protected abstract MyTreeMap<K, V> includeAndBalance(
            K inclKey, V inclValue);
}

/**
 * Enumeration for node color
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 */
enum Color {
    /**
     * Red color
     */
    RED,
    /**
     * Black color
     */
    BLACK
}
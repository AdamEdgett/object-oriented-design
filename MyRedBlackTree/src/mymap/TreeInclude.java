package mymap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * TreeInclude represents an non-empty list of key-value pairs
 * in a tree format for increased performance
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class TreeInclude<K, V> extends MyTreeMap<K, V> {
    private MyTreeMap<K, V> left;
    private MyTreeMap<K, V> right;
    private K key;
    private V value;
    private Comparator<? super K> comparator;
    private int size;

    /**
     * Constructor for a TreeInclude
     * Includes the new key value pair to the given MyTreeMap
     * Sets the node color to the given argument
     * @param left the left side of the MyTreeMap
     * @param right the right side of the MyTreeMap
     * @param key the key to include
     * @param value the value to include
     * @param comparator the comparator to use to create the MyTreeMap
     * @param color the node color to use
     */
    public TreeInclude(MyTreeMap<K, V> left, MyTreeMap<K, V> right,
                       K key, V value, Comparator<? super K> comparator,
                       Color color) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.value = value;
        this.comparator = comparator;
        this.color = color;
        this.size = 1 + this.left.size() + this.right.size();
    }

    /**
     * Includes the given key value pair to this MyTreeMap
     * and then balances the tree
     * @param inclKey the new key
     * @param inclValue the new value
     * @return the new MyTreeMap
     */
    protected MyTreeMap<K, V> includeAndBalance(K inclKey, V inclValue) {
        int c = this.comparator.compare(this.key, inclKey);
        if (c == 0) {
            return new TreeInclude<K, V>(
                    this.left, this.right,
                    this.key, inclValue, this.comparator, this.color);
        }
        else if (c <= -1) {
            TreeInclude<K, V> ti = new TreeInclude<K, V>(
                    this.left.includeAndBalance(inclKey, inclValue),
                    this.right, this.key, this.value,
                    this.comparator, this.color);
            return ti.balance();
        }
        else {
            TreeInclude<K, V> ti = new TreeInclude<K, V>(
                    this.left,
                    this.right.includeAndBalance(inclKey, inclValue),
                    this.key, this.value, this.comparator, this.color);
            return ti.balance();
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
        int c = this.comparator.compare(this.key, contKey);
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
        int c = this.comparator.compare(this.key, getKey);
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
        return this.include(setKey, setVal);
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
            hashCode += 3 + (5 * subkey.hashCode() ^ 11)
                    * (3 * this.get(subkey).hashCode() ^ 13);
        }
        return hashCode;
    }

    /**
     * Balances the red-black tree
     * @return the balanced red-black tree
     */
    private TreeInclude<K, V> balance() {
        if (this.isBlack()) {
            if (this.getLeft().isRed()) {
                if (this.getLeft().getRight().isRed()) {
                    return lrRedBalance();
                }
                else if (this.getLeft().getLeft().isRed()) {
                    return llRedBalance();
                }
            }

            else if (this.getRight().isRed()) {
                if (this.getRight().getLeft().isRed()) {
                    return rlRedBalance();
                }
                else if (this.getRight().getRight().isRed()) {
                    return rrRedBalance();
                }
            }
        }
        return this;
    }

    /**
     * Balances the red-black tree
     * in the case of a left-right = red unbalance
     * @return the balanced red-black tree
     */
    private TreeInclude<K, V> lrRedBalance() {
        MyTreeMap<K, V> lrChild = this.getLeft().getRight();
        TreeInclude<K, V> tempLeft = new TreeInclude<K, V>(
                this.getLeft().getLeft(),
                lrChild.getLeft(),
                this.getLeft().getKey(),
                this.getLeft().getValue(),
                this.comparator,
                Color.BLACK);
        TreeInclude<K, V> tempRight = new TreeInclude<K, V>(
                lrChild.getRight(),
                this.getRight(),
                this.getKey(), this.getValue(),
                this.comparator,
                Color.BLACK);
        return new TreeInclude<K, V>(
                tempLeft,
                tempRight,
                lrChild.getKey(), lrChild.getValue(),
                this.comparator,
                Color.RED
        );
    }

    /**
     * Balances the red-black tree
     * in the case of a left-left = red unbalance
     * @return the balanced red-black tree
     */
    private TreeInclude<K, V> llRedBalance() {
        MyTreeMap<K, V> lrChild = this.getLeft().getRight();
        MyTreeMap<K, V> llChild = this.getLeft().getLeft();
        TreeInclude<K, V> tempRight = new TreeInclude<K, V>(
                lrChild,
                this.getRight(),
                this.getKey(), this.getValue(),
                this.comparator,
                Color.BLACK);
        return new TreeInclude<K, V>(
                llChild.setColor(Color.BLACK),
                tempRight,
                this.getLeft().getKey(),
                this.getLeft().getValue(),
                this.comparator,
                Color.RED
        );
    }

    /**
     * Balances the red-black tree
     * in the case of a right-left = red unbalance
     * @return the balanced red-black tree
     */
    private TreeInclude<K, V> rlRedBalance() {
        MyTreeMap<K, V> rlChild = this.getRight().getLeft();
        MyTreeMap<K, V> rrChild = this.getRight().getRight();
        TreeInclude<K, V> tempLeft = new TreeInclude<K, V>(
                this.getLeft(),
                rlChild.getLeft(),
                this.getKey(), this.getValue(),
                this.comparator,
                Color.BLACK);
        TreeInclude<K, V> tempRight = new TreeInclude<K, V>(
                rlChild.getRight(),
                rrChild,
                this.getRight().getKey(),
                this.getRight().getValue(),
                this.comparator,
                Color.BLACK);
        return new TreeInclude<K, V>(
                tempLeft,
                tempRight,
                rlChild.getKey(), rlChild.getValue(),
                this.comparator,
                Color.RED
        );
    }

    /**
     * Balances the red-black tree
     * in the case of a right-right = red unbalance
     * @return the balanced red-black tree
     */
    private TreeInclude<K, V> rrRedBalance() {
        MyTreeMap<K, V> rlChild = this.getRight().getLeft();
        MyTreeMap<K, V> rrChild = this.getRight().getRight();
        TreeInclude<K, V> tempLeft = new TreeInclude<K, V>(
                this.getLeft(),
                rlChild,
                this.getKey(), this.getValue(),
                this.comparator,
                Color.BLACK);
        return new TreeInclude<K, V>(
                tempLeft,
                rrChild.setColor(Color.BLACK),
                this.getRight().getKey(),
                this.getRight().getValue(),
                this.comparator,
                Color.RED
        );
    }

    /**
     * Sets the node color
     * @param color the node color to set
     * @return the modified TreeInclude
     */
    public MyTreeMap<K, V> setColor(Color color) {
        return new TreeInclude<K, V>(
                this.getLeft(),
                this.getRight(),
                this.getKey(), this.getValue(),
                this.comparator,
                color
        );
    }

    /**
     * @return the key
     */
    public K getKey() {
        return this.key;
    }

    /**
     * @return the value
     */
    public V getValue() {
        return this.value;
    }

    /**
     * @return the left side of the tree
     */
    public MyTreeMap<K, V> getLeft() {
        return this.left;
    }

    /**
     * @return the right side of the tree
     */
    public MyTreeMap<K, V> getRight() {
        return this.right;
    }
}

package mymap;

import java.util.Comparator;

/**
 * A visitor for MyMap that was created with MyMap.empty(c) It is the
 * responsibility of the user of this interface to guarantee that it is only
 * used for Red-Black Trees
 * 
 * @author CS3500sp14
 * @version 2014.02.26
 * 
 * @param <K>
 *            the type of key stored in the MyMap
 * @param <V>
 *            the type of value stored in the MyMap
 * @param <R>
 *            the type of data produced by the visitor methods
 */
public interface RBTVisitor<K, V, R> {
    /**
     * The method for the empty tree
     * 
     * @param comp
     *            the Comparator for the whole tree
     * @param color
     *            the color of the node, which should be "RED" or "BLACK"
     * @return some value of the type R
     */
    public R visitEmpty(Comparator<? super K> comp, String color);

    /**
     * The method for the node of the tree
     * 
     * @param comp
     *            the Comparator for the whole tree
     * @param color
     *            the color of the node, which should be "RED" or "BLACK"
     * @param k
     *            the key for the node
     * @param v
     *            the value for the node
     * @param left
     *            the left subtree of the node
     * @param right
     *            the right subtree of the node
     * @return some value of the type R
     */
    public R visitNode(Comparator<? super K> comp, String color, K k, V v,
            MyMap<K, V> left, MyMap<K, V> right);
}

import mymap.MyMap;
import mymap.RBTVisitor;

import java.util.Comparator;

/**
 * Visitor that returns the black height of the tree
 * which is the number of non-empty black node from the root to any empty node
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 */
public class BlackHeight<K, V> implements RBTVisitor<K, V, Integer> {
    /**
     * The method for the empty tree
     *
     * @param comp
     *            the Comparator for the whole tree
     * @param color
     *            the color of the node, which should be "RED" or "BLACK"
     * @return some value of the type R
     */
    public Integer visitEmpty(Comparator<? super K> comp, String color) {
        return 0;
    }

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
    public Integer visitNode(Comparator<? super K> comp, String color, K k, V v,
            MyMap<K, V> left, MyMap<K, V> right) {
        if (color.equals("BLACK")) {
            return 1 + right.acceptRBT(this);
        }
        else {
            return right.acceptRBT(this);
        }
    }
}

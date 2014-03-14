import mymap.MyMap;
import mymap.RBTVisitor;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Visitor that returns an ArrayList of the lengths
 * of the paths from the root to each empty
 * does not include empty nodes
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 */
public class PathLengths<K, V>
        implements RBTVisitor<K, V, ArrayList<Integer>> {
    /**
     * The method for the empty tree
     *
     * @param comp
     *            the Comparator for the whole tree
     * @param color
     *            the color of the node, which should be "RED" or "BLACK"
     * @return some value of the type R
     */
    public ArrayList<Integer> visitEmpty(
            Comparator<? super K> comp, String color) {
        return new ArrayList<Integer>() { { add(0); } };
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
    public ArrayList<Integer> visitNode(
            Comparator<? super K> comp, String color, K k, V v,
            MyMap<K, V> left, MyMap<K, V> right) {
        ArrayList<Integer> paths = new ArrayList<Integer>();
        ArrayList<Integer> leftPaths = left.acceptRBT(this);
        for (Integer length : leftPaths) {
            paths.add(length + 1);
        }
        ArrayList<Integer> rightPaths = right.acceptRBT(this);
        for (Integer length : rightPaths) {
            paths.add(length + 1);
        }
        return paths;
    }
}

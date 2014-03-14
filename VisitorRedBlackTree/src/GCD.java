import mymap.MyMapVisitor;

/**
 * Visitor that returns the greatest common divisor of k and v
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 */
public class GCD implements MyMapVisitor<Integer, Integer> {
    /**
     * @param k
     *            given key
     * @param v
     *            given value
     * @return a suitable new value
     */
    public Integer visit(Integer k, Integer v) {
        if (k == v) {
            return v;
        }
        else if (k > v) {
            return visit(k - v, v);
        }
        else {
            return visit(k, v - k);
        }
    }
}

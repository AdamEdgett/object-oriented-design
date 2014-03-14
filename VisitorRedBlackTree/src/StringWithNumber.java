import mymap.MyMapVisitor;

/**
 * Visitor that returns v followed by k with a space in between
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 3/14/14
 */
public class StringWithNumber implements MyMapVisitor<Integer, String> {
    /**
     * @param k
     *            given key
     * @param v
     *            given value
     * @return a suitable new value
     */
    public String visit(Integer k, String v) {
        return v + " " + k;
    }
}

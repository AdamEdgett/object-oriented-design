import mymap.MyMapVisitor;

/**
 * Created by Adam on 3/13/14.
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

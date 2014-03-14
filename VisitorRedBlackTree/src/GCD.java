import mymap.MyMapVisitor;

/**
 * Created by Adam on 3/13/14.
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
            else if(k > v) {
                return visit(k - v, v);
            }
            else {
                return visit(k, v - k);
            }
        }
}

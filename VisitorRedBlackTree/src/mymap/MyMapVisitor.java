package mymap;

/**
 * A visitor for MyMap
 * 
 * @author CS3500sp14
 * @version 2014.02.26
 * 
 * @param <K>
 *            the type of key stored in the MyMap
 * @param <V>
 *            the type of value stored in the MyMap
 */
public interface MyMapVisitor<K, V> {

    /**
     * @param k
     *            given key
     * @param v
     *            given value
     * @return a suitable new value
     */
    public V visit(K k, V v);
}

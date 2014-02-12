import java.util.Comparator;

/**
 * MyTreeMap represents a list of key-value pairs
 *
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/11/14
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public abstract class MyTreeMap<K, V> extends MyMap<K, V> {

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
    public abstract MyTreeMap<K, V> include(K inclKey, V inclValue);
}

package mymap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The iterator class for MyMap
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2/28/14
 * @param <K> the type of the keys
 */
public class MyMapIterator<K> implements Iterator<K> {
    private ArrayList<K> keys;

    /**
     * Constructor for MyMapIterator
     * @param keys the list of keys
     */
    public MyMapIterator(ArrayList<K> keys) {
        this.keys = keys;
    }

    /**
     * Returns if the iterator has a next element
     * @return if the iterator has a next element
     */
    public boolean hasNext() {
        return !this.keys.isEmpty();
    }

    /**
     * Returns the next element in the iterator
     * @return the next element in the iterator
     */
    public K next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(
                    "Attempted to get next on empty iterator"
            );
        }
        else {
            K next = this.keys.get(0);
            this.keys.remove(next);
            return next;
        }
    }

    /**
     * Removes the element
     * @throws UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
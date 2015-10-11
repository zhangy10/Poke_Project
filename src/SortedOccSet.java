import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName SortedOccSet
 * 
 *            Oct 4, 2015
 * 
 * @param <E>
 *            The element type of this Set.
 * 
 * @Description According to the specification, a few cases mentioned the
 *              situation that a number of types might be the same in Poker
 *              game.
 *              <p>
 *              For example, the challenge of identifying which description
 *              belongs to which card type (n of A Kind) is to find the number
 *              of the same rank in each hand of cards. In this case, the way of
 *              using hash idea to find the occurrence of the same things is the
 *              best solution offering a linear time-consuming complexity to
 *              build the relationship between the number of occurrence and
 *              different things.
 *              <p>
 *              For other cases, implementing or abstracting a single class to
 *              offer the above hash idea is a proper way to avoid code
 *              repetitions and to make the logical parts clearer. Therefore,
 *              this SortedOccSet class is designed for offering the below
 *              features:
 *              <p>
 *              1) Sorting the given objects by ascending order.
 *              <p>
 *              2) Returning the number of occurrence of the same objects.
 *              <p>
 *              3) Finding the a list objects with the same types by
 *              implementing the Occurrence interface as input.
 *              <p>
 *              Note:
 *              <p>
 *              1) The input objects must implement both of methods, compareTo()
 *              for sorting and equal() for checking whether two objects are the
 *              same.
 *              <p>
 *              2) This collection will be involved in two logical parts,
 *              finding same ranks in a hand of cards in CardType class and
 *              picking the highest card type from a group of players in Referee
 *              class.
 *              <p>
 *              3) The Occurrence interface requires to be implemented in some
 *              situations that will find the object references of the same
 *              given type by the number of occurrence. More details can be
 *              found in this interface.
 * 
 * @see CardType, Referee, Occurrence
 */
public final class SortedOccSet<E> {
    /**
     * The main data structure of this class is to use map to build a
     * relationship between key (input type) and value (ArrayList), wrapping the
     * operations of the map to offer the above features.
     */
    private Map<E, ArrayList<E>> map;

    /**
     * The constructor creates a new TreeMap object.
     * <p>
     * Using the TreeMap collection is a better way than using HashMap, because
     * it implements the SortedMap<K, V> interface, offering a sorting feature
     * supported by Red-Black Tree algorithm with O(nlogn) complexity. The key
     * can be sorted as an ascending order.
     */
    public SortedOccSet() {
        map = new TreeMap<>();
    }

    /**
     * Return the number of occurrence of the given key. Regarding with the
     * add() method, the number of occurrence will be the size of a list of same
     * type objects.
     * 
     * @param key:
     *            the given key to find how many times it occurs.
     * @return int: the number of occurrence
     */
    public int getOccurrence(E key) {
        return map.get(key).size();
    }

    /**
     * Return a list of object references which are the same type. The returned
     * list will be copy by using clone() (default way for copying an array in
     * Java), because this class is immutable.
     * 
     * @param key:
     *            the given key to find a reference list which contains a number
     *            of objects that are the same type as the key.
     * 
     * @return List<E>: a list of same type objects.
     */
    public List<E> getOccurList(E key) {
        return copyOccurList(map.get(key));
    }

    /**
     * Adding an object into this Set. The same type of objects will be grouped
     * into a list. The size of this list will be the number of occurrence which
     * can be returned by using getOccurrence() method.
     * 
     * @param key:
     *            key object that its type will be the same as the given type
     *            when the Set is initialized.
     */
    public void add(E key) {
        /*
         * The containsKey() method will recall the equal() method of the key to
         * group the same objects in the Set. Therefore, as mentioned, the key
         * need to override the equal() to make sure the equalization of each
         * given key.
         */
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<E>());
        }
        // Find the same key in map, and then add the given key into the list.
        map.get(key).add(key);
    }

    /**
     * Return an iterator object that can allow the collection with the enhanced
     * for-loop. Also, Iterator interface supported by Java is an easier and
     * safer way to offer users access data in collections than for-loop.
     * <p>
     * Note: For Map interface, there are 3 ways supported by Java to access the
     * data of Map, map.keySet(), map.values() and map.entrySet(). For each of
     * them, an iterator object can be returned for traversing Map. The question
     * is how to use them into different situations. Actually, it depends on the
     * below 3 situations:
     * <p>
     * 1) Only for traversing the key. In this case, calling
     * map.keySet().iterator() is recommended. In fact, you can firstly use this
     * method to traverse key set, and using the specific key to get() the
     * value. However, the performance of this is more inefficient than using
     * the third way, map.entrySet().iterator().
     * <p>
     * 2) Only for traversing the value. The map.values().iterator() does work
     * for it.
     * <p>
     * 3) Involving both of key and value. In this case, the efficient way is to
     * return the map.entrySet().iterator(), because it avoids to call the get()
     * method again.
     * <p>
     * Here, the map.keySet().iterator() is used. The reason for this is to keep
     * this class as immutable, because if it returns the entrySet, the
     * references of values will be exposed to the outside.
     * 
     * @return Iterator<E>: the iterator of the Set.
     */
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    /**
     * Copying a list references for keeping it immutable
     * 
     * @param key:
     *            key object.
     * @return List<E>: copied list.
     * @throws ClassCastExceptions:
     *             this method may throw ClassCastExceptions because of force
     *             casting from Object to List.
     */
    @SuppressWarnings("unchecked")
    private List<E> copyOccurList(ArrayList<E> value) {
        /*
         * The above annotation indicates that the named compiler warnings
         * should be suppressed in the annotated element. Otherwise, compiler
         * will give an warning error.
         */
        return (List<E>) value.clone();
    }

    /**
     * To find an object by given key implemented Occurrence interface.
     * 
     * @see Occurrence
     * 
     * @param key:
     *            Occurrence object
     * @return E: an object which has the same occurrence as the given key
     */
    public E findByOccurence(Occurrence key) {
        return findByOccurence(null, key);
    }

    /**
     * Overloading the findByOccurence() method, to extend the find feature for
     * avoiding duplicated result.
     * <p>
     * This is specialized for finding another pair of a card type in this
     * Project.
     *
     * @param unCheckedKey:
     *            unchecked result which has been found.
     * @param key:
     *            Occurrence object
     * @return E: an object which has the same occurrence as the given key
     */
    public E findByOccurence(E unCheckedKey, Occurrence key) {
        /*
         * The reason for using map.entrySet().iterator() has given in the above
         * iterator() method's annotation. Please check it out.
         */
        Iterator<Entry<E, ArrayList<E>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<E, ArrayList<E>> entry = iterator.next();
            E another = entry.getKey();
            int occurence = entry.getValue().size();
            /*
             * if another is not equal with the unchecked one, and the
             * occurrence of another is equal with the given number of
             * occurrence, return this object.
             */
            if (!another.equals(unCheckedKey)
                    && occurence == key.getOccurrence()) {
                return another;
            }
        }
        return null;
    }

    /**
     * Return a value which is in the first place of the Set.
     * <p>
     * This is specialized for selecting the first one as the players who hold
     * the same highest card type.
     * 
     * @return List<E>: the top objects.
     */
    public List<E> findPeak() {
        List<E> topList = null;
        for (ArrayList<E> value : map.values()) {
            topList = copyOccurList(value);
            break;
        }
        return topList;
    }

    /**
     * Releasing the class references.
     */
    public void clear() {
        map.clear();
    }
}


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *         StudentID: 671205
 *
 * @ClassName: OccurSortedSet
 * 
 * 
 * @param <E>
 *            The element type of this Set. Oct 6, 2015
 * 
 * @Description: equal(), compareTo()
 */
public final class SortedOccSet<E> {
	private Map<E, ArrayList<E>> map;

	public SortedOccSet() {
		map = new TreeMap<>();
	}

	public int getOccurrence(E key) {
		return map.get(key).size();
	}

	public void clear() {
		map.clear();
	}
	
	public List<E> getOccurList(E key) {
		return copyOccurList(key);
	}

	public void add(E key) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(key);
	}

	public boolean contains(Object key) {
		return map.containsKey(key);
	}

	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	public int size() {
		return map.size();
	}

	@SuppressWarnings("unchecked")  // this method may throw ClassCastExceptions!
	private List<E> copyOccurList(E key) {
		return (List<E>) (map.get(key)).clone();
	}

	public E findByOccurence(Occurrence key) {
		return findByOccurence(null, key);
	}

	public E findByOccurence(E unCheckedKey, Occurrence key) {
		Iterator<E> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			E another = iterator.next();
			if (!another.equals(unCheckedKey)
					&& getOccurrence(another) == key.getOccurrence()) {
				return another;
			}
		}
		return null;
	}
}

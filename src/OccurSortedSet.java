
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
 *            Oct 6, 2015
 * 
 * @Description: equal(), compareTo()
 */
public final class OccurSortedSet<E> {
	private Map<E, ArrayList<E>> map;

	public OccurSortedSet() {
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

	@SuppressWarnings("unchecked")
	private List<E> copyOccurList(E key) {
		return (List<E>) (map.get(key)).clone();
	}
}

/**
 * 
 * @author Yu Zhang
 * 
 *         LoginID: zhangy10
 * 
 *
 * @ClassName Occurrence
 * 
 *            Oct 4, 2015
 * 
 * @Description This interface is combined with the customized collection,
 *              SortedOccSet, to offer a way that calculates the occurrence of
 *              the same objects in a given list.
 *              <p>
 *              By using the idea of hash to design this kind of data structure
 *              is to offer a better performance with linear time-consuming than
 *              iterating a list to find the occurrence of the same objects.
 * 
 * @see SortedOccSet
 */
public interface Occurrence {

    /**
     * The concrete class needs to implement this method to return the number of
     * occurrence.
     * <p>
     * By using the given number of occurrence as key finds the relevant value
     * from Set.
     * 
     * @return int: the number of occurrence of the same objects.
     */
    public int getOccurrence();

}

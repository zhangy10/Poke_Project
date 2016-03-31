/**
 * This interface is combined with the customized collection, SortedOccSet, to
 * offer a way that calculates the occurrence of the same objects in a given
 * list.
 * <p>
 * By using the idea of hash to design this kind of data structure is to offer a
 * better performance with linear time-consuming than iterating a list to find
 * the occurrence of the same objects.
 * <p>
 * Note: The reason for designing the Interface is to adapt different input
 * types which will use the feature of finding object by the given occurrence in
 * the SortedOccSet. In this project, there is only one case that will implement
 * this interface for identifying the CardType by the given occurrence of the
 * Rank. However, from OOP design perspective, using Interface to adapt multiple
 * types is recommended.
 * 
 * @author Yu Zhang
 * 
 * @ClassName Occurrence
 * 
 *            Oct 4, 2015
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

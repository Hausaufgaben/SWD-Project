import java.util.HashSet;
import java.util.Iterator;

/**
 * <code>LimitedHashSet</code> is <code>HashSet</code> object with limits that 
 * each element must contain its values within a given range and total amount 
 * of elements only up to certain amount.
 * 
 * @author Purit Thong-On
 * @author Piyaphat Russamitinakornkul
 * @version 1
 * @since 31/05/2015
 * @param <E> Template arguments
 */
public class LimitedHashSet<E extends Comparable<E>> extends HashSet<E>{
    /*****************************
     * 1. Class data member here *
     ****************************/
    private E UPPER_LIMIT;
    private E LOWER_LIMIT;
    private int NUMBER_LIMIT;
    
    /*******************
     * 2. Constructors *
     *******************/
    /**
     * Creates a <code>LimitedHashSet</code> object for storing distinct elements 
     * up to <code>limit</code> from <code>lower</code> to <code>upper</code>.
     * @param lower The lowest object value this <code>HashSet</code> allows
     * @param upper The highest object value this <code>HashSet</code> allows
     * @param limit The maximum number of elements this <code>HashSet</code> can handle
     */
    public LimitedHashSet(E lower, E upper, int limit) {
        super();
        
        LOWER_LIMIT = lower;
        UPPER_LIMIT = upper;
        NUMBER_LIMIT = limit;
    }
    
    /**
     * Creates a <code>LimitedHashSet</code> object for storing distinct elements 
     * up to <code>limit</code> from <code>lower</code> to <code>upper</code>.
     * @param lower The lowest object value this <code>HashSet</code> allows
     * @param upper The highest object value this <code>HashSet</code> allows
     * @param limit The maximum number of elements this <code>LimitedHashSet</code> can handle
     * @param elements A given list of initial elements for this <code>LimitedHashSet</code>
     */
    public LimitedHashSet(E lower, E upper, int limit, E[] elements) {
        LOWER_LIMIT = lower;
        UPPER_LIMIT = upper;
        NUMBER_LIMIT = limit;
        
        for(E i: elements)
            add(i);
    }
    
    /********************
     * 3. Adder Methods *
     ********************/
    /**
     * Adds a element to this <code>LimitedHashSet</code>.
     * @param element A given object to be added
     * @return <code>true</code>, if the number could be added to this set.
     */
    @Override
    public boolean add(E element) {
        if(size() == NUMBER_LIMIT ||
                element.compareTo(LOWER_LIMIT) < 0 ||
                element.compareTo(UPPER_LIMIT) > 0)
            return false;
        
        return super.add(element);
    }
    
    /*********************
     * 4. Setter Methods *
     *********************/
    /**
     * Either expands or shrinks the capacity of this <code>LimitedHashSet</code>.
     * @param newLimit A new capacity of this <code>LimitedHashSet</code>.
     */
    public void changeLimit(int newLimit) {
        NUMBER_LIMIT = newLimit;
    }

    /**
     * Either expands or shrinks the lower limit value of this <code>LimitedHashSet</code>.
     * @param newBound A new lower limit value
     */
    public void changeLowerBound(E newBound) {
        LOWER_LIMIT = newBound;
    }

    /**
     * Either expands or shrinks the upper limit value of this <code>LimitedHashSet</code>.
     * @param newBound A new upper limit value
     */
    public void changeUpperBound(E newBound) {
        UPPER_LIMIT = newBound;
    }

    /**
     * Replaces an old element in this <code>LimitedHashSet</code> with a given new element.
     * @param oldElement An element to be removed
     * @param newElement An element to be replaced
     * @return <code>true</code>, if <code>oldElement</code> could be removed and <code>newElement</code> could be added
     */
    public boolean changeNumber(E oldElement, E newElement) {
        if(remove(oldElement))
            return add(newElement);
        return false;
    }

    /*********************
     * 5. Getter Methods *
     *********************/
    /**
     * Getter method for obtaining total amount of elements allowed on this <code>LimitedHashSet</code>.
     * @return The value of <code>limit</code> of this <code>LimitedHashSet</code>
     */
    public int getLimit() {
        return NUMBER_LIMIT;
    }
    
    /**
     * Getter method for obtaining to the minimum value allowed on this <code>LimitedHashSet</code>.
     * @return The value of the lowest number allowed
     */
    public E getLowerBound() {
        return LOWER_LIMIT;
    }
    
    /**
     * Getter method for obtaining to the maximum value allowed on this <code>LimitedHashSet</code>.
     * @return The value of the highest element allowed
     */
    public E getUpperBound() {
        return UPPER_LIMIT;
    }
    
    /**
     * Creates an array of all elements in this <code>LimitedHashSet</code>.
     * @return An array of all elements
     */
    @Override
    public E[] toArray() {
        if(isEmpty())
            return null;
        
        E[] results = (E[])(new Object[size()]);
        int j = 0;
        Iterator<E> i = iterator();
        
        while(i.hasNext())
            results[j++] = i.next();
        
        return results;
    }
}

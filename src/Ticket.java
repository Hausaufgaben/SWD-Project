import java.util.Iterator;

/**
 * Creates a <code>Ticket</code> object containing numbers for playing a lottery 
 * game. The numbers on the ticket is within a certain range. In addition, there 
 * is an amount of number which the ticket must contain to become a valid ticket.
 * 
 * @author Purit Thong-On
 * @author Piyaphat Russamitinakornkul
 * @version 2
 * @since 26/05/2015
 */
public class Ticket extends LimitedHashSet<Integer> {
    /*******************
     * 1. Data Members *
     ******************/
    private String name;

    /*******************
     * 2. Constructors *
     *******************/
    /**
     * Creates a blank ticket without numbers, but with a <code>limit</code> of 
     * numbers required for this <code>Ticket</code> to be valid, and each 
     * number within a range of <code>[lower, upper]</code>.
     * 
     * @param lower The lowest number limit
     * @param upper The highest number limit
     * @param limit Total amount of numbers required by this <code>Ticket</code>
     * @param name A name of the holder of this ticket
     */
    public Ticket(int lower, int upper, int limit, String name) {
        super(lower, upper, limit);
        this.name = name;
    }
    
    /**
     * Creates a ticket with given numbers, but with a <code>limit</code> of 
     * numbers required for this <code>Ticket</code> to be valid, and each 
     * number within a range of <code>[lower, upper]</code>.
     * 
     * @param lower The lowest number limit
     * @param upper The highest number limit
     * @param limit Total amount of numbers required by this <code>Ticket</code>
     * @param numbers A list of numbers to be added to a ticket
     * @param name A name of the holder of this ticket
     */
    public Ticket(int lower, int upper, int limit, Integer[] numbers, String name) {
        super(lower, upper, limit, numbers);
    }
    
    /*********************
     * 3. Setter Methods *
     *********************/
    /**
     * Changes the <code>name</code> of the holder of this <code>Ticket</code> 
     * with a given <code>newName</code>.
     * 
     * @param newName A new name to be given to this <code>Ticket</code>
     */
    public void changeName(String newName) {
        name = newName;
    }

    /***********************
     * 4. Property Methods *
     ***********************/
    /**
     * Determines whether the ticket is valid for playing a lottery game.
     * 
     * @return <code>true</code>, if the ticket is valid for playing a lottery game.
     */
    public boolean isValidTicket() {
        return size() == getLimit();
    }
    
    /*********************
     * 5. Getter Methods *
     *********************/
    /**
     * Getter method for obtaining the name of the holder of this <code>Ticket</code>.
     * 
     * @return A <code>String</code> of the name of the holder of this object 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Creates a string of all numbers in this <code>Ticket</code> in a single 
     * line with a 0 digit in front.
     * 
     * @return A String representation of this <code>Ticket</code> object
     */
    @Override
    public String toString() {
        String results = "";
        int space = Integer.toString(getUpperBound()).length();
        Iterator<Integer> i = iterator();

        while(i.hasNext())
            results += String.format("%0" + space + "d ", i.next());
        
        return results.trim();
    }
}

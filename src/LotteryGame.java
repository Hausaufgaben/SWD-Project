import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creates an object of game for playing lottery.
 * 
 * @author Purit Thong-On
 * @author Piyaphat Russamitinakornkul
 * @version 1
 * @since 26/05/2015
 */
public class LotteryGame {
    /*****************************
     * 1. Class data member here *
     ****************************/
    final private int UPPER_LIMIT;
    final private int LOWER_LIMIT;
    final private int NUMBER_OF_ENTRY;
    final private String MENU_HEADER = "=== Lottery Menu ===";
    final private String MENU_1 = "1 Enter ticket";
    final private String MENU_2 = "2 Lottery drawing";
    final private String MENU_3 = "3 Show result";
    final private String MENU_0 = "0 Quit";
    final private String MENU_PROMPT = "Please enter (0-3): ";
    final private String IO_ERROR = "Error! Cannot read an input.";
    final private String INVALID_MENU = "Please enter a valid menu number!";
    final private String ENTER_NAME = "Please enter a name of the holder of this ticket: ";
    final private String ENTER_TICKET = "Please enter 6 numbers, all between 1 and 49: ";
    final private String TICKET_CONFIRMED = "The ticket has been added.";
    final private String INPUT_ERROR = "Error! Invalid ticket numbers.";
    final private String CURRENT_DRAWN = "Drawn Numbers: ";
    final private String LOTTO_HEADER = "Lottery Drawing Results:";
    final private String THANK_YOU = "Thank you for playing!";
    
    private ArrayList<Ticket> tickets;
    private Jackpot jackpot;
    private boolean lotteryDrawn;
    private int longestName;
    BufferedReader input;

    /*******************
     * 2. Constructors *
     *******************/
    /**
     * Sets up a <code>LotteryGame</code> with given rules of range of numbers 
     * allowed and total numbers required per each ticket.
     * <code>limit</code> from <code>lower</code> to <code>upper</code>.
     * @param lower The lowest number for each ticket this game allows
     * @param upper The highest number for each ticket this game allows
     * @param limit The total amount of numbers required for a valid ticket
     */
    public LotteryGame(int lower, int upper, int limit) {
        LOWER_LIMIT = lower;
        UPPER_LIMIT = upper;
        NUMBER_OF_ENTRY = limit;
        
        lotteryDrawn = false;
        input = new BufferedReader(new InputStreamReader(System.in));
        tickets = new ArrayList<>();
        longestName = 4;
    }
    
    /*********************
     * 3. Member Methods *
     *********************/
    /**
     * This method is called too allow this <code>LotteryGame</code> object to 
     * start running.
     */
    public void start() {
        int choice = 0;
        
        do {
            try {
                choice = menu();

                switch(choice) {
                case 1:
                    if(!lotteryDrawn)
                        addTicket();
                    break;
                case 2:
                    generateJackpot();
                    break;
                case 3:
                    if(lotteryDrawn && (!tickets.isEmpty()))
                        showTickets();
                    else
                        System.out.println(INVALID_MENU);
                    break;
                case 0:
                    System.out.println(THANK_YOU);
                    break;
                default:
                    System.out.println(INVALID_MENU);
                }
            }
            catch(IOException e) {
                System.out.println(IO_ERROR);
            }
        } while(choice != 0);
    }
    
    /**
     * Allows the player to choose what to do in a game by entering a number 
     * according to the displayed menu with various options.
     * @return A number which the user inputs
     * @throws IOException If the number is not valid, this function returns -1 instead
     */
    public int menu() throws IOException {
        try {
            System.out.println(MENU_HEADER);
            if(!lotteryDrawn)
                System.out.println(MENU_1);
            System.out.println(MENU_2);
            
            if(lotteryDrawn && (!tickets.isEmpty()))
                System.out.println(MENU_3);
            
            System.out.println(MENU_0);
            System.out.print(MENU_PROMPT);
            return Integer.parseInt(input.readLine());
        }
        catch(NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * This method allows the play to add six numbers as a new ticket for a game.
     */
    public void addTicket() {
        String[] numbers;
        String name;
        Ticket newTicket;
        
        try {
            System.out.print(ENTER_NAME);
            name =  input.readLine();
            System.out.println(ENTER_TICKET);
            numbers = input.readLine().split("\\s+");
            
            newTicket = new Ticket(LOWER_LIMIT, UPPER_LIMIT, NUMBER_OF_ENTRY, name);
            if(name.length() > longestName)
                longestName = name.length();

            for(String i : numbers)
                newTicket.add(Integer.parseInt(i));

            if(newTicket.isValidTicket())
                tickets.add(newTicket);
            else
                throw new Exception();
            System.out.println(TICKET_CONFIRMED);
        }
        catch (Exception e) {
            System.out.println(INPUT_ERROR);
        }
    }
    
    /**
     * This method allows the player to draw six numbers as lottery results.
     */
    public void generateJackpot() {
        lotteryDrawn = true;
        jackpot = new Jackpot(LOWER_LIMIT, UPPER_LIMIT, NUMBER_OF_ENTRY);
        
        System.out.println(CURRENT_DRAWN);
        System.out.println(jackpot);
    }
    
    /**
     * This method allows the player to check all the hits of each ticket and 
     * view the results.
     */
    public void showTickets() {
        Iterator<Ticket> i = tickets.iterator();
        Ticket current;
        int totalDigits = -(Integer.toString(UPPER_LIMIT).length() + 1);
        
        System.out.println(LOTTO_HEADER + "\n" +
                           CURRENT_DRAWN + "\n" +
                           jackpot);
        System.out.printf("| %-" + longestName + "s| %" +
                Integer.toString((totalDigits * NUMBER_OF_ENTRY)) +
                "s| Hits\n", "Name", "Numbers");
        
        while(i.hasNext()) {
            current = i.next();
            System.out.printf("| %-" +
                              longestName +
                              "s: %s | %02d\n",
                              current.getName(),
                              current,
                              jackpot.validateHits(current));
        }
    }
}

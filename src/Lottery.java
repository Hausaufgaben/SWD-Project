/**
 * A main function for starting a game is located in this class.
 * 
 * @author Purit Thong-On
 * @author Piyaphat Russamitinakornkul
 * @version 1
 * @since 26/05/2015
 */
class Lottery {
    final private static int LOWEST = 1;
    final private static int HIGHEST = 49;
    final private static int ENTRIES = 6;
    
    /**
     * Main function of the game.
     * @param args Command-Line Arguments
     */
    public static void main(String[] args) {
        LotteryGame game = new LotteryGame(LOWEST, HIGHEST, ENTRIES);
        
        game.start();
    }
}
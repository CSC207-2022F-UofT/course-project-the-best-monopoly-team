package TreeHandlers.MainTreeNodeLogic;

import TreeHandlers.AuctionNodeLogic.AuctionTreeNodeLogic;
import TreeHandlers.GeneralGameLogic;

/**
 * This class handles the logic for the main tree nodes.
 */
public class MainTreeNodeLogic extends GeneralGameLogic {
    static int[] mainStates = new int[2];
    static String diceRoll;


    public MainTreeNodeLogic(String name){
        super(name);
    }

    /**
     * When states are already known.
     * @param states - The states of the program from the loaded game
     */
    public static void initializeStates(int[] states){
        mainStates = states;
    }
    /**
     * This method is used to initialize the auctionTreeHandler to prepare for an auction scenario.
     */
    public void setupAuction(){
        AuctionTreeNodeLogic temp = new AuctionTreeNodeLogic("temp");
        temp.initialize();
    }
}

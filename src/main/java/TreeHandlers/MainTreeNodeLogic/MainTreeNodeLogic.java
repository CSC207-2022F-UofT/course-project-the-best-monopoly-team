package TreeHandlers.MainTreeNodeLogic;

import TreeHandlers.AuctionNodeLogic.AuctionTreeNodeLogic;
import TreeHandlers.GeneralGameLogic;

public class MainTreeNodeLogic extends GeneralGameLogic {
    static int[] mainStates = new int[2];
    static String diceRoll;


    public MainTreeNodeLogic(String name){
        super(name);
    }

    /**
     * When states are already known.
     * @param states
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

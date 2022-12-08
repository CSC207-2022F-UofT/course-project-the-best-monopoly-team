package NodeLogic.MainTreeNodeLogic;

import NodeLogic.AuctionNodeLogic.AuctionTreeNodeLogic;
import NodeLogic.GeneralGameLogic;

public class MainTreeNodeLogic extends GeneralGameLogic {


    /*
    STATE ARRAY FORMATTING FOR SAVING:
[0]: number of players
[1]: game length (turn where game ends)
[2]: game mode
[3]: current turn
[4]: current player
[5]: Roll


IN GAME STATE ARRAY FORMATTING:
[0]: Confirmation node
[1]: Roll
[2]: Game mode
[3]: Turn counter
[4]: Game Length

     */
    static int[] mainStates = new int[5];
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
    public static int[] getStates(){
        return mainStates;
    }
}

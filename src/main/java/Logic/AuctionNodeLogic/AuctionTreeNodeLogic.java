package Logic.AuctionNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.Property;
import Entities.State;
import Logic.GeneralGameLogic;
import Logic.GameLogic;

import java.util.List;

/**
 * This class handles the logic for the auction tree nodes.
 */
public class AuctionTreeNodeLogic extends GeneralGameLogic {
    final int LOW_OPTION = 20;
    final int MEDIUM_OPTION = 80;
    final int HIGH_OPTION = 160;
    static int[] auctionStates;
    static Player playerWon;
    static Property biddingProperty;
    static int potIndex;
    static int currentPlayerIndex;
    static int auctionComplete = -1;

    public AuctionTreeNodeLogic(String name){
        super(name);
    }

    public static void array_init(int playerLength){
        auctionStates = new int[playerLength + 1];
    }

    /**
     * Gets the current state object of the tree when the auction is not yet complete
     * @return a state object representing the program
     */
    public State beforeLogic(){
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        currentPlayerIndex = getCurrentPlayerIndex();
        //loop through the players till we get a player who has not folded
        do {
            changePlayers();
        }while (auctionStates[getCurrentPlayerIndex()] == 1);
        currentState.setId(((GeneralGameLogic)gameLogicInteractor.getCurrentTree().getUseCase()).getName());
        return currentState;
    }

    /**
     * Gets the current state object of the tree when the auction bidding process has finished.
     * @param currentState a State object representing the current state of the game.
     * @return a State object of the tree when the auction bidding process has finished.
     */
    public State afterLogic(State currentState){
        //if the auction is still going, go back to the beginning of the tree
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        if (auctionComplete == -1){
            gameLogicInteractor.setCurrentTreeToMaxParent();
            currentState = gameLogicInteractor.getCurrentState();
        }
        return currentState;
    }
    /**
     * Check if the auction is complete
     * @return an integer representing the index of the player in the array who won.
     * if no one has won, return -1.
     */
    public int checkAuction(){
        int auctionFinishedCounter = 0;
        int playerIndex = 0;

        //check if everyone but one player has folded
        for (int i = 0; i<auctionStates.length - 1; i++){
            if (auctionStates[i] == 0){
                auctionFinishedCounter += 1;
                playerIndex = i;
            }
        }
        if (auctionFinishedCounter == 1){
            return playerIndex;
        }
        return -1;
    }

    /**
     * Initialize this tree to prepare for an auction
     */
    public void initialize() {
        Board board = getBoard();
        List<Player> players = getPlayers();
        int returnPlayerIndex = getReturnPlayerIndex();
        auctionComplete = -1;
        biddingProperty = (Property) board.getPlayerCell(players.get(returnPlayerIndex));
        auctionStates = new int[players.size() + 1];
        potIndex = auctionStates.length - 1;
    }
}

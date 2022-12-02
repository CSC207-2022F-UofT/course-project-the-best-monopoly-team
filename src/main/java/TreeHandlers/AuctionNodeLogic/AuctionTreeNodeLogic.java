package TreeHandlers.AuctionNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.Property;
import Entities.State;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;

import java.util.List;

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
    public AuctionTreeNodeLogic(){}

    public static void array_init(int playerLength){
        auctionStates = new int[playerLength + 1];
    }



    /**
     * Gets the current state object of the tree when the auction is not yet complete
     * @return a state object representing the program
     */
    public State getState(){
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setPlayer(currentPlayer);
        currentState.setDescription(currentPlayer.getName());
        currentState.addOptions(""+LOW_OPTION);
        currentState.addOptions(""+MEDIUM_OPTION);
        currentState.addOptions(""+HIGH_OPTION);
        currentState.addOptions("Fold");
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.setBiddingProperty(biddingProperty);
        return currentState;
    }
    public State beforeLogic(){
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        currentPlayerIndex = getCurrentPlayerIndex();
        //loop through the players every time one gives their choice
        do {
            changePlayers();
        }while (auctionStates[getCurrentPlayerIndex()] == 1);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        return currentState;
    }

    public State afterLogic(State currentState){
        //if the auction is still going, go back to the beginning of the tree
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        if (auctionComplete == -1){
            gameLogicInteractor.setCurrentTreeToMaxParent();
            currentState = getState();
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

package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

/**
 * This tree handler handles the input during the auction phase of the program.
 */
public class AuctionTreeHandler extends TreeHandler {
    int[] auctionStates;
    Player playerWon;
    Property biddingProperty;
    int potIndex;
    int currentPlayerIndex;
    int auctionComplete = -1;

    public AuctionTreeHandler(int playerLength){
        auctionStates = new int[playerLength + 1];
    }

    /**
     * This method handles the choices of the user in the auction game part of the program.
     * <p>
     */
    public State handleInput(){
        State currentState = new State();
        currentPlayerIndex = getCurrentPlayerIndex();
        //loop through the players every time one gives their choice
        do {
            changePlayers();
        }while (auctionStates[getCurrentPlayerIndex()] == 1);

        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "LowOption":
                auctionStates[potIndex] += LOW_OPTION;
                break;
            case "MediumOption":
                auctionStates[potIndex] += MEDIUM_OPTION;
                break;
            case "HighOption":
                auctionStates[potIndex] += HIGH_OPTION;
                break;
            case "Fold":
                //fold indicator
                auctionStates[currentPlayerIndex] = 1;
                auctionComplete = checkAuction();

                //when the auction is complete
                if (auctionComplete != -1){
                    //give the winner their property
                    playerWon = players.get(auctionComplete);
                    playerWon.addProperty(biddingProperty);
                    playerWon.pay(auctionStates[potIndex]);

                    //return to the main tree and correct player
                    gameLogicInteractor.setCurrentTree(getReturnTree());
                    currentPlayer = players.get(returnPlayerIndex);

                    //mutate state object
                    currentState.setPlayer(playerWon);
                    currentState.setBiddingPot(auctionStates[potIndex]);
                    currentState.addOptions("Ok");
                }
                break;
        }
        //if the auction is still going, go back to the beginning of the tree
        if (auctionComplete == -1){
            gameLogicInteractor.setCurrentTreeToMaxParent();
            currentState = getState();
        }

        return currentState;
    }

    /**
     * Gets the current state object of the tree when the auction is not yet complete
     * @return a state object representing the program
     */
    public State getState(){
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setPlayer(currentPlayer);
        currentState.setDescription(currentPlayer.getName() + " " + descriptionOtherTrees);
        currentState.addOptions(""+LOW_OPTION);
        currentState.addOptions(""+MEDIUM_OPTION);
        currentState.addOptions(""+HIGH_OPTION);
        currentState.addOptions("Fold");
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.setBiddingProperty(biddingProperty);
        return currentState;
    }

    /**
     * Check if the auction is complete
     * @return an integer representing the index of the player in the array who won.
     * if no one has won, return -1.
     */
    private int checkAuction(){
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
        auctionComplete = -1;
        this.biddingProperty = (Property) board.getPlayerCell(players.get(returnPlayerIndex));
        auctionStates = new int[players.size() + 1];
        potIndex = auctionStates.length - 1;
    }

}

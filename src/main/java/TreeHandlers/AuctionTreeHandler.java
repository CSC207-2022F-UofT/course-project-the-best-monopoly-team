package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Player;
import Entities.Property;
import Entities.State;

public class AuctionTreeHandler extends TreeHandler {
    final int LOW_OPTION = 20;
    final int MEDIUM_OPTION = 80;
    final int HIGH_OPTION = 160;
    int[] auctionStates;
    Player playerWon;

    public AuctionTreeHandler(int playerLength){
        auctionStates = new int[playerLength + 1];
    }
    public State handleInput(){
        State currentState = new State();
        int currentPlayerIndex = getCurrentPlayerIndex();
        int potIndex = auctionStates.length - 1;
        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "LowOption":
                auctionStates[potIndex] += LOW_OPTION;
                currentState = super.getInitialState();
                break;
            case "MediumOption":
                auctionStates[potIndex] += MEDIUM_OPTION;
                currentState = super.getInitialState();
                break;
            case "HighOption":
                auctionStates[potIndex] += HIGH_OPTION;
                currentState = super.getInitialState();
                break;
            case "Fold":
                auctionStates[currentPlayerIndex] = 1;
                int auctionComplete = checkAuction();
                if (auctionComplete != -1){
                    playerWon = players.get(auctionComplete);
                    playerWon.addProperty(biddingProperty);
                    playerWon.pay(auctionStates[potIndex]);
                    descriptionOtherTrees = playerWon.getName() + " won the auction for " +auctionStates[potIndex] + " money";
                    gameLogicInteractor.setCurrentTree(getReturnTree());
                    currentPlayer = players.get(returnPlayerIndex);
                    return gameLogicInteractor.handleTree(0);
                }
                break;
        }
        return currentState;
    }

    private int checkAuction(){
        int auctionFinishedCounter = 0;
        int playerIndex = 0;
        for (int i = 0; i<auctionStates.length - 1; i++){
            if (auctionStates[i] == 0){
                auctionFinishedCounter += 1;
                playerIndex = i;
            }
        }
        if (auctionFinishedCounter > 1){
            return playerIndex;
        }
        return -1;
    }

}

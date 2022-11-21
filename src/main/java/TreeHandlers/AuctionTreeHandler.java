package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Player;
import Entities.Property;
import Entities.State;

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
    public State handleInput(){
        State currentState = new State();
        currentPlayerIndex = getCurrentPlayerIndex();
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
                auctionStates[currentPlayerIndex] = 1;
                auctionComplete = checkAuction();
                if (auctionComplete != -1){
                    playerWon = players.get(auctionComplete);
                    playerWon.addProperty(biddingProperty);
                    playerWon.pay(auctionStates[potIndex]);
                    gameLogicInteractor.setCurrentTree(getReturnTree());
                    currentPlayer = players.get(returnPlayerIndex);
                    currentState.setPlayer(playerWon);
                    currentState.setBiddingPot(auctionStates[potIndex]);
                    currentState.addOptions("ok");
                }
                break;
        }
        if (auctionComplete == -1){
            gameLogicInteractor.setCurrentTreeToMaxParent();
            currentState = getState();
        }

        return currentState;
    }
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

    private int checkAuction(){
        int auctionFinishedCounter = 0;
        int playerIndex = 0;
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
    public void initialize() {
        auctionComplete = -1;
        this.biddingProperty = (Property) board.getPlayerCell(players.get(returnPlayerIndex));
        auctionStates = new int[players.size() + 1];
        potIndex = auctionStates.length - 1;
    }

}

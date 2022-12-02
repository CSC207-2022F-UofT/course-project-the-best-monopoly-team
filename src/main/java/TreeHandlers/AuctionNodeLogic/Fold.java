package TreeHandlers.AuctionNodeLogic;

import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.List;

public class Fold extends AuctionTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        List<Player> players = getPlayers();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        int returnPlayerIndex = getReturnPlayerIndex();

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
        }        return afterLogic(currentState);
    }
}

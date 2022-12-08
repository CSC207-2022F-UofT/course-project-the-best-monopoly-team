package NodeLogic.AuctionNodeLogic;

import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import UseCases.NodeLogic;

import java.util.List;

/**
 * This class represents a use case when a player folds during an auction.
 */
public class Fold extends AuctionTreeNodeLogic implements NodeLogic {

    public Fold() {
        super("Fold");
    }


    /**
     * This method handles a case where a player folds during an auction. It returns a State object containing
     * information required to continue the auction process.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object containing information required to continue the auction process.
     */
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        List<Player> players = getPlayers();
        GameLogic gameLogicInteractor = getGameLogicInteractor();

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
            setCurrentPlayer(players.get(returnPlayerIndex));

            //mutate state object
            currentState.setPlayer(playerWon);
            currentState.setBiddingPot(auctionStates[potIndex]);
            currentState.addOptions("Ok");
        }
        return afterLogic(currentState);
    }
}

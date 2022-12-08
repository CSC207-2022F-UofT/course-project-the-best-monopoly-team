package Logic.AuctionNodeLogic;

import Entities.Player;
import Entities.State;
import Logic.GameLogic;
import Logic.NodeLogic;
import Logic.GeneralGameLogic;

/**
 * This class represents the use case where an auction is started.
 */
public class AuctionParentNode extends AuctionTreeNodeLogic implements NodeLogic {
    public AuctionParentNode() {
        super("Auction Tree Parent Node");
    }

    /**
     * This method creates a State object containing relevant information on the auction. This includes the player that
     * is to bid, the options to bid, the property that is being auctioned, and the bidding pot.
     * @param input an integer representing the user's input. This parameter will not be used for this method.
     * @return A State object containing relevant information on the auction.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();
        currentState.setId(((GeneralGameLogic)gameLogicInteractor.getCurrentTree().getUseCase()).getName());
        currentState.setPlayer(currentPlayer);
        currentState.addOptions(""+LOW_OPTION);
        currentState.addOptions(""+MEDIUM_OPTION);
        currentState.addOptions(""+HIGH_OPTION);
        currentState.addOptions("Fold");
        currentState.setBiddingPot(auctionStates[potIndex]);
        currentState.setBiddingProperty(biddingProperty);
        return currentState;
    }
}

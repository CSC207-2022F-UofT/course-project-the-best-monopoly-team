package NodeLogic.AuctionNodeLogic;

import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import UseCases.NodeLogic;
import NodeLogic.GeneralGameLogic;

public class AuctionParentNode extends AuctionTreeNodeLogic implements NodeLogic {
    public AuctionParentNode() {
        super("Auction Tree Parent Node");
    }

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

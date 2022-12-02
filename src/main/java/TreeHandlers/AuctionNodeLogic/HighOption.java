package TreeHandlers.AuctionNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class HighOption extends AuctionTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        auctionStates[potIndex] += HIGH_OPTION;
        return afterLogic(currentState);
    }
}

package TreeHandlers.AuctionNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class MediumOption extends AuctionTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        auctionStates[potIndex] += MEDIUM_OPTION;
        return afterLogic(currentState);
    }
}

package Logic.AuctionNodeLogic;

import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case when a player chooses to bid low in an auction.
 */
public class LowOptionUseCase extends AuctionTreeNodeLogic implements NodeLogic {
    public LowOptionUseCase() {
        super("Low Option");
    }

    /**
     * This method a State object containing necessary information after the low bid is attempted.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return A State object containing necessary information after the low bid is attempted.
     */
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        auctionStates[potIndex] += LOW_OPTION;
        return afterLogic(currentState);
    }
}

package Logic.AuctionNodeLogic;

import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case when a player chooses to make a middle bid in an auction.
 */
public class MediumOptionUseCase extends AuctionTreeNodeLogic implements NodeLogic {

    public MediumOptionUseCase() {
        super("Medium Option");
    }

    /**
     * This method a State object containing necessary information after the medium bid is attempted.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return A State object containing necessary information after the medium bid is attempted.
     */
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        auctionStates[potIndex] += MEDIUM_OPTION;
        return afterLogic(currentState);
    }
}

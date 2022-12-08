package UseCases.Logic.AuctionNodeLogic;

import Entities.State;
import UseCases.Logic.NodeLogic;

/**
 * This class represents the use case when a player chooses to bid high in an auction.
 */
public class HighOptionUseCase extends AuctionTreeNodeLogic implements NodeLogic {

    public HighOptionUseCase() {
        super("High Option");
    }

    /**
     * This method a State object containing necessary information after the high bid is attempted.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return A State object containing necessary information after the high bid is attempted.
     */
    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        auctionStates[potIndex] += HIGH_OPTION;
        return afterLogic(currentState);
    }
}

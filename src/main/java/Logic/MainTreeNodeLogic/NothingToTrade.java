package NodeLogic.MainTreeNodeLogic;

import Entities.*;
import UseCases.NodeLogic;

/**
 *  This class represents a use case where the current player has nothing to trade.
 */
public class NothingToTrade extends MainTreeNodeLogic implements NodeLogic{
    public NothingToTrade() {
        super("Trader/Tradee Has No Properties");
    }

    /**
     * This method creates and returns a State object containing information required to proceed in the game when the
     * current player has nothing to trade.
     * @param input An integer representing the user's input. However, this parameter will not be used for this use case.
     * @return A State object containing the options and other information required to proceed with the game when the
     * current player has nothing to trade.
     */
    @Override
    public State create_state(int input) {

        State currentState = new State();
        currentState.setId(getName());
        currentState.addOptions("Ok");

        return currentState;
    }
}

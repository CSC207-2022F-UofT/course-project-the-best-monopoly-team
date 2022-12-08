package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.NodeLogic;


/**
 * This class represents a use case where two Player instances trade with each other. The class handles the actions
 * required to successfully set up a trade.
 */
public class TradeUseCase extends MainTreeNodeLogic implements NodeLogic {

    public TradeUseCase() {
        super("Trade");
    }

    /**
     * This method creates a State object and adds all the possible list of player that the current player can trade
     * with into the State object that was created and returns it.
     * @param input An integer that represents the input of the user. However, this parameter is not used in this method.
     * @return A State object containing options which are a list of all possible players that the current player can
     * trade with. This excludes the current player.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(getName());
        //provide a list of all possible players considering the current player is not an option
        addPlayersState(currentState);
        return currentState;

    }
}

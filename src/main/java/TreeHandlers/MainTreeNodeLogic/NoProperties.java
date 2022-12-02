package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

/**
 * This class represents the use case where the targeted player does not have any properties.
 */
public class NoProperties extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object and sets a description showing that there are not properties available and
     * the required option, "Ok", to the State object.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object containing a description alerting the current player that are not properties available.
     * The returned State object also adds the "Ok" option.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setDescription("You have no properties :(");
        currentState.addOptions("Ok");

        return currentState;
    }
}
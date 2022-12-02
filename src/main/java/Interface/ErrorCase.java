package Interface;

import Entities.State;
import Interface.NodeLogic;
import TreeHandlers.GeneralGameLogic;

/**
 * This class represents the use case where there is an error.
 */
public class ErrorCase extends GeneralGameLogic implements NodeLogic {
    /**
     * This method creates a State object and adds an option which will show that something went wrong during execution.
     * @param input An integer representing the user's input. However, this parameter will not be used in this method.
     * @return A State object containing the added "Something went wrong" option.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId("Information");
        currentState.addOptions("Something went wrong");

        return currentState;
    }
}
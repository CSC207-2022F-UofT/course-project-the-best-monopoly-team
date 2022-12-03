package TreeHandlers.MainTreeNodeLogic;

import Entities.State;
import Interface.NodeLogic;

/**
 * This class represents the use case where the player has already rolled the dice.
 */
public class AlreadyRolled extends MainTreeNodeLogic implements NodeLogic {

    public AlreadyRolled() {
        super("Already Rolled");
    }

    /**
     * This method creates a State object containing information required to proceed when the current player has already
     * rolled the dice.
     * @param input An integer that represents the user's input. However, this will not be used for this method.
     * @return A State object containing information required to proceed when the current player has already rolled the
     * dice.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId(getName());
        currentState.addOptions("Ok");

        return currentState;
    }
}
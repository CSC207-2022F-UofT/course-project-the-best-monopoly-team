package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interface.NodeLogic;

/**
 * This class represents the use case where the current player chooses to unmortgage a chosen property.
 */
public class UnMortgage extends MainTreeNodeLogic implements NodeLogic {
    public UnMortgage() {
        super("Un-Mortgage property");
    }

    //TODO ADD JAVADOC TO THIS METHOD AFTER THIS IS IMPLEMENTED.
    @Override
    public State create_state(int input) {

        State currentState = new State();
        currentState.setId(getName());
        //TODO ADD FUNCTIONALITY

        return currentState;
    }
}
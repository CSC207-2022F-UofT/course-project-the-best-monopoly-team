package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interface.NodeLogic;

public class UnMortgage extends MainTreeNodeLogic implements NodeLogic {
    public UnMortgage() {
        super("Un-Mortgage property");
    }

    @Override
    public State create_state(int input) {

        State currentState = new State();
        currentState.setId(getName());
        //TODO ADD FUNCTIONALITY

        return currentState;
    }
}
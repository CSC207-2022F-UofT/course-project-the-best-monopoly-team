package TreeHandlers;

import Entities.State;

public class ErrorCase extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId("Information");
        currentState.addOptions("Something went wrong");

        return currentState;
    }
}
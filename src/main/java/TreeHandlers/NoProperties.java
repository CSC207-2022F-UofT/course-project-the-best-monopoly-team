package TreeHandlers;

import Entities.State;

public class NoProperties extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setDescription("You have no properties :(");
        currentState.addOptions("Ok");

        return currentState;
    }
}
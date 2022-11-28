package TreeHandlers;

import Entities.State;
public class AlreadyRolled extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.addOptions("Ok");

        return currentState;
    }
}
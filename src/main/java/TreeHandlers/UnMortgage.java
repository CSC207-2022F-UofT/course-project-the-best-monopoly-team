package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

public class UnMortgage extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //TODO ADD FUNCTIONALITY

        return currentState;
    }
}
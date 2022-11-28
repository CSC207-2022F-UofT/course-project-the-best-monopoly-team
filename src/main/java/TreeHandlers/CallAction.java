package TreeHandlers;

import Entities.State;

public class CallAction extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        //gets the response from rolling on a space
        currentState.setRoll(diceRoll);
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }
}
package NodeLogic.MainTreeNodeLogic;

import Entities.State;
import UseCases.NodeLogic;

public class CallAction extends MainTreeNodeLogic implements NodeLogic {
    public CallAction() {
        super("Perform Action");
    }

    @Override
    public State create_state(int input) {
        State currentState = new State();
        String answer = getAnswer();

        currentState.setId(getName());
        //gets the response from rolling on a space
        currentState.setRoll(diceRoll);
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }
}
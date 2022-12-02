package TreeHandlers.MainTreeNodeLogic;

import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class CallAction extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        String answer = getAnswer();
        GameLogic gameLogicInteractor = getGameLogicInteractor();

        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //gets the response from rolling on a space
        currentState.setRoll(diceRoll);
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }
}
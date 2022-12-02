package TreeHandlers.MainTreeNodeLogic;

import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class AlreadyRolled extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.addOptions("Ok");

        return currentState;
    }
}
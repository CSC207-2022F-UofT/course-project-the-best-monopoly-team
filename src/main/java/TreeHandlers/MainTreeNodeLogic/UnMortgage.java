package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class UnMortgage extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //TODO ADD FUNCTIONALITY

        return currentState;
    }
}
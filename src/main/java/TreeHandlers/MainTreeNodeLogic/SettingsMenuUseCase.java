package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class SettingsMenuUseCase extends MainTreeNodeLogic implements NodeLogic {

    public State create_state(int input){
        GameLogic gameLogicInteractor = getGameLogicInteractor();

        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setBackEnable(true);
        //options for the settings menu
        addSwitchOptions(currentState);
        return currentState;
    }

}

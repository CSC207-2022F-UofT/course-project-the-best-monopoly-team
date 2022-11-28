package TreeHandlers;

import Entities.State;

public class SettingsMenuUseCase extends TreeHandler implements NodeLogic{

    public State create_state(int input){
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setBackEnable(true);
        //options for the settings menu
        addSwitchOptions(currentState);
        return currentState;
    }

}

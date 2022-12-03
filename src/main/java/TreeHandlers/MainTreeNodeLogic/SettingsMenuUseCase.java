package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

/**
 * This class represents a use case where the settings menu is to be set up.
 */
public class SettingsMenuUseCase extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object for the initialization of the settings menu.
     * @param input An integer representing the user's input.
     * @return A State object set up for the display of the settings menu.
     */
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

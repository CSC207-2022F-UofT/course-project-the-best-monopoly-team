package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

/**
 * This class represents the use case where the users want to end the game.
 */
public class ExitGameUseCase extends TreeHandler implements NodeLogic{

    /**
     * This method creates a State
     * @param input An integer representing the user's input. However, this parameter is not used for this method.
     * @return A State object containing options that confirm whether the users want to exit the game. The State object
     * also updates the State object to accommodate the game being exited.
     */
    public State create_state(int input){
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        if (mainStates[0] == 1) {
            mainStates[0]= 0;
            currentState.setExitToMenu(true);
        }
        else{
            //confirmation node setup
            currentState.addOptions("Yes");
            currentState.addOptions("No");
            confirmationReturn = currentTree;
        }
        return currentState;
    }

}

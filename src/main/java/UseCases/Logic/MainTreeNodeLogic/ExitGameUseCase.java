package UseCases.Logic.MainTreeNodeLogic;

import Entities.*;
import UseCases.Logic.GameLogic;
import UseCases.Logic.NodeLogic;

/**
 * This class represents the use case where the users want to end the game.
 */

public class ExitGameUseCase extends MainTreeNodeLogic implements NodeLogic {

    public ExitGameUseCase() {
        super("Exit Game");
    }

    /**
     * This method creates a State
     * @param input An integer representing the user's input. However, this parameter is not used for this method.
     * @return A State object containing options that confirm whether the users want to exit the game. The State object
     * also updates the State object to accommodate the game being exited.
     */
    public State create_state(int input){
        GameLogic gameLogicInteractor = getGameLogicInteractor();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(getName());
        if (mainStates[0] == 1) {
            mainStates[0]= 0;
            currentState.setExitToMenu(true);
        }
        else{
            //confirmation node setup
            currentState.addOptions("Yes");
            currentState.addOptions("No");
            setConfirmationReturn(currentTree);
        }
        return currentState;
    }

}

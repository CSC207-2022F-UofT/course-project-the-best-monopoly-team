package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

public class ExitGameUseCase extends TreeHandler implements NodeLogic{

    public State create_state(int input){
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
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

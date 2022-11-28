package TreeHandlers;

import Entities.State;

public class ConfirmationUseCase extends TreeHandler implements NodeLogic{

    @Override
    public State create_state(int input) {
        //Gives the user another chance to reconsider their actions
        if (input == 0) {
            mainStates[0] = 1;
            gameLogicInteractor.setCurrentTree(confirmationReturn);
            return gameLogicInteractor.handleTree(input);
        }
        else{
            return afterBottomNode();
        }
    }

}

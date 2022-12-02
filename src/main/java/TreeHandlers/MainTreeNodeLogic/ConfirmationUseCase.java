package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class ConfirmationUseCase extends MainTreeNodeLogic implements NodeLogic {

    @Override
    public State create_state(int input) {

        GameLogicTree confirmationReturn = getConfirmationReturn();
        GameLogic gameLogicInteractor = getGameLogicInteractor();

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

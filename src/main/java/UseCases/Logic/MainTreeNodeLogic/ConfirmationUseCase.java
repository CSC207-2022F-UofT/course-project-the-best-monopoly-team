package UseCases.Logic.MainTreeNodeLogic;

import Entities.*;
import UseCases.Logic.GameLogic;
import UseCases.Logic.NodeLogic;

/**
 * This class represents a use case where the current player has to confirm something.
 */
public class ConfirmationUseCase extends MainTreeNodeLogic implements NodeLogic {

    public ConfirmationUseCase() {
        super("Confirm Action");
    }

    /**
     * This method returns a State object either containing a State object representing the end of a tree path or one
     * that aids in allowing the user to confirm their actions.
     * @param input An integer representing if a user would like to confirm their actions.
     * @return A State object either containing a State object representing the end of a tree path or one that aids in
     * allowing the user to confirm their actions.
     */
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

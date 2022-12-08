package NodeLogic.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import UseCases.NodeLogic;

/**
 * This class represents the use case when a player ends its turn.
 */

public class EndTurnUseCase extends MainTreeNodeLogic implements NodeLogic {

    public EndTurnUseCase() {
        super("End Turn");
    }

    /**
     * This method creates a State object that either helps to tell the user that it cannot end its turn or changes
     * the current player and makes the state go back to normal.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object containing either an "Ok" option or a State object corresponding to one at the end of a
     * tree path.
     */
    public State create_state(int input){
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        currentState.setId(getName());
        //end the turn if the person is not in debt
        if (currentPlayer.getMoney()  >= 0){
            mainStates[3] += 1;
            if (mainStates[3] >= mainStates[4] && mainStates[4] != -1){
                gameLogicInteractor.transverseCurrentTree(1);
                currentState = gameLogicInteractor.handleTree(0);
                return currentState;
            }

            //changing the player and turning the state back to normal
            changePlayers();
            mainStates[0] = 0;
            mainStates[1] = 0;
            currentState = afterBottomNode();
        }
        else{
            //option when the user cannot end their turn
            currentState.addOptions("Ok");
        }
        return currentState;
    }

}

package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

/**
 * This use case represents when the current user lands on an empty property space.
 */
public class EmptyPropertySpace extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object containing information on the response and options from rolling on an empty
     * property space.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return a State object containing information on the response and options from rolling on an empty property
     * space.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        Property targetProperty;

        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //gets the response and options from rolling on an empty property
        currentState.setRoll(diceRoll);
        targetProperty = (Property) board.getCell(currentPlayer.getPosition());
        currentState.setCurrentPlayerProperty(targetProperty);
        addSwitchOptions(currentState);

        return currentState;
    }
}
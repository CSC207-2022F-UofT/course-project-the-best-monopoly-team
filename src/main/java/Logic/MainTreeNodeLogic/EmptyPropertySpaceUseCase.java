package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.NodeLogic;

/**
 * This use case represents when the current user lands on an empty property space.
 */
public class EmptyPropertySpaceUseCase extends MainTreeNodeLogic implements NodeLogic {

    public EmptyPropertySpaceUseCase() {
        super("Property Unowned");
    }

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
        Player currentPlayer = getCurrentPlayer();
        Property targetProperty;

        State currentState = new State();
        currentState.setId(getName());
        //gets the response and options from rolling on an empty property
        currentState.setRoll(diceRoll);
        targetProperty = (Property) board.getCell(currentPlayer.getPosition());
        currentState.setCurrentPlayerProperty(targetProperty);
        addSwitchOptions(currentState);

        return currentState;
    }
}
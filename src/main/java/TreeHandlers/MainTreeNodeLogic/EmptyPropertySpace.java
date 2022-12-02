package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class EmptyPropertySpace extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        Property targetProperty = getTargetProperty();

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
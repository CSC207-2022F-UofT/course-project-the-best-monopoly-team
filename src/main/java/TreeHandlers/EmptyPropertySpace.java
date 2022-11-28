package TreeHandlers;

import Entities.Property;
import Entities.State;

public class EmptyPropertySpace extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
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
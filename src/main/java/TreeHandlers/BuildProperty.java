package TreeHandlers;

import Entities.Property;
import Entities.State;

public class BuildProperty extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));

        //builds a house on the chosen property
        currentPlayer.buildHouse(targetProperty,1);
        currentState.setCurrentPlayerProperty(targetProperty);
        currentState.addOptions("Ok");
        return currentState;
    }
}
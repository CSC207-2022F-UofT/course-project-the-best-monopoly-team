package TreeHandlers.MainTreeNodeLogic;

import Entities.Player;
import Entities.Property;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.HashMap;

public class BuildProperty extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));

        //builds a house on the chosen property
        currentPlayer.buildHouse(targetProperty,1);
        currentState.setCurrentPlayerProperty(targetProperty);
        currentState.addOptions("Ok");
        return currentState;
    }
}
package TreeHandlers.MainTreeNodeLogic;

import Entities.Player;
import Entities.Property;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.HashMap;

/**
 * This class represents the use case when a building is to be built on a property.
 */
public class BuildProperty extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method builds a house on a chosen property and returns a State object containing the necessary information
     * to continue the game after the house is built.
     * @param input An integer containing the user's input. However, this input will not be used for this method.
     * @return Returns a State object containing the necessary information required to continue the game after the house
     * is built.
     */
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
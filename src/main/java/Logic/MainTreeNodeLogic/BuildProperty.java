package Logic.MainTreeNodeLogic;

import Entities.Player;
import Entities.Property;
import Entities.State;
import Logic.NodeLogic;
import Logic.PlayerLogic;

import java.util.HashMap;

/**
 * This class represents the use case when a building is to be built on a property.
 */
public class BuildProperty extends MainTreeNodeLogic implements NodeLogic {

    public BuildProperty() {
        super("Build House/Hotel");
    }

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
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        currentState.setId(getName());
        Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("PropertySelected"));

        //builds a house on the chosen property
        PlayerLogic playerLogic = new PlayerLogic(currentPlayer);
        playerLogic.buildHouse(targetProperty,1);
        currentState.setCurrentPlayerProperty(targetProperty);
        currentState.addOptions("Ok");
        return currentState;
    }
}
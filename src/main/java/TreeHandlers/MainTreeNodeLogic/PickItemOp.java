package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the use case where the current player has to choose an item of a targeted opponent's
 * inventory.
 */
public class PickItemOp extends MainTreeNodeLogic implements NodeLogic {
    /** This method creates a State object and adds the selected inventory item belonging to a targeted opponent to the
     * State object and adds the properties of the current player as options to be chosen from later.
     * @param input An integer representing the index of the targeted opponent's property.
     * @return A State object containing the selected item and options for the current player to choose from later.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //the input corresponds to the index of the targeted opponent's property inventory
        selectedOptions.put(currentTree.getName(), input);

        //provide item options from the current player's inventory
        ArrayList<Property> currentPlayerInventory = currentPlayer.getProperties();
        //using "i" starting from 0 to number of properties the player has - 1
        for(int i = 0; i < currentPlayer.getProperties().size(); i++){
            currentState.addOptions(currentPlayerInventory.get(i).getName());
        }
        return currentState;
    }
}
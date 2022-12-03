package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.HashMap;

/**
 * This class represents the use case where the current player has to choose an item that it owns.
 */
public class PickItemSelf extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object and adds the selected option the current player chooses to it while also
     * up the State object for the following steps after choosing an item from its own inventory.
     * @param input An integer representing the index of the current player's targeted property.
     * @return A State object containing the selected item and options for the following step after choosing the item.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setBackEnable(true);
        currentState.addOptions("Yes");
        currentState.addOptions("No");

        //the input corresponds to the index of the current player's targeted property;
        selectedOptions.put(currentTree.getName(), input);
        return currentState;
    }
}
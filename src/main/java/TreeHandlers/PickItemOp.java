package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Property;
import Entities.State;

import java.util.ArrayList;

public class PickItemOp extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setBackEnable(true);
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
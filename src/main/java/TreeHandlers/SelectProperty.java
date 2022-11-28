package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

public class SelectProperty extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.setBackEnable(true);

        //Case property selected (adds the property index)
        selectedOptions.put(currentTree.getName(), input);

        //the player chooses what to do to the property
        currentState.addOptions("Mortgage");
        currentState.addOptions("Unmortgage");
        currentState.addOptions("Build a house");

        return currentState;
    }
}
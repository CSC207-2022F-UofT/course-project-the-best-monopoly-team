package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

public class PickItemSelf extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
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
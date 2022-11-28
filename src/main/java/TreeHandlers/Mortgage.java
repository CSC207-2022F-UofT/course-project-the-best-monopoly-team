package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Property;
import Entities.State;

public class Mortgage extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();

        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        if (mainStates[0] == 1) {
            //the player chooses to mortgage the property
            Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));
            currentPlayer.mortgage(targetProperty);
            mainStates[0] = 0;
            currentState = afterBottomNode();
        }
        else{
            //setup for confirmation node
            currentState.addOptions("yes");
            currentState.addOptions("no");
            confirmationReturn = currentTree;
        }
        return currentState;
    }
}
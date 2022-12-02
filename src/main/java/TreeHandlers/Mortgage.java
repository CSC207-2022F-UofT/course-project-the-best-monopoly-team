package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Property;
import Entities.State;

/**
 * This class represents the use case where the current player chooses to mortgage a chosen property.
 */
public class Mortgage extends TreeHandler implements NodeLogic {

    /**
     * This method creates a State object and updates it based on whether the current player chose to mortgage the
     * property or not. It will mortgage the property if the current player chooses to.
     * @param input An integer representing the user's input. However, this parameter will not be used for this use case.
     * @return A State object that either has options for confirmation in the case where the mortgage was not conducted,
     * or the standards State object when the tree is now at its bottom node.
     */
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
            currentState.addOptions("Yes");
            currentState.addOptions("No");
            confirmationReturn = currentTree;
        }
        return currentState;
    }
}
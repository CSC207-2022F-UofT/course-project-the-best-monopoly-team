package NodeLogic.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import UseCases.NodeLogic;
import UseCases.PlayerLogic;

import java.util.HashMap;

/**
 * This class represents the use case where the current player chooses to mortgage a chosen property.
 */
public class Mortgage extends MainTreeNodeLogic implements NodeLogic {

    public Mortgage() {
        super("Mortgage Property");
    }

    /**
     * This method creates a State object and updates it based on whether the current player chose to mortgage the
     * property or not. It will mortgage the property if the current player chooses to.
     * @param input An integer representing the user's input. However, this parameter will not be used for this use case.
     * @return A State object that either has options for confirmation in the case where the mortgage was not conducted,
     * or the standards State object when the tree is now at its bottom node.
     */
    @Override
    public State create_state(int input) {

        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        PlayerLogic currentPlayerLogic = new PlayerLogic(currentPlayer);
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();

        State currentState = new State();
        currentState.setId(getName());
        if (mainStates[0] == 1) {
            //the player chooses to mortgage the property
            Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("PropertySelected"));
            currentPlayerLogic.mortgage(targetProperty);
            mainStates[0] = 0;
            currentState = afterBottomNode();
        }
        else{
            //setup for confirmation node
            currentState.addOptions("Yes");
            currentState.addOptions("No");
            setConfirmationReturn(currentTree);
        }
        return currentState;
    }
}
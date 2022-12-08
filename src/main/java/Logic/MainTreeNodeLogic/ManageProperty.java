package Logic.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Logic.NodeLogic;

import java.util.ArrayList;

/**
 * This method represents the use case where the users want to manage a property.
 */
public class ManageProperty extends MainTreeNodeLogic implements NodeLogic {
    public ManageProperty() {
        super("Manage Property");
    }

    /**
     * This method creates a State object either containing properties as options or moves to another node when there
     * are no properties to manage.
     * @param input An integer representing the user's input. This parameter is not used for this method.
     * @return A State object containing either properties to manage or moves the game into another node.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(getName());
        ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();

        //if player has no properties, go to another node.
        if (currentPlayerProperties.isEmpty()){
            gameLogicInteractor.transverseCurrentTree(1);
            currentState = gameLogicInteractor.handleTree(0);
        }

        //provide options on the properties available
        for (Property currentPlayerProperty : currentPlayerProperties) {
            currentState.addOptions(currentPlayerProperty.getName());
        }

        return currentState;
    }
}
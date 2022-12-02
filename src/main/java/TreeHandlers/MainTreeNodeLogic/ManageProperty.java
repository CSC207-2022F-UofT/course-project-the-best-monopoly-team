package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

public class ManageProperty extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
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
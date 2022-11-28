package TreeHandlers;

import Entities.Property;
import Entities.State;

import java.util.ArrayList;

public class ManageProperty extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setBackEnable(true);
        ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();

        //if player has no properties, go to another node.
        if (currentPlayerProperties.isEmpty()){
            gameLogicInteractor.transverseCurrentTree(1);
            currentState = gameLogicInteractor.handleTree(0);
        }

        //provide options on the properties available
        for(int i = 0; i < currentPlayerProperties.size(); i++){
            currentState.addOptions(currentPlayerProperties.get(i).getName());
        }

        return currentState;
    }
}
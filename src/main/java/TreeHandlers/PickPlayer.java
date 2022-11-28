package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Player;
import Entities.Property;
import Entities.State;

import java.util.ArrayList;

public class PickPlayer extends TreeHandler implements NodeLogic{
    @Override
    public State create_state(int input) {
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(currentTree.getName());
        currentState.setBackEnable(true);
        //adds the chosen player index in selected options (who the current player wants to trade with)

                /*
                We asked them for an input in reference to a list with their player removed,
                thus we have to add 1 to their input in certain cases
                 */
        if (input >= getCurrentPlayerIndex()) {
            input += 1;
        }
        //getting the player the user wants to trade with
        selectedOptions.put(currentTree.getName(), input);

        //provide item options from the inventory of the selected player
        Player selectedPlayer = board.getPlayers().get(input);
        ArrayList<Property> playerProperties = selectedPlayer.getProperties();
        if (playerProperties.isEmpty() || currentPlayer.getProperties().isEmpty()){
            gameLogicInteractor.transverseCurrentTree(1);
            currentState = gameLogicInteractor.handleTree(0);
            return currentState;
        }
        //using "i" starting from 0 to number of properties the player has - 1
        for (int i = 0; i < playerProperties.size(); i++){
            currentState.addOptions(playerProperties.get(i).getName());
        }

        return currentState;
    }
}

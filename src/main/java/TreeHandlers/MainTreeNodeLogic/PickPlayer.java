package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a use case where the current player has to choose a player to interact with.
 */
public class PickPlayer extends MainTreeNodeLogic implements NodeLogic{

    /**
     * This method creates a State object and adds the selected player to the State object. It will also add options to
     * the State object. These options are the items from the inventory of the selected player.
     * @param input An integer representing the Player instance that the current player has chosen.
     * @return A State object containing the selected player and a list of the chosen player's properties.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

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

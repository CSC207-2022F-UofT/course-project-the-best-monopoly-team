package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Player;
import Entities.Property;
import Entities.State;

import java.util.ArrayList;

public class BankruptcyUseCase extends TreeHandler implements NodeLogic{

    @Override
    public State create_state(int input) {
        State currentState = new State();
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        if (mainStates[0] == 1) {
            mainStates[0] = 0;

            //removing all player connection with the board
            ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();
            for (Property targetedProperty : currentPlayerProperties) {
                targetedProperty.setOwner(null);
                targetedProperty.setHouses(0);
                targetedProperty.setMortgageStatus(false);
            }
            Player tempPlayer = currentPlayer;
            //switching the player before removing the original player
            changePlayers();
            board.removePlayer(tempPlayer);

            //changing the player and turning the state back to normal
            mainStates = new int[2];
            currentState = afterBottomNode();
        } else {
            //confirmation node setup
            confirmationReturn = currentTree;
            currentState.addOptions("Yes");
            currentState.addOptions("No");
        }
        return currentState;
    }

}

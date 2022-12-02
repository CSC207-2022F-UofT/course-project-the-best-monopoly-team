package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

public class BankruptcyUseCase extends MainTreeNodeLogic implements NodeLogic {

    @Override
    public State create_state(int input) {
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        Board board = getBoard();

        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
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
            setConfirmationReturn(currentTree);
            currentState.addOptions("Yes");
            currentState.addOptions("No");
        }
        return currentState;
    }

}

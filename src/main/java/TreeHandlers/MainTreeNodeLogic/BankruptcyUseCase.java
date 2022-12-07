package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

/**
 * This class represents a use case when a player in the game is bankrupt.
 */
public class BankruptcyUseCase extends MainTreeNodeLogic implements NodeLogic {

    public BankruptcyUseCase() {
        super("Bankruptcy");
    }

    /**
     * This method creates a State and returns it. The State object either contains options for confirmation or be one
     * representing the end of a tree path. The method also disconnects the bankrupt player from the game.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return A State object either contains options for confirmation or be one representing the end of a tree path.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        Board board = getBoard();

        currentState.setId(getName());
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
            //switching the player before removing the original player
            changePlayers();
            board.removePlayer(currentPlayer);

            if (board.getPlayers().size() == 1){
                gameLogicInteractor.transverseCurrentTree(1);
                currentState = gameLogicInteractor.handleTree(0);
                return currentState;
            }

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

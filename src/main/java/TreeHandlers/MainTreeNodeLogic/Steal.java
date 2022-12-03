package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

/**
 * This class represents a use case where a Player instance chooses to steal money from another Player instance.
 */
public class Steal extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a States object and adds all the possible list of players that the current player can steal
     * from as options. Then it returns the State object.
     * @param input An integer that represents the input of the user. However, this parameter is not used in this method.
     * @return A State object containing options which are a list of all possible that the current player can steal from.
     * This excludes the current player.
     */
    @Override
    public State create_state(int input) {

        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //provide options of which players we can steal from
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        for (Player player : playerCopy) {
            currentState.addOptions(player.getName());
        }
        return currentState;
    }
}
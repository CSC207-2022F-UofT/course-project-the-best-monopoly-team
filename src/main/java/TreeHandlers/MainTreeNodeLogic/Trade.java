package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

/**
 * This class represents a use case where two Player instances trade with each other. The class handles the actions
 * required to successfully set up a trade.
 */
public class Trade extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object and adds all the possible list of player that the current player can trade
     * with into the State object that was created and returns it.
     * @param input An integer that represents the input of the user. However, this parameter is not used in this method.
     * @return A State object containing options which are a list of all possible players that the current player can
     * trade with. This excludes the current player.
     */
    @Override
    public State create_state(int input) {

        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //provide a list of all possible players considering the current player is not an option
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        for(int i = 0; i < playerCopy.size(); i++){
            currentState.addOptions(playerCopy.get(i).getName());
        }
        return currentState;
    }
}

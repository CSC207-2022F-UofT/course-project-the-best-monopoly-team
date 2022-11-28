package TreeHandlers;

import Entities.Player;
import Entities.State;
import TreeHandlers.NodeLogic;
import TreeHandlers.TreeHandler;

import java.util.ArrayList;

public class Trade extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setBackEnable(true);

        //provide a list of all possible players considering the current player is not an option
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        for(int i = 0; i < playerCopy.size(); i++){
            currentState.addOptions(playerCopy.get(i).getName());
        }
        return currentState;
    }
}

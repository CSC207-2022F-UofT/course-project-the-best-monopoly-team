package TreeHandlers;

import Entities.Player;
import Entities.State;

import java.util.ArrayList;

public class Steal extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //provide options of which players we can steal from
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        for(int i = 0; i < playerCopy.size(); i++){
            currentState.addOptions(playerCopy.get(i).getName());
        }
        return currentState;
    }
}
package TreeHandlers;

import Entities.Player;
import Entities.State;

import java.util.ArrayList;

public class ChoosePlayerUseCase extends TreeHandler implements NodeLogic{

    public State create_state(int input){
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        State currentState = new State();
        //Steal from the target player
        playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        String stealStatus = currentPlayer.steal(playerCopy.get(input));

        currentState.setDescription(stealStatus);
        currentState.addOptions("Ok");
        return currentState;
    }

}

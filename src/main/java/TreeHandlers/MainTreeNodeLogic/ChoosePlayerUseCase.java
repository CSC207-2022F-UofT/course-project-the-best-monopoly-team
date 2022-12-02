package TreeHandlers.MainTreeNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.ArrayList;

public class ChoosePlayerUseCase extends MainTreeNodeLogic implements NodeLogic {

    public State create_state(int input){
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //Steal from the target player
        playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        String stealStatus = currentPlayer.steal(playerCopy.get(input));

        currentState.setDescription(stealStatus);
        currentState.addOptions("Ok");
        return currentState;
    }

}

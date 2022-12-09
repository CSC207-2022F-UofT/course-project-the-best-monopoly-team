package Logic.MainTreeNodeLogic;

import Entities.State;
import Logic.NodeLogic;
import Entities.Player;

import java.util.List;


public class FinishGameUseCase extends MainTreeNodeLogic implements NodeLogic {
    public FinishGameUseCase(){
        super("Game Complete");
    }

    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId("Game Complete");
        currentState.addOptions("Ok");

        // The case that there is only one player in the player array

        // handles the case where the max turns are met
        if (mainStates[3] >= mainStates[4]) {
            List<Player> players = getPlayers();
            String player_with_most_money = "";
            int max_money = 0;
            for (Player player : players) {
                if (player.getMoney() > max_money) {
                    player_with_most_money = player.getName();
                    max_money = player.getMoney();
                }
            }
            currentState.setDescription("Max turn reaches, " + player_with_most_money + " has the most money and wins the game.");

        } else if (getPlayers().size() == 1) {
            List<Player> players = getPlayers();
            Player winningPlayer = players.get(0);
            String winnningString = winningPlayer.getName() + " won the game!!!";
            currentState.setDescription(winnningString);
        }

        mainStates[0] = 1;
        return currentState;

    }
}

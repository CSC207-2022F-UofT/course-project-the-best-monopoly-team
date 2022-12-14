package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case where the player has chosen the number of players.
 */
public class NumberOfPlayersUseCase extends InitialLogic implements NodeLogic {
    public NumberOfPlayersUseCase() {
        super("Number of Players");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the number of players the user wants
        state.setBackEnable(true);
        selectedOptions.put("NumOfPlayers", input + 2);
        //options associated with the next node
        state.addOptions("30 rounds");
        state.addOptions("60 rounds");
        state.addOptions("90 rounds");
        state.addOptions("no limit");
        return afterLogic(state);
    }
}

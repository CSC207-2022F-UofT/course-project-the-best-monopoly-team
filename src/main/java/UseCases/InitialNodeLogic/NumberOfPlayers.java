package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class NumberOfPlayers extends InitialLogic implements NodeLogic {
    public NumberOfPlayers() {
        super("Number of Players");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the number of players the user wants
        state.setBackEnable(true);
        selectedOptions.put("NumOfPlayers",input);
        //options associated with the next node
        state.addOptions("30 rounds");
        state.addOptions("60 rounds");
        state.addOptions("90 rounds");
        state.addOptions("no limit");
        return afterLogic(state);
    }
}

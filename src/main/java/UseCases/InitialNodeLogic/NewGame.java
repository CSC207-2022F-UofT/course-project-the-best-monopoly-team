package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class NewGame extends InitialLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting if the user wants to play a new game
        state.setBackEnable(true);
        selectedOptions.put("NewOrLoad",1);

        //options associated with the next node
        state.addOptions("Normal mode");
        return afterLogic(state);
    }
}

package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class ChooseGameMode extends InitialLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the mode the user wants
        state.setBackEnable(true);
        selectedOptions.put(currentTree.getName(),input);

        //options associated with the next node
        for (int i = 2; i < 9; i++){
            state.addOptions(i + " players");
        }
        return afterLogic(state);
    }
}

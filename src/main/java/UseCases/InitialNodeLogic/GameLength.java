package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class GameLength extends InitialLogic implements NodeLogic {
    public GameLength() {
        super("Game Length");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the game length the user requested
        state.setBackEnable(true);
        selectedOptions.put("GameLength",input);

        //options associated with the next node
        state.addOptions("Yes");
        state.addOptions("No");
        return afterLogic(state);
    }
}

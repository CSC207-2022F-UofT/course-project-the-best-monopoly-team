package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;

public class ChooseSave extends InitialLogic implements NodeLogic {
    public ChooseSave() {
        super("Choose Save");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the user's saved game choice
        selectedOptions.put("SaveChoice",input);

        //options associated with the next node
        state.addOptions("Yes");
        state.addOptions("No");
        return afterLogic(state);
    }
}

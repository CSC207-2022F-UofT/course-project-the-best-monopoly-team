package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class LoadGame extends InitialLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting if the user wants to load an old one
        selectedOptions.put("NewOrLoad", 0);

        //options associated with the next node
        //TODO provide lists of saves
        return afterLogic(state);
    }
}

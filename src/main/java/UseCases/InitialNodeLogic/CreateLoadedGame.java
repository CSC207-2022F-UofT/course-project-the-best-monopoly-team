package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class CreateLoadedGame extends InitialLogic implements NodeLogic {
    public CreateLoadedGame() {
        super("Create Loaded Game");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //in "Create Loaded Game" node
        //TODO: CREATE THE GAME BY MAKING THE LOGIC INTERACTOR
        return afterLogic(state);
    }
}

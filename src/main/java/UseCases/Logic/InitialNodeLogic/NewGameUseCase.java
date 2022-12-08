package UseCases.Logic.InitialNodeLogic;

import Entities.State;
import UseCases.Logic.NodeLogic;

public class NewGameUseCase extends InitialLogic implements NodeLogic {
    public NewGameUseCase() {
        super("New Game");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting if the user wants to play a new game
        state.setBackEnable(true);

        //options associated with the next node
        state.addOptions("Normal mode");
        state.addOptions("Rich mode");
        return afterLogic(state);
    }
}

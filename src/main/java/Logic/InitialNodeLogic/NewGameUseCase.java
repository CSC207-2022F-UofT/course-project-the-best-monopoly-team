package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;
/**
 * This class represents the use case where the player has chosen to play a new game.
 */
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

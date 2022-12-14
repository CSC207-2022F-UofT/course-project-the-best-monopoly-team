package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case where the player has chosen their desired game mode.
 */
public class ChooseGameModeUseCase extends InitialLogic implements NodeLogic {
    public ChooseGameModeUseCase() {
        super("Choose Game Mode");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the mode the user wants
        state.setBackEnable(true);
        selectedOptions.put("GameMode",input);

        //options associated with the next node
        for (int i = 2; i < 9; i++){
            state.addOptions(i + " players");
        }
        return afterLogic(state);
    }
}

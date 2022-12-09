package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;
/**
 * This class represents the use case where the player has chosen a save file to load.
 */
public class ChooseSaveUseCase extends InitialLogic implements NodeLogic {
    public ChooseSaveUseCase() {
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

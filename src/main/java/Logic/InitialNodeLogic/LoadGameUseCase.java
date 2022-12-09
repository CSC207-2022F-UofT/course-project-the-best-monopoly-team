package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;
import Persistence.LoadAccess;
/**
 * This class represents the use case where the player has chosen to load a game.
 */
public class LoadGameUseCase extends InitialLogic implements NodeLogic {
    public LoadGameUseCase() {
        super("Load Game");
    }

    @Override
    public State create_state(int input) {
        State state = beforeLogic();

        //options associated with the next node
        LoadAccess load = caseInteractor.getLoadAccess();

        String[] allSaves = load.checkSaves(load.getFile().getAbsolutePath());
        // add save file name to state
        for (String save : allSaves) {
            state.addOptions(save);
        }

        return afterLogic(state);
    }
}

package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;
import Persistence.LoadAccess;
import Persistence.LoadFile;

import java.io.File;

public class LoadGame extends InitialLogic implements NodeLogic {
    public LoadGame() {
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

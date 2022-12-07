package UseCases.InitialNodeLogic;

import Entities.Board;
import Entities.State;
import Interface.NodeLogic;
import Persistence.LoadAccess;
import Persistence.LoadFile;

import java.io.File;

public class CreateLoadedGame extends InitialLogic implements NodeLogic {
    public CreateLoadedGame() {
        super("Create Loaded Game");
    }

    @Override
    public State create_state(int input) {
        State state;
        //in "Create Loaded Game" node
        //TODO: CREATE THE GAME BY MAKING THE LOGIC INTERACTOR
        if (input == 0){
            try {
                File file = new File("src/gameData");
                LoadAccess load = new LoadFile(file);
                String[] allSaves = load.checkSaves("src/gameData");

                int integerFile = selectedOptions.get("SaveChoice");
                Board board = caseInteractor.loadSavedBoard(allSaves[integerFile]);
                int[] initialStates = caseInteractor.loadInitialStates();

                caseInteractor.createGame(board, initialStates);
                state = caseInteractor.getLogicInteractor().getCurrentState();
            } catch (Exception IOException){
                return null;
            }
        }
        else{
            state = caseInteractor.getInitialState();
        }

        return afterLogic(state);
    }
}

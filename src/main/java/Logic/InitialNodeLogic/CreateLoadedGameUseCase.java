package Logic.InitialNodeLogic;

import Entities.Board;
import Entities.State;
import Logic.NodeLogic;
import Persistence.LoadAccess;
/**
 * This class represents the use case where the player has confirmed their choice of loading a saved
 * game.
 */
public class CreateLoadedGameUseCase extends InitialLogic implements NodeLogic {
    public CreateLoadedGameUseCase() {
        super("Create Loaded Game");
    }

    @Override
    public State create_state(int input) {
        State state;
        //in "Create Loaded Game" node
        if (input == 0){
            try {
                LoadAccess load = caseInteractor.getLoadAccess();
                String[] allSaves = load.checkSaves(load.getFile().getAbsolutePath());

                int integerFile = selectedOptions.get("SaveChoice");
                Board board = caseInteractor.loadSavedBoard(allSaves[integerFile]);
                int[] initialStates = caseInteractor.loadInitialStates(allSaves[integerFile]);

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

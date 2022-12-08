package Logic.InitialNodeLogic;

import Entities.State;
import Logic.NodeLogic;

public class CreateNewGameUseCase extends InitialLogic implements NodeLogic {
    public CreateNewGameUseCase() {
        super("Create New Game");
    }

    @Override
    public State create_state(int input) {
        State state;
        //creating the game if confirmed
        if (input == 0) {
            int[] states = new int[6];
            states[0] = selectedOptions.get("NumOfPlayers");
            states[1] = selectedOptions.get("GameLength");
            states[2] = selectedOptions.get("GameMode");
            states[3] = 0;
            states[4] = 0;
            states[5] = 0;
            caseInteractor.createNewGame(states);
            state = caseInteractor.getLogicInteractor().getCurrentState();
        }
        else{
            state = caseInteractor.getInitialState();
        }
        return afterLogic(state);
    }
}

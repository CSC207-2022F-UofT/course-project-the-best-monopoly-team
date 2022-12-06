package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class CreateNewGame extends InitialLogic implements NodeLogic {
    public CreateNewGame() {
        super("Create New Game");
    }

    @Override
    public State create_state(int input) {
        State state;
        //creating the game if confirmed
        if (input == 0) {
            caseInteractor.createGame();
            state = caseInteractor.getLogicInteractor().getCurrentState();
        }
        else{
            state = caseInteractor.getInitialState();
        }
        return afterLogic(state);
    }
}

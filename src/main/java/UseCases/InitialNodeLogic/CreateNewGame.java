package UseCases.InitialNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class CreateNewGame extends InitialLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //creating the game if confirmed
        if (input == 0) {
            caseInteractor.createGame(true);
            state = caseInteractor.getLogicInteractor().getCurrentState();
        }
        else{
            state = caseInteractor.getInitialState();
        }
        return afterLogic(state);
    }
}

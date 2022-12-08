package TreeHandlers.MainTreeNodeLogic;

import Entities.State;
import Interface.NodeLogic;

public class FinishGameUseCase extends MainTreeNodeLogic implements NodeLogic {
    public FinishGameUseCase(){
        super("Game Complete");
    }

    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId("Game Complete");
        currentState.addOptions("Ok");
        mainStates[0] = 1;
        return currentState;

    }
}

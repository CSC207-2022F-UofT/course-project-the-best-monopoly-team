package TreeHandlers;

import Entities.State;
import TreeHandlers.NodeLogic;
import TreeHandlers.TreeHandler;

public class InformationUseCase extends TreeHandler implements NodeLogic {

    @Override
    public State create_state(int input) {
        State currentState = afterBottomNode();
        return currentState;
    }

}

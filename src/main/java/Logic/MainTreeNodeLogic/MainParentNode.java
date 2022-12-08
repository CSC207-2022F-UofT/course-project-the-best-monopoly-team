package Logic.MainTreeNodeLogic;

import Entities.Player;
import Entities.State;
import Logic.NodeLogic;

public class MainParentNode extends MainTreeNodeLogic implements NodeLogic {
    public MainParentNode() {
        super("Main Tree Parent Node");
    }

    @Override
    public State create_state(int input) {
        Player currentPlayer = getCurrentPlayer();
        State currentState = new State();
        currentState.setId(getName());
        currentState.setPlayer(currentPlayer);
        addSwitchOptions(currentState);
        return currentState;
    }
}

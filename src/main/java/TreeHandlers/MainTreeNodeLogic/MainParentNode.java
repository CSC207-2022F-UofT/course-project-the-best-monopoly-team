package TreeHandlers.MainTreeNodeLogic;

import Entities.Player;
import Entities.State;
import Interface.NodeLogic;

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
        currentState.setDescription(currentPlayer.getName() + " It's your turn! What do you want to do? You currently " +
                "have " + currentPlayer.getMoney() + " dollars");
        addSwitchOptions(currentState);
        return currentState;
    }
}

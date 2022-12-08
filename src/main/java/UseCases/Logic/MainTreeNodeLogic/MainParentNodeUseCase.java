package UseCases.Logic.MainTreeNodeLogic;

import Entities.Player;
import Entities.State;
import UseCases.Logic.NodeLogic;

/**
 * This class represents the use case where the game is in a non-auction or non-trading state.
 */
public class MainParentNodeUseCase extends MainTreeNodeLogic implements NodeLogic {
    public MainParentNodeUseCase() {
        super("Main Tree Parent Node");
    }

    /**
     * This method creates a State object containing relevant information of the current state of the game including
     * the current player and the options of what the player can do.
     * @param input an integer representing the user's input. This parameter will not be used for this method.
     * @return A State object containing options available of what the current player can do.
     */
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

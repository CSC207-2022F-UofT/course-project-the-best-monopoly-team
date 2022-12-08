package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.NodeLogic;


/**
 * This class represents a use case where a Player instance chooses to steal money from another Player instance.
 */
public class StealUseCase extends MainTreeNodeLogic implements NodeLogic {

    public StealUseCase() {
        super("Steal");
    }

    /**
     * This method creates a States object and adds all the possible list of players that the current player can steal
     * from as options. Then it returns the State object.
     * @param input An integer that represents the input of the user. However, this parameter is not used in this method.
     * @return A State object containing options which are a list of all possible that the current player can steal from.
     * This excludes the current player.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setBackEnable(true);
        currentState.setId(getName());
        //provide options of which players we can steal from
        addPlayersState(currentState);
        return currentState;
    }
}
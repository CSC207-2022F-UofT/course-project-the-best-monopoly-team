package Logic.MainTreeNodeLogic;

import Entities.State;
import Logic.NodeLogic;
/**
 * This class represents a use case after an action card is drawn.
 */
public class CallActionUseCase extends MainTreeNodeLogic implements NodeLogic {
    public CallActionUseCase() {
        super("Perform Action");
    }

    /**
     * This method creates and returns a State object containing relevant information on the action called.
     * @param input an integer representing the user's input. This parameter will not be used in this method.
     * @return a State object containing relevant information on the action called and options for the user to choose
     * from.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        String answer = getAnswer();

        currentState.setId(getName());
        //gets the response from rolling on a space
        currentState.setRoll(diceRoll);
        currentState.setDescription(answer);
        currentState.addOptions("Ok");
        return currentState;
    }
}
package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.NodeLogic;


/**
 * This class represents the use case where the users of the game wants to save the game.
 */
public class SaveGameUseCase extends MainTreeNodeLogic implements NodeLogic {

    public SaveGameUseCase() {
        super("Save The Game");
    }

    /**
     * This method returns a State object containing the option to confirm the saving of the game and sets up the State
     * object with to prepare for the saving of the game.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object containing the added confirmation option to save the game and sets up some instance
     * attributes  to prepare for the save.
     */
    public State create_state(int input){

        State currentState = new State();
        //options for saving the game
        currentState.setId(getName());
        currentState.addOptions("Ok");
        currentState.setSaveGame(true);
        return currentState;
    }

}

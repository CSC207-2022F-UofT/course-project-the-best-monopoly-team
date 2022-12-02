package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class SaveGameUseCase extends MainTreeNodeLogic implements NodeLogic {

    public State create_state(int input){

        GameLogic gameLogicInteractor = getGameLogicInteractor();

        State currentState = new State();
        //options for saving the game
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.addOptions("Ok");
        //TODO: make this option save the game in use case interactor
        currentState.setSaveGame(true);
        return currentState;
    }

}

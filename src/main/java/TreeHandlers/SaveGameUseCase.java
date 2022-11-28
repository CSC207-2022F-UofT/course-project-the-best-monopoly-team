package TreeHandlers;

import Entities.State;

public class SaveGameUseCase extends TreeHandler implements NodeLogic{

    public State create_state(int input){
        State currentState = new State();
        //options for saving the game
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        currentState.addOptions("Ok");
        //TODO: make this option save the game in use case interactor
        currentState.setSaveGame(true);
        return currentState;
    }

}

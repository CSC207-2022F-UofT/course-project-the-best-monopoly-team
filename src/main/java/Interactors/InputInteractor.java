package Interactors;

import Entities.Input;
import Entities.State;
import UseCases.UseCaseInteractor;

/**
 * InputInteractor is a class that controls the data in the Input Entity and interacts with the UseCaseInteractor to
 * process the input and obtain an updated state
 */
public class InputInteractor {
    /**
     * InstanceVar input: the Input object which represents the users current input
     * InstanceVar interactor: the UseCaseInteractor object that takes in the input for processing
     * InstanceVar currentState: the State object used in processing to obtain the new state
     */
    Input input;
    private final UseCaseInteractor interactor;
    private State currentState;

    /**
     * The constructor for the InputInteractor Class
     * @param interactor: the UseCaseInteractor object used for data processing and logic
     */
    public InputInteractor(UseCaseInteractor interactor){
        this.input = new Input();
        this.interactor = interactor;
        this.currentState = interactor.getInitialState();
    }

    /**
     * Function returns the state that the game is in
     * @return the state that the game is in
     */
    public State getCurrentState() {
        return this.currentState;
    }

    /**
     * Function that updates the state based on the user's input
     */
    public void sendInput() {
        if (this.input.getInput() == this.currentState.getOptions().size())
            this.currentState = this.interactor.handleInput(-1);
        else
            this.currentState = this.interactor.handleInput(this.input.getInput());
    }

    /**
     * A function that gets the users choice and sets that as the current input
     * @param choice the users choice translated to an integer
     */
    public void getChoice(int choice){
        this.input.setInput(choice);
    }

    /**
     * A function that processes the logic to get the new state and returns the updated state
     * @return the updated state
     */
    public State getUpdatedState(){
        sendInput();
        return this.currentState;
    }
}
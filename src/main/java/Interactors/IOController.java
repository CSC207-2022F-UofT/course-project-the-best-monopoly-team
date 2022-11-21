package Interactors;

import Entities.State;
import UseCases.UseCaseInteractor;

import java.io.File;
import java.util.ArrayList;

/**
 * IOController is the class that handles sending the input data to be process to the back-end of the program
 * and taking the output data and presenting it to the user through the PresenterDisplay
 */
public class IOController {
    /**
     * InstanceVar output: the current output that should be presented to the user
     * InstanceVar input: the current input that was provided by the user that is used for data processing
     * InstanceVar interactor: the UseCaseInteractor instance that handles input processing
     * InstanceVar state: the State instance that changes state data depending on input data
     */
    private String output;
    private int input;
    private UseCaseInteractor interactor;
    private State currentState;


    /**
     * The constructor for the IOController class
     */
    public IOController(File file){
        this.output = "Welcome to Monopoly! Do you want to ";
        this.input = 0;
        this.interactor = new UseCaseInteractor(new TextFileTranslator(file));
        this.currentState = this.interactor.getInitialState();
    }

    /**
     * Function that returns the output data stored in an instance of this class
     *
     * @return the string containing the output that should be presented to the user
     */
    public String presentOutput(){
        return this.output;
    }

    /**
     * Function that changes the current input
     * @param input the new input that should replace the old input
     */
    public void setInput(int input){
        this.input = input;
    }

    /**
     * Function that send the updated input data to the private instance, interactor, to handle state and data
     * processing and then present the new options based on the processing to the user
     */
    public void connectLogic(){
        this.currentState = this.interactor.handleInput(this.input);
        displayOptions();
    }

    /**
     * Helper function that takes state data and formulates the data into a string readable by the user
     */
    public void displayOptions(){
        StringBuilder outputString = new StringBuilder();
        ArrayList<String> options = currentState.getOptions();
        outputString.append(currentState.getDescription());
        for (int i = 0; i < options.size(); i++){
            outputString.append(options.get(i)).append("(").append(i).append("), ");
        }
        this.output = outputString.toString();

    }
}

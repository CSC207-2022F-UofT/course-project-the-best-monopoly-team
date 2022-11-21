package Interactors;

import Entities.State;
import UseCases.UseCaseInteractor;

import java.io.File;
import java.util.ArrayList;

public class IOController {
    private String output;
    private int input;
    private UseCaseInteractor interactor;
    private State currentState;

    public IOController(File file){
        this.output = "Welcome to Monopoly! Do you want to ";
        this.input = 0;
        this.interactor = new UseCaseInteractor(new TextFileTranslator(file));
        this.currentState = this.interactor.getInitialState();
    }
    public String presentOutput(){
        return this.output;
    }
    public void setInput(int input){
        this.input = input;
    }
    public void connectLogic(){
        if(input == currentState.getOptions().size()){
            this.currentState = this.interactor.handleInput(-1);
        }
        else {
            this.currentState = this.interactor.handleInput(this.input);
        }
        displayOptions();
    }
    public void displayOptions(){
        String newOutput = "";
        ArrayList<String> options = currentState.getOptions();
        this.output =  currentState.getDescription();
        for (int i = 0; i < options.size(); i++){
            this.output = this.output + options.get(i) + "(" + i + "), ";
        }
        if (currentState.isBackEnable()){
            this.output = this.output + "back" + "(" + options.size() + ")";
        }
    }
}

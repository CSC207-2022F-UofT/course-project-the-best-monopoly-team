package Interactors;

import Entities.Output;
import Entities.State;
import UseCases.UseCaseInteractor;

import java.util.ArrayList;

/***
 * OutputInteractor is a class that controls the data in the Output Entity and interacts with the UseCaseInteractor
 * to present output the user based on the current state of the game
 */
public class OutputInteractor {
    /**
     *  InstanceVar output: an Output object that keeps track and updates the output depending on the state of the game
     *  InstanceVar currentState: a State object that helps with determining the output that should be presented to the user
     */
    private final Output output;
    private State currentState;

    /**
     * The Constructor for the OutputInteractor Class
     * @param interactor: gets the initial state of the game
     */
    public OutputInteractor(UseCaseInteractor interactor){
        this.output = new Output();
        StateOutputReader createInitOutput = new StateOutputReader();
        createInitOutput.initStateHash();
        this.output.setInitStateHash(createInitOutput.getStateMap());
        this.currentState = interactor.getInitialState();
    }

    /**
     * Function to return the current Output message for context to the user
     * @return the context String
     */
    public String getOutputMessage() {
        updateLogicStates(this.currentState.getId());
        return this.output.getStateOutput(this.currentState.getId());
    }
    public State getCurrentState(){
        return this.currentState;
    }

    /**
     * This function deals with all the states that need to be updated periodically based on the current state of the game
     * @param state: the current state that game is in
     */
    public void updateLogicStates(String state){
        switch (state) {
            case "Main Tree Parent Node":
                updateMainTree();
                break;
            case "Send The Trade":
                updateSendTrade();
                break;
            case "Build House/Hotel":
                updateBuildProperty();
                break;
            case "Perform Action":
                updateCallAction();
                break;
            case "Property Unowned":
                updateEmptyPropertySpace();
                break;
            case "Auction Tree Parent Node":
                updateAuctionTree();
                break;
            case "Fold":
                updateFold();
                break;
            case "Choose Player (Steal)":
                updateSteal();
            case "Game Complete":
                updateEnding();
            case "Save The Game":
                updateSave();
        }
    }

    /**
     * All the functions below are helper update functions
     */
    public void updateMainTree(){
        String currString = this.currentState.getPlayer().getName() + " It's your turn! What do you want to do? You currently have " +
                this.currentState.getPlayer().getMoney() + " dollars";
        this.output.modifyStateOutput("Main Tree Parent Node", currString);
    }
    public void updateSave(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput("Save The Game", currString);
    }
    public void updateEnding(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput("Game Complete", currString);
    }
    public void updateSteal(){
        String currString = this.currentState.getDescription();
        this.output.modifyStateOutput("Choose Player (Steal)", currString);
    }
    public void updateSendTrade(){
        String currString = currentState.getTradingOpponent().getName() + ", Incoming trade from player " +
                currentState.getPlayer().getName() + " requesting for " + currentState.getTradingPlayerProperty().getName() +
                " in return for " + currentState.getCurrentPlayerProperty().getName();
        this.output.modifyStateOutput("Send The Trade", currString);
    }
    public void updateBuildProperty(){
        String currString = currentState.getCurrentPlayerProperty().getHouses() + " houses built on this property";
        this.output.modifyStateOutput("Build House/Hotel", currString);
    }

    public void updateCallAction(){
        String currString =  "You rolled a " + currentState.getRoll()+ currentState.getDescription();
        this.output.modifyStateOutput("Perform Action", currString);
    }
    public void updateEmptyPropertySpace(){
        String currString = "You rolled a " + currentState.getRoll() + " You have landed on " +
                currentState.getCurrentPlayerProperty().getName() + " and no ones owns this. It costs " +
                currentState.getCurrentPlayerProperty().getPrice() + " What do you want to do?";
        this.output.modifyStateOutput("Property Unowned", currString);
    }
    public void updateAuctionTree(){
        String currString = currentState.getPlayer().getName() + ", we are bidding on " + currentState.getBiddingProperty().getName() +
                " with the current pot being " + currentState.getBiddingPot();
        this.output.modifyStateOutput("Auction Tree Parent Node", currString);
    }
    public void updateFold(){
        String currString = currentState.getPlayer().getName() + " won the auction for " + currentState.getBiddingPot() + " dollars";
        this.output.modifyStateOutput("Fold", currString);
    }

    /**
     * Function to get the options the user has based on the state
     * @return the ArrayList of options in the current state
     */
    public ArrayList<String> getStateOptions(){
        ArrayList<String> currOptions = new ArrayList<>(this.currentState.getOptions());
        if (this.currentState.isBackEnable()){
            currOptions.add("back");
        }
        return currOptions;
    }

    /**
     * Update the current state based on changes on the previous state from the user's input
     * @param state: the updated state
     */
    public void updateState(State state){
        this.currentState = state;
    }

//    public String getOutput() {
//        updateLogicStates(this.currentState.getId());
//        return this.output.getStateOutput(this.currentState.getId());
//    }
}
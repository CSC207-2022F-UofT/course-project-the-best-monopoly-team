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
     *  InsatanceVar output: an Output object that keeps track and updates the output depending on the state of the game
     *  InstanceVar currentState: a State object that helps with determining the output that should be presented to the user
     */
    private Output output;
    private State currentState;

    /**
     * The Constructor for the OutputInteractor Class
     * @param interactor: gets the initial state of the game
     */
    public OutputInteractor(UseCaseInteractor interactor){
        this.output = new Output();
        this.currentState = interactor.getInitialState();
    }

    /**
     * setFinalOutput updates the string to be presented to the user by updating all the output strings that should
     * be presented to the user based on the state and presented all the options based on the state
     */
    public void setFinalOutput(){
        updateLogicStates(this.currentState.getId());
        this.output.setFinalOutput(this.output.getStateOutput(this.currentState.getId()));
        addOptionStrings();
    }

    /**
     * This function deals with all the states that need to be updated periodically based on the current state of the game
     * @param state: the current state that game is in
     */
    public void updateLogicStates(String state){
        StringBuilder currString = new StringBuilder();
        switch (state) {
            case "MainTree":
                currString.append(this.currentState.getPlayer().getName()).append(" It's your turn! What do you want to do? You currently have ")
                        .append(this.currentState.getPlayer().getMoney()).append(" dollars");
                this.output.modifyStateOutput("MainTree", currString.toString());
                currString.setLength(0);
                break;
            case "SendTrade":
                currString.append(currentState.getTradingOpponent().getName()).append(", Incoming trade from player ")
                        .append(currentState.getPlayer().getName())
                        .append(" requesting for ").append(currentState.getTradingPlayerProperty().getName())
                        .append(" in return for ").append(currentState.getCurrentPlayerProperty().getName());
                this.output.modifyStateOutput("SendTrade", currString.toString());
                currString.setLength(0);
                break;
            case "BuildProperty":
                currString.append(currentState.getCurrentPlayerProperty().getHouses()).append(" houses built on this property");
                this.output.modifyStateOutput("BuildProperty", currString.toString());
                currString.setLength(0);
                break;
            case "CallAction":
                currString.append("You rolled a ").append(currentState.getRoll()).append(currentState.getDescription());
                this.output.modifyStateOutput("CallAction", currString.toString());
                currString.setLength(0);
                break;
            case "EmptyPropertySpace":
                currString.append("You rolled a ").append(currentState.getRoll())
                        .append(" You have landed on ").append(currentState.getCurrentPlayerProperty().getName())
                        .append(" and no ones owns this. It costs ")
                        .append(currentState.getCurrentPlayerProperty().getPrice())
                        .append(" What do you want to do?");
                this.output.modifyStateOutput("EmptyPropertySpace", currString.toString());
                currString.setLength(0);
                break;
            case "AuctionTree":
                currString.append(currentState.getPlayer().getName())
                        .append(", we are bidding on ").append(currentState.getBiddingProperty().getName())
                        .append(" with the current pot being ").append(currentState.getBiddingPot());
                this.output.modifyStateOutput("AuctionTree", currString.toString());
                currString.setLength(0);
                break;
            case "Fold":
                currString.append(currentState.getPlayer().getName()).append(" won the auction for ")
                        .append(currentState.getBiddingPot()).append(" dollars");
                this.output.modifyStateOutput("Fold", currString.toString());
                currString.setLength(0);
        }
    }

    /**
     * Function to get the options the user has based on the state and concatenate those options to present to the user
     */
    public void addOptionStrings(){
        StringBuilder currOptions = new StringBuilder("\n");
        ArrayList<String> options = this.currentState.getOptions();
        for (int i = 0; i < options.size(); i++)
            currOptions.append(options.get(i)).append("(").append(i).append("), ");
        if (this.currentState.isBackEnable())
            currOptions.append("back").append("(").append(options.size()).append(")");
        this.output.addToFinalOutput(currOptions.toString());
    }

    /**
     * @return the final output string that is to be presented to the user
     */
    public String getOutput(){
        return this.output.getFinalOutput();
    }

    /**
     * Update the current state based on changes on the previous state from the user's input
     * @param state: the updated state
     */
    public void updateState(State state){
        this.currentState = state;
    }
}

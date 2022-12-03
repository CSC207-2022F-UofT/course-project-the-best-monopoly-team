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
        if(input == currentState.getOptions().size()){
            this.currentState = this.interactor.handleInput(-1);
        }
        else {
            this.currentState = this.interactor.handleInput(this.input);
        }
        displayOptions();
    }

    /**
     * Helper function that takes state data and formulates the data into a string readable by the user
     */
    public void displayOptions(){
    //    StringBuilder outputString = new StringBuilder();
 //       outputString.append(currentState.getDescription());
        this.output = stateToString(currentState);

    }
    public String stateToString(State state){
        StringBuilder outputString = new StringBuilder();
        switch (state.getId()){
            case "Initial Menu Parent Node":
                outputString.append("Welcome to Monopoly++, Would you like to do?");
                break;
            case "New Game":
                outputString.append("What mode would you like to play?");
                break;
            case "Choose Game Mode":
                outputString.append("How many players");
                break;
            case "Number of Players":
                outputString.append("How many rounds? ");
                break;
            case "Game Length":
                outputString.append("Create the game?");
                break;
            case "Create New Game":
                //Produces no output
                break;
            case "Load Game":
                outputString.append("What save do you want to load ");
                break;
            case "Choose Save":
                outputString.append("Confirm the load? ");
                break;
            case "Create Loaded Game":
                break;
            case "Main Tree Parent Node":
                outputString.append(currentState.getPlayer().getName())
                        .append(" It's your turn! What do you want to do? You currently have ")
                        .append(currentState.getPlayer().getMoney()).append(" dollars");
                break;
            case "Trade":
                outputString.append("Who do you want to trade with? ");
                break;
            case "Pick Player (Trade)":
                outputString.append("What property do you want from the player? ");
                break;
            case "Trader/Tradee Has No Properties":
                outputString.append("Trade cannot be done; one of you have no properties");
                break;
            case "Pick Item Of Opponent":
                outputString.append("What property are you willing to trade? ");
                break;
            case "Pick Item Of Self":
                outputString.append("Send the trade?");
                break;
            case "Send The Trade":
                outputString.append(currentState.getTradingOpponent().getName()).append(", Incoming trade from player ")
                        .append(currentState.getPlayer().getName())
                        .append(" requesting for ").append(currentState.getTradingPlayerProperty().getName())
                        .append(" in return for ").append(currentState.getCurrentPlayerProperty().getName());
                break;
            case "Manage Property":
                outputString.append("What property do you want to manage? ");
                break;
            case "User Has No Properties (Manage Properties)":
                outputString.append("You have no properties :(");
                break;
            case "Select The Property (Manage Property)":
                outputString.append("What do you want to do with the property? ");
                break;
            case "Mortgage Property":
                outputString.append("Are you sure you want to mortgage? ");
                break;
            case "Un-Mortgage property":
                //TODO implement this
                break;
            case "Build House/Hotel":
                outputString.append(currentState.getCurrentPlayerProperty().getHouses()).append(" houses built on this property");
                break;
            case "Roll The Dice":
                //Produces no output
                break;
            case "Already Rolled":
                outputString.append("You already rolled this round! ");
                break;
            case "Perform Action":
                outputString.append("You rolled a ").append(currentState.getRoll()).append(currentState.getDescription());
                break;
            case "Property Unowned":
                outputString.append("You rolled a ").append(currentState.getRoll())
                        .append(" You have landed on ").append(currentState.getCurrentPlayerProperty().getName())
                        .append(" and no ones owns this. It costs ")
                        .append(currentState.getCurrentPlayerProperty().getPrice())
                        .append(" What do you want to do?");
                break;
            case "Buy Property":
                //Produces no output
                break;
            case "Auction":
            case "Auction Tree Parent Node":
                outputString.append(currentState.getPlayer().getName())
                        .append(", we are bidding on ").append(currentState.getBiddingProperty().getName())
                        .append(" with the current pot being ").append(currentState.getBiddingPot());
                break;
            case "Steal":
                outputString.append("What player do you want to steal from?");
                break;
            case "Choose Player (Steal)":
                outputString.append(currentState.getDescription());
                break;
            case "End Turn":
                outputString.append("You can't end your turn, you have negative money");
                break;
            case "Settings Menu":
                outputString.append("Welcome to the settings menu!");
                break;
            case "Exit Game":
                outputString.append("Are you sure you want to exit?");
                break;
            case "Save The Game":
                outputString.append("Game saved!");
                break;
            case "Bankruptcy":
                outputString.append("Confirm bankruptcy?");
                break;
            case "Confirm Action":
                //Produces no output
                break;
            case "Information Node":
                //Produces no output
                break;
            case "Low Option":
                //Produces no output
                break;
            case "Medium Option":
                //Produces no output
                break;
            case "High Option":
                //Produces no output
                break;
            case "Fold":
                outputString.append(currentState.getPlayer().getName()).append(" won the auction for ")
                        .append(currentState.getBiddingPot()).append(" dollars");
                break;
            case "Trading Parent Node":
                //Produces no output
                break;
            case "Accept The Trade":
                outputString.append("Trade success");
                break;
            case "Decline The Trade":
                outputString.append("Trade failure");
                break;
        }
        ArrayList<String> options = currentState.getOptions();
        outputString.append("\n");
        for (int i = 0; i < options.size(); i++){
            outputString.append(options.get(i)).append("(").append(i).append("), ");
        }
        if (currentState.isBackEnable()){
            outputString.append("back").append("(").append(options.size()).append(")");
        }



        return outputString.toString();
    }
}

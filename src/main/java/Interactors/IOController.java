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
            case "InitialMenu":
                outputString.append("Welcome to Monopoly++, Would you like to do?");
                break;
            case "NewGame":
                outputString.append("What mode would you like to play?");
                break;
            case "ChooseGameMode":
                outputString.append("How many players");
                break;
            case "NumberOfPlayers":
                outputString.append("How many rounds? ");
                break;
            case "GameLength":
                outputString.append("Create the game?");
                break;
            case "CreateNewGame":
                //NOTHING
                break;
            case "LoadGame":
                outputString.append("What save do you want to load ");
                break;
            case "ChooseSave":
                outputString.append("Confirm the load? ");
                break;
            case "CreateLoadedGame":
                break;
            case "MainTree":
                outputString.append(currentState.getPlayer().getName())
                        .append(" It's your turn! What do you want to do? You currently have ")
                        .append(currentState.getPlayer().getMoney()).append(" dollars");
                break;
            case "Trade":
                outputString.append("Who do you want to trade with? ");
                break;
            case "PickPlayer":
                outputString.append("What property do you want from the player? ");
                break;
            case "NothingToTrade":
                outputString.append("Trade cannot be done; one of you have no properties");
                break;
            case "PickItemOp":
                outputString.append("What property are you willing to trade? ");
                break;
            case "PickItemSelf":
                outputString.append("Send the trade?");
                break;
            case "SendTrade":
                outputString.append(currentState.getTradingOpponent().getName()).append(", Incoming trade from player ")
                        .append(currentState.getPlayer().getName())
                        .append(" requesting for ").append(currentState.getTradingPlayerProperty().getName())
                        .append(" in return for ").append(currentState.getCurrentPlayerProperty().getName());
                break;
            case "ManageProperty":
                outputString.append("What property do you want to manage? ");
                break;
            case "NoProperties":
                outputString.append("You have no properties :(");
                break;
            case "SelectProperty":
                outputString.append("What do you want to do with the property? ");
                break;
            case "Mortgage":
                outputString.append("Are you sure you want to mortgage? ");
                break;
            case "UnMortgage":

                break;
            case "BuildProperty":
                outputString.append(currentState.getCurrentPlayerProperty().getHouses()).append(" houses built on this property");
                break;
            case "Roll":
                //nothing here
                break;
            case "AlreadyRolled":
                outputString.append("You already rolled this round! ");
                break;
            case "CallAction":
                outputString.append("You rolled a ").append(currentState.getRoll()).append(currentState.getDescription());
                break;
            case "EmptyPropertySpace":
                outputString.append("You rolled a ").append(currentState.getRoll())
                        .append(" You have landed on ").append(currentState.getCurrentPlayerProperty().getName())
                        .append(" and no ones owns this. It costs ")
                        .append(currentState.getCurrentPlayerProperty().getPrice())
                        .append(" What do you want to do?");
                break;
            case "Buy":
                //NOTHING
                break;
            case "Auction":
            case "AuctionTree":
                outputString.append(currentState.getPlayer().getName())
                        .append(", we are bidding on ").append(currentState.getBiddingProperty().getName())
                        .append(" with the current pot being ").append(currentState.getBiddingPot());
                break;
            case "Steal":
                outputString.append("What player do you want to steal from?");
                break;
            case "ChoosePlayer":
                //Nothing here
                break;
            case "EndTurn":
                outputString.append("You can't end your turn, you have negative money");
                break;
            case "SettingsMenu":
                outputString.append("Welcome to the settings menu!");
                break;
            case "ExitGame":
                outputString.append("Are you sure you want to exit?");
                break;
            case "SaveGame":
                outputString.append("Game saved!");
                break;
            case "Bankruptcy":
                outputString.append("Confirm bankruptcy?");
                break;
            case "Confirmation":
                //Nothing here
                break;
            case "Information":
                //Nothing here
                break;
            case "LowOption":
                break;
            case "MediumOption":
                break;
            case "HighOption":
                break;
            case "Fold":
                outputString.append(currentState.getPlayer().getName()).append(" won the auction for ")
                        .append(currentState.getBiddingPot()).append(" dollars");
                break;
            case "TradeTree":
                break;
            case "AcceptTrade":
                outputString.append("Trade success");
                break;
            case "DeclineTrade":
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

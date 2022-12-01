package Entities;

import java.util.HashMap;

/**
 * Output Entity stores all the output data that is to be presented to the user each move
 */
public class Output {
    /**
     * InstanceVar stateOutput: A hashmap that contains predefinied outputs for specific states
     * InstanceVar finalOutput: the string object containing the cumulative output
     */
    private HashMap<String, String> stateOutput;
    private String finalOutput;

    /**
     * The constructor for this class, it pre-defines all the key-value pairs in the hashmap
     * note: some values are null as those values do not present any output to the user and are simply processing stages
     * note: some keys start off as empty string as those change based on logic, so empty strings are used as placeholders
     * note: the Value with respect to each key represents the output giving the option for the user to choose what they want to do
     * i.e. the output string value is to determine the next state from the current state
     */
    public Output(){
        this.stateOutput = new HashMap<>();
        this.stateOutput.put("InitialMenu", "Welcome to Monopoly++, Would you like to ");
        this.stateOutput.put("NewGame", "What mode would you like to play? ");
        this.stateOutput.put("ChooseGameMode","How many players? ");
        this.stateOutput.put("NumberOfPlayers", "How many rounds? ");
        this.stateOutput.put("GameLength", "Create the game? ");
        this.stateOutput.put("CreateNewGame", null);
        this.stateOutput.put("LoadGame", "What save do you want to load? ");
        this.stateOutput.put("ChooseSave", "Confirm the load? ");
        this.stateOutput.put("CreateLoadedGame", null);
        this.stateOutput.put("MainTree", "");
        this.stateOutput.put("Trade", "Who do you want to trade with? ");
        this.stateOutput.put("PickPlayer", "What property do you want from the player? ");
        this.stateOutput.put("NothingToTrade", "Trade cannot be done; one of you have no properties");
        this.stateOutput.put("PickItemOp", "What property are you willing to trade? ");
        this.stateOutput.put("PickItemSelf", "Send the trade? ");
        this.stateOutput.put("SendTrade", "");
        this.stateOutput.put("ManageProperty", "What property do you want to manage? ");
        this.stateOutput.put("NoProperties", "You have no properties :(");
        this.stateOutput.put("SelectProperty", "What do you want to do with the property? ");
        this.stateOutput.put("Mortgage", "Are you sure you want to mortgage? ");
        this.stateOutput.put("UnMortgage", null);
        this.stateOutput.put("BuildProperty", "");
        this.stateOutput.put("AlreadyRolled", "You already rolled this round! ");
        this.stateOutput.put("CallAction", "");
        this.stateOutput.put("EmptyPropertySpace", "");
        this.stateOutput.put("Buy", null);
        this.stateOutput.put("Auction", null);
        this.stateOutput.put("AuctionTree", "");
        this.stateOutput.put("Steal", "What player do you want to steal from? ");
        this.stateOutput.put("ChoosePlayer", null);
        this.stateOutput.put("EndTurn", "You can't end your turn, you have negative money");
        this.stateOutput.put("SettingsMenu", "Welcome to the settings menu!");
        this.stateOutput.put("ExitGame", "Are you sure you want to exit? ");
        this.stateOutput.put("SaveGame", "Game saved!");
        this.stateOutput.put("Bankruptcy", "Confirm bankruptcy? ");
        this.stateOutput.put("Confirmation", null);
        this.stateOutput.put("Information", null);
        this.stateOutput.put("LowOption", null);
        this.stateOutput.put("MediumOption", null);
        this.stateOutput.put("HighOption", null);
        this.stateOutput.put("Fold", "");
        this.stateOutput.put("TradeTree", null);
        this.stateOutput.put("AcceptTrade", "Trade success");
        this.stateOutput.put("DeclineTrade", "Trade failure");

        this.finalOutput = "";
    }

    /**
     * Function that returns the value from the key-value pair of the hashmap
     * @param state: the key for the state that needs to be accessed
     * @return the value to be used in the output from the key used
     */
    public String getStateOutput(String state){
        return this.stateOutput.get(state);
    }

    /**
     * Function that modifies some key-value pairs as some outputs change based on state logic
     * @param state: the current state
     * @param output: the new output obtained from processing logic
     */
    public void modifyStateOutput(String state, String output){
        this.stateOutput.replace(state, output);
    }

    /**
     * Function that returns the final complied string
     * @return the final complied output string
     */
    public String getFinalOutput(){
        return this.finalOutput;
    }

    /**
     * set the finalOutput string
     * @param finalOutput: the output value that is to be used
     */
    public void setFinalOutput(String finalOutput) {
        this.finalOutput = finalOutput;
    }

    /**
     * Function that adds strings to the final output, e.g. for adding options to the string
     * @param adder: the additional output information needed to be added to the output string
     */
    public void addToFinalOutput(String adder){
        this.finalOutput = this.finalOutput + " " + adder;
    }
}

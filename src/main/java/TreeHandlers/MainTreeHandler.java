package TreeHandlers;

import Entities.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTreeHandler extends TreeHandler {
    //mainStates[0]: reserved for confirmation node
    //mainStates[1]: reserved for sendTrade node and auction node
    //mainStates[2]: reserved for roll
    int[] mainStates = new int[5];
    String answer;
    public MainTreeHandler(){
    }
    public MainTreeHandler(int[] states){
        mainStates = states;
    }
    public State handleInput(int input){
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        switch (currentTree.getName()){
            case "Trade":
                currentState.setDescription("Who do you want to trade with?");
                //Case trade selected
                //provide a list of all possible players considering the current player is not an option
                ArrayList<Player> playerCopy = new ArrayList<Player>(Arrays.asList(this.board.getPlayers()));
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickPlayer":
                currentState.setDescription("What property do you want from the player?");
                //Case player picked
                //adds the chosen player index in selected options
                selectedOptions.put(currentTree.getName(), input);
                //provide item options from the inventory of the selected player
                Player selectedPlayer = board.getPlayers()[input];
                ArrayList<Property> playerProperties = selectedPlayer.properties;
                //using "i" starting from 0 to number of properties the player has - 1
                for (int i = 0; i < playerProperties.size(); i++){
                    currentState.addOptions(playerProperties.get(i).getName());
                }
                break;
            case "PickItemOp":
                currentState.setDescription("What property are you willing to trade?");
                //Case picking the item of the opponent
                //the input corresponds to the index of the target player in this.board.getPlayers()
                selectedOptions.put(currentTree.getName(), input);
                //provide item options from the current player's inventory
                ArrayList<Property> currentPlayerInventory = currentPlayer.properties;
                //using "i" starting from 0 to number of properties the player has - 1
                for (int i = 0; i < currentPlayerInventory.size(); i++){
                    currentState.addOptions(currentPlayerInventory.get(i).getName());
                }
                break;
            case "PickItemSelf":
                currentState.setDescription("Send the trade?");
                //the input corresponds to the index of the opponent targeted property;
                selectedOptions.put(currentTree.getName(), input);
                //Case picking the item of the player
                //send the trade offer using selectedOptions. Index 0 will be the selected item from opponent and
                //index 1 will be the selected item from the current player.
                break;
            case "SendTrade":
                if (mainStates[1] == 0) {
                    selectedOptions.put(currentTree.getName(), input);
                    //input corresponds to the index of the current player's selected property
                    //Case sending the trade
                    //the input should be 0 or 1. 0 if the trade was accepted, 1 if the trade was declined.
                    Player tradingOpponent = this.board.getPlayers()[selectedOptions.get("PickPlayer")];

                    currentState.setDescription("Incoming trade from player " + currentPlayer.name +
                            " requesting for "+ tradingOpponent.properties.get(selectedOptions.get("PickItemOp"))
                            + " in return for "+ currentPlayer.properties.get(selectedOptions.get("PickItemSelf")));

                    returnTree = currentTree;
                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()
                    this.currentPlayer = tradingOpponent;
                    gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
                    addSwitchOptions(currentState);

                    mainStates[1] = 1;
                }
                else {
                    currentState.setDescription(description);
                    currentState.addOptions("ok");
                    mainStates[1] = 0;
                    break;
                }
                break;
            case "ManageProperty":
                currentState.setDescription("What property do you want to manage?");
                //Case manage property selected
                //provide options on the properties available
                ArrayList<Property> currentPlayerProperties = this.currentPlayer.properties;
                for(int i = 0; i < currentPlayerProperties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "SelectProperty":
                currentState.setDescription("What do you want to do with the property?");
                //Case property selected (adds the property index)
                selectedOptions.put(currentTree.getName(), input);
                //the player chooses what to do to the property
                //0 is for the mortgage option
                //1 is for the un mortgaged option
                //2 is for the build house option
                currentState.addOptions(Integer.toString(0));
                currentState.addOptions(Integer.toString(1));
                currentState.addOptions(Integer.toString(2));
                break;
            case "Mortgage":
                if (mainStates[0] == 1) {
                    //Case player selected what to do with the property
                    //the player chooses to mortgage the property
                    Property targetProperty = this.board.getProperties()[selectedOptions.get("SelectProperty")];
                    this.currentPlayer.mortgage(targetProperty);
                    mainStates[0] = 0;
                }
                else{
                    currentState.setDescription("Are you sure you want to mortgage?");
                    currentState.addOptions("yes");
                    currentState.addOptions("no");
                }
                break;
            case "BuildProperty":
                Property targetProperty = this.board.getProperties()[selectedOptions.get("SelectProperty")];
                //the player chooses to build houses
                this.currentPlayer.buildHouse(targetProperty,1);
                currentState.setDescription(targetProperty.getHouses() + " houses built on this property");
                currentState.addOptions("ok");
                break;
            case "Roll":
                if (mainStates[2] == 0) {
                    //Case roll selected
                    //We can determine if a player lands on a property by checking if the position of
                    //the player is on one with a property on it (not 0,2,7,10,17,20,22,30,33,36,38).
                    Cell landedOnCell = board.getCell(this.currentPlayer.position);
                    if (landedOnCell instanceof Property &&
                            ((Property) landedOnCell).getOwner() == null) {
                        gameLogicInteractor.transverseCurrentTree(0);
                    } else {
                        answer = landedOnCell.performAction(currentPlayer);
                        gameLogicInteractor.transverseCurrentTree(1);
                    }
                    mainStates[2] = 1;
                }
                else{
                    currentState.setDescription("You already rolled this round!");
                    currentState.addOptions("ok");
                }
                break;
            case "CallAction":
                currentState.setDescription(answer);
                currentState.addOptions("ok");
                break;
            case "EmptyPropertySpace":
                currentState.setDescription("You have landed on a free property. What do you want to do?");
                addSwitchOptions(currentState);
                break;
            case "Buy":
                //Case buy selected
                //player buys the property that the player lands on
                targetProperty = board.getProperties()[this.currentPlayer.position];

                //indicates that the player can afford it and sets the property owner as the current player and
                //deducts the player's money.
                this.currentPlayer.pay(targetProperty.getPrice());
                this.currentPlayer.properties.add(targetProperty);
                targetProperty.setOwner(this.currentPlayer);

                gameLogicInteractor.setCurrentTreeToMaxParent();
                currentState = getInitialState();
                break;
            case "Auction":
                if (mainStates[1] == 0) {
                    //Case auction selected
                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()

                    returnTree = currentTree;
                    gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[2]);
                    addSwitchOptions(currentState);
                    mainStates[1] = 1;
                }
                else{
                    currentState.setDescription(description);
                    currentState.addOptions("ok");
                    mainStates[1] = 0;
                }
                break;
            case "Steal":
                currentState.setDescription("What player do you want to steal from?");
                //Case steal selected
                //provide options of which players we can steal from
                playerCopy = new ArrayList<Player>(Arrays.asList(this.board.getPlayers()));
                playerCopy.remove(this.currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "ChoosePlayer":
                //Case player to steal from selected
                //steal from the player

                playerCopy = new ArrayList<Player>(Arrays.asList(this.board.getPlayers()));
                playerCopy.remove(this.currentPlayer);
                String stealStatus = this.currentPlayer.steal(playerCopy.get(input));

                //TODO: add description of stealing
                currentState.setDescription(stealStatus);
                currentState.addOptions("ok");
                break;
            case "EndTurn":
                if (currentPlayer.money < 0){
                    currentPlayer = players[(getCurrentPlayerIndex()+1)%players.length];
                    mainStates = new int[5];
                    currentState = getInitialState();
                }
                else{
                    currentState.setDescription("You can't end your turn, you have negative money!");
                    currentState.addOptions("Ok");
                }
                gameLogicInteractor.setCurrentTreeToMaxParent();
                break;
            case "SettingsMenu":
                currentState.setDescription("Welcome to the settings menu");
                addSwitchOptions(currentState);
                break;
            case "ExitGame":
                if (mainStates[0] == 1) {
                    currentState.setDescription("Are you sure you want to exit?");
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                }
                else{
                    currentState.setExitToMenu(true);
                }
                break;
            case "SaveGame":
                currentState.setDescription("Game saved!");
                currentState.addOptions("ok");
                currentState.setSaveGame(true);
                break;
            case "Bankruptcy":
                if (mainStates[0] == 1) {
                    mainStates[0] = 0;
                    currentPlayerProperties = this.currentPlayer.properties;
                    for (Property targetedProperty : currentPlayerProperties) {
                        targetedProperty.setOwner(null);
                        targetedProperty.setHouses(0);
                        targetedProperty.setMortgageStatus(false);
                    }
                    this.board.removePlayer(this.currentPlayer);
                    // switching players
                    currentPlayer = players[(getCurrentPlayerIndex()+1)%players.length];
                    mainStates = new int[5];
                    gameLogicInteractor.setCurrentTreeToMaxParent();
                    currentState = getInitialState();
                }
                else {
                    currentState.setDescription("Confirm bankruptcy?");
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                }
                break;
            case "Confirmation":
                if (input == 0) {
                    mainStates[0] = 1;
                    gameLogicInteractor.setCurrentTree((GameLogicTree) currentTree.getParent());
                    handleInput(input);
                }
                else{
                    gameLogicInteractor.setCurrentTreeToMaxParent();
                    currentState = getInitialState();
                }
                break;
            case "Information":
                gameLogicInteractor.setCurrentTreeToMaxParent();
                currentState = getInitialState();
                break;
        }

        return currentState;
    }
}

package TreeHandlers;

import Entities.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTreeHandler extends TreeHandler {
    //mainStates[0]: reserved for confirmation node
    //mainStates[1]: reserved for sendTrade node and auction node (switching the trees)
    //mainStates[2]: reserved for roll
    //mainStates
    int[] mainStates = new int[5];
    String answer;
    GameLogicTree confirmationReturn;
    String diceroll;
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
                currentState.setBackEnable(true);


                currentState.setDescription("Who do you want to trade with? ");

                //provide a list of all possible players considering the current player is not an option
                ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(playerCopy.get(i).getName());
                }
                break;
            case "PickPlayer":
                currentState.setBackEnable(true);
                currentState.setDescription("What property do you want from the player? ");

                //adds the chosen player index in selected options (who the current player wants to trade with)
                selectedOptions.put(currentTree.getName(), input);

                //provide item options from the inventory of the selected player
                Player selectedPlayer = board.getPlayers().get(input);
                ArrayList<Property> playerProperties = selectedPlayer.getProperties();
                if (playerProperties.isEmpty() || currentPlayer.getProperties().isEmpty()){
                    gameLogicInteractor.transverseCurrentTree(1);
                    currentState = handleInput(0);
                    break;
                }
                //using "i" starting from 0 to number of properties the player has - 1
                for (int i = 0; i < playerProperties.size(); i++){
                    currentState.addOptions(playerProperties.get(i).getName());
                }
                break;
            case "NothingToTrade":
                currentState.setDescription("Trade cannot be done; one of you have no properties");
                currentState.addOptions("Ok");
                break;
            case "PickItemOp":
                currentState.setBackEnable(true);
                currentState.setDescription("What property are you willing to trade? ");

                //the input corresponds to the index of the opponent targeted property;
                selectedOptions.put(currentTree.getName(), input);

                //provide item options from the current player's inventory
                ArrayList<Property> currentPlayerInventory = currentPlayer.getProperties();
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < currentPlayer.getProperties().size(); i++){
                    currentState.addOptions(currentPlayerInventory.get(i).getName());
                }
                break;
            case "PickItemSelf":
                currentState.setBackEnable(true);
                currentState.setDescription("Send the trade?");
                currentState.addOptions("yes");
                //TODO: make this back option do something
                currentState.addOptions("no");

                //the input corresponds to the index of the current player's targeted property;
                selectedOptions.put(currentTree.getName(), input);

                break;
            case "SendTrade":
                if (mainStates[1] == 0) {
                    Player tradingOpponent = board.getPlayers().get(selectedOptions.get("PickPlayer"));

                    currentState.setDescription("Incoming trade from player " + currentPlayer.getName() +
                            " requesting for "+ tradingOpponent.getProperties().get(selectedOptions.get("PickItemOp")).getName()
                            + " in return for "+ currentPlayer.getProperties().get(selectedOptions.get("PickItemSelf")).getName());

                    returnTree = currentTree;
                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()
                    currentPlayer = tradingOpponent;
                    gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
                    addSwitchOptions(currentState);

                    mainStates[1] = 1;
                }
                else {
                    currentState.setDescription(descriptionOtherTrees);
                    currentState.addOptions("ok");
                    mainStates[1] = 0;
                    break;
                }
                break;
            case "ManageProperty":
                currentState.setBackEnable(true);
                currentState.setDescription("What property do you want to manage? ");
                //Case manage property selected
                //provide options on the properties available
                ArrayList<Property> currentPlayerProperties = currentPlayer.getProperties();
                if (currentPlayerProperties.isEmpty()){
                    gameLogicInteractor.transverseCurrentTree(1);
                    currentState = handleInput(0);
                }
                for(int i = 0; i < currentPlayerProperties.size(); i++){
                    currentState.addOptions(currentPlayerProperties.get(i).getName());
                }

                break;
            case "NoProperties":
                currentState.setDescription("You have no properties :(");
                currentState.addOptions("Ok");
                break;

            case "SelectProperty":
                currentState.setBackEnable(true);
                currentState.setDescription("What do you want to do with the property? ");
                //Case property selected (adds the property index)
                selectedOptions.put(currentTree.getName(), input);

                //the player chooses what to do to the property
                currentState.addOptions("Mortgage");
                currentState.addOptions("Unmortgage");
                currentState.addOptions("Build a house");
                break;
            case "Mortgage":
                if (mainStates[0] == 1) {
                    //Case player selected what to do with the property
                    //the player chooses to mortgage the property
                    Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));
                    currentPlayer.mortgage(targetProperty);
                    mainStates[0] = 0;
                    currentState = afterBottomNode();
                }
                else{
                    currentState.setDescription("Are you sure you want to mortgage? ");
                    currentState.addOptions("yes");
                    currentState.addOptions("no");
                    confirmationReturn = currentTree;
                }
                break;
            case "BuildProperty":
                Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("SelectProperty"));
                //the player chooses to build houses
                currentPlayer.buildHouse(targetProperty,1);
                currentState.setDescription(targetProperty.getHouses() + " houses built on this property");
                currentState.addOptions("ok");
                break;
            case "Roll":
                if (mainStates[2] == 0) {
                    //Case roll selected
                    //We can determine if a player lands on a property by checking if the position

                    diceroll = currentPlayer.rollDice();
                    board.updatePlayerPosition(currentPlayer);
                    Cell landedOnCell = board.getCell(currentPlayer.getPosition());
                    if (landedOnCell instanceof Property &&
                            ((Property) landedOnCell).getOwner() == null) {
                        gameLogicInteractor.transverseCurrentTree(0);
                    } else {
                        answer = landedOnCell.performAction(currentPlayer,board);
                        gameLogicInteractor.transverseCurrentTree(1);
                    }
                    currentState = handleInput(0);
                    mainStates[2] = 1;
                }
                else{
                    gameLogicInteractor.transverseCurrentTree(2);
                    currentState = handleInput(0);
                }
                break;
            case "AlreadyRolled":
                currentState.setDescription("You already rolled this round! ");
                currentState.addOptions("ok");
                break;
            case "CallAction":
                currentState.setDescription("You rolled a " + diceroll + answer);
                currentState.addOptions("ok");
                break;
            case "EmptyPropertySpace":
                targetProperty = (Property) board.getCell(currentPlayer.getPosition());
                currentState.setDescription("You rolled a "+diceroll+"You have landed on "+ targetProperty.getName() +" a free property. " +
                        "It costs "+ targetProperty.getPrice() + ". What do you want to do? ");
                addSwitchOptions(currentState);
                break;
            case "Buy":
                //Case buy selected
                //player buys the property that the player lands on
                targetProperty = (Property) board.getCell(currentPlayer.getPosition());

                //indicates that the player can afford it and sets the property owner as the current player and
                //deducts the player's money.
                currentPlayer.pay(targetProperty.getPrice());
                currentPlayer.getProperties().add(targetProperty);
                targetProperty.setOwner(currentPlayer);
                currentState = afterBottomNode();

                break;
            case "Auction":
                if (mainStates[1] == 0) {
                    //Case auction selected
                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()

                    returnTree = currentTree;
                    gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[2]);
                    gameLogicInteractor.setupAuction();
                    currentState = getInitialState();
                    mainStates[1] = 1;
                }
                else{
                    currentState.setDescription(descriptionOtherTrees);
                    currentState.addOptions("ok");
                    mainStates[1] = 0;
                }
                break;
            case "Steal":
                currentState.setBackEnable(true);
                currentState.setDescription("What player do you want to steal from? ");
                //Case steal selected
                //provide options of which players we can steal from
                playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "ChoosePlayer":
                //Case player to steal from selected
                //steal from the player

                playerCopy = new ArrayList<Player>(board.getPlayers());
                playerCopy.remove(currentPlayer);
                String stealStatus = currentPlayer.steal(playerCopy.get(input));

                //TODO: add description of stealing
                currentState.setDescription(stealStatus);
                currentState.addOptions("ok");
                break;
            case "EndTurn":
                if (currentPlayer.getMoney() >= 0){
                    changePlayers();
                    mainStates = new int[5];
                    currentState = afterBottomNode();
                }
                else{
                    currentState.setDescription("You can't end your turn, you have negative money! ");
                    currentState.addOptions("Ok");
                }
                break;
            case "SettingsMenu":
                currentState.setBackEnable(true);
                currentState.setDescription("Welcome to the settings menu! ");
                addSwitchOptions(currentState);
                break;
            case "ExitGame":
                if (mainStates[0] == 1) {
                    mainStates[0]= 0;
                    currentState.setExitToMenu(true);
                }
                else{
                    currentState.setDescription("Are you sure you want to exit? ");
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                    confirmationReturn = currentTree;
                }
                break;
            case "SaveGame":
                currentState.setDescription("Game saved! ");
                currentState.addOptions("ok");
                currentState.setSaveGame(true);
                break;
            case "Bankruptcy":
                if (mainStates[0] == 1) {
                    mainStates[0] = 0;
                    currentPlayerProperties = currentPlayer.getProperties();
                    for (Property targetedProperty : currentPlayerProperties) {
                        targetedProperty.setOwner(null);
                        targetedProperty.setHouses(0);
                        targetedProperty.setMortgageStatus(false);
                    }
                    Player tempPlayer = currentPlayer;
                    changePlayers();
                    board.removePlayer(tempPlayer);
                    // switching players
                    mainStates = new int[5];
                    currentState = afterBottomNode();
                }
                else {
                    confirmationReturn = currentTree;
                    currentState.setDescription("Confirm bankruptcy? ");
                    currentState.addOptions("Yes");
                    currentState.addOptions("No");
                }
                break;
            case "Confirmation":
                if (input == 0) {
                    mainStates[0] = 1;
                    gameLogicInteractor.setCurrentTree(confirmationReturn);
                    currentState = handleInput(input);
                }
                else{
                    currentState = afterBottomNode();
                }
                break;
            case "Information":
                currentState = afterBottomNode();
                break;
        }
        currentTree.setPreviousState(currentState);

        return currentState;
    }
    public State afterBottomNode(){
        gameLogicInteractor.setCurrentTreeToMaxParent();
        return getInitialState();
    }

}

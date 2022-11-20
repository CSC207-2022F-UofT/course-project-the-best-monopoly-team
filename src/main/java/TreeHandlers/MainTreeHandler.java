package TreeHandlers;

import Entities.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTreeHandler extends TreeHandler {

    public State handleInput(int input){
        State currentState = new State();
        switch (gameLogicInteractor.currentTree.getName()){
            case "MainTree":
                return getInitialState();
            case "Trade":
                //Case trade selected
                //provide a list of all possible players considering the current player is not an option
                ArrayList<Player> playerCopy = new ArrayList<Player>(Arrays.asList(this.board.getPlayers()));
                playerCopy.remove(currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickPlayer":
                //Case player picked
                //adds the chosen player index in selected options
                selectedOptions.put(gameLogicInteractor.currentTree.getName(), input);
                //provide item options from the inventory of the selected player
                Player selectedPlayer = board.getPlayers()[input];
                ArrayList<Property> playerProperties = selectedPlayer.getProperties();
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < playerProperties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickItemOp":
                //Case picking the item of the opponent
                //the input corresponds to the index of the target player in this.board.getPlayers()
                selectedOptions.put(gameLogicInteractor.currentTree.getName(), input);
                //provide item options from the current player's inventory
                ArrayList<Property> currentPlayerInventory = currentPlayer.getProperties();
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < currentPlayer.getProperties().size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickItemSelf":
                //the input corresponds to the index of the opponent targeted property;
                selectedOptions.put(gameLogicInteractor.currentTree.getName(), input);
                //Case picking the item of the player
                //send the trade offer using selectedOptions. Index 0 will be the selected item from opponent and
                //index 1 will be the selected item from the current player.
                break;
            case "SendTrade":
                selectedOptions.put(gameLogicInteractor.currentTree.getName(), input);
                //input corresponds to the index of the current player's selected property
                //Case sending the trade
                //the input should be 0 or 1. 0 if the trade was accepted, 1 if the trade was declined.
                if(input == 0){
                    //TODO: Process the trade.

                    returnPlayerIndex = getCurrentPlayerIndex();
                    //returnPlayerAddress will hold the original player index in this.board.getPlayers()
                    this.currentPlayer = this.board.getPlayers()[selectedOptions.get("PickPlayer")];
                    gameLogicInteractor.currentTree = gameLogicInteractor.trees[1];
                    currentState.addOptions(Integer.toString(0));
                    currentState.addOptions(Integer.toString(1));
                }
                currentState.setEndNode(true);
                break;
            case "ManageProperty":
                //Case manage property selected
                //provide options on the properties available
                ArrayList<Property> currentPlayerProperties = this.currentPlayer.getProperties();
                for(int i = 0; i < currentPlayerProperties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "SelectProperty":
                //Case property selected (adds the property index)
                selectedOptions.put(gameLogicInteractor.currentTree.getName(), input);
                //the player chooses what to do to the property
                //0 is for the mortgage option
                //1 is for the un mortgaged option
                //2 is for the build house option
                currentState.addOptions(Integer.toString(0));
                currentState.addOptions(Integer.toString(1));
                currentState.addOptions(Integer.toString(2));
                break;
            case "Mortgage":
                //TODO: finish case 8
                //Case player selected what to do with the property
                //the player chooses to mortgage the property
                Property targetProperty = this.board.getProperties()[selectedOptions.get(0)];
                this.currentPlayer.mortgage(targetProperty);
                currentState.setEndNode(true);
                break;
            case "BuildProperty":
                //the player chooses to build houses
                targetProperty = this.board.getProperties()[selectedOptions.get(0)];
                this.currentPlayer.buildHouse(targetProperty,1);
                currentState.setEndNode(true);
            case "Roll":
                //Case roll selected
                //We can determine if a player lands on a property by checking if the position of
                //the player is on one with a property on it (not 0,2,7,10,17,20,22,30,33,36,38).
                Cell landedOnCell = board.getCell(this.currentPlayer.getPosition());
                if(landedOnCell instanceof Property &&
                        ((Property) landedOnCell).getOwner() == null) {
                    currentState.setOnProperty(true);
                }
                else {
                    landedOnCell.performAction(currentPlayer, board);
                }
                break;
            case "Buy":
                //Case buy selected
                //player buys the property that the player lands on
                targetProperty = board.getProperties()[this.currentPlayer.getPosition()];
                if(this.currentPlayer.getMoney() >= targetProperty.getPrice()){
                    //indicates that the player can afford it and sets the property owner as the current player and
                    //deducts the player's money.
                    this.currentPlayer.pay(targetProperty.getPrice());
                    this.currentPlayer.getProperties().add(targetProperty);
                    targetProperty.setOwner(this.currentPlayer);
                    //1 indicates the property was bought successfully
                    currentState.addOptions("1");
                }
                else{
                    //0 indicates it failed cause the player has insufficient funds
                    currentState.addOptions("0");
                }
                currentState.setEndNode(true);
                break;
            case "Auction":
                //Case auction selected
                //TODO: process the auction
                break;
            case "Steal":
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
                currentState.setEndNode(true);
                break;
            case "EndTurn":
                /*TODO: Change the turn, do not let player end turn if
                negative money
                * */

                break;
            case "SettingsMenu":
                //TODO: show the settings menu
                break;
            case "ExitGame":
                currentState.setExitToMenu(true);
                break;
            case "SaveGame":
                currentState.setSaveGame(true);
                break;
            case "Bankruptcy":
                //TODO: remove player from game and all of their assets
                currentPlayerProperties = this.currentPlayer.getProperties();
                for (Property targetedProperty : currentPlayerProperties) {
                    targetedProperty.setOwner(null);
                    targetedProperty.setHouses(0);
                    targetedProperty.setMortgageStatus(false);
                }
                this.board.removePlayer(this.currentPlayer);
                break;
        }

        return currentState;
    }
}

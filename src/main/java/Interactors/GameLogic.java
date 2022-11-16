package Interactors;

import Entities.*;
import org.hamcrest.core.IsInstanceOf;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {

    Player currentPlayer;
    Board board;
    GameLogicTree[] trees = new GameLogicTree[3];
    GameLogicTree currentTree;
    ArrayList<Integer> selectedOptions = new ArrayList<Integer>();

    private int[] globalStates = new int[5];
    Player[] players;

    //add a method to move the player to a specific cell

    public GameLogic(Player currentPlayer, Board board){
        players = board.getPlayers();
        this.currentPlayer = currentPlayer;

        this.board = board;
        createTrees();
    }
    public void createTrees(){
        //Creating the game loop tree
        GameLogicTree main = new GameLogicTree("MainTree");
        GameLogicTree trade = new GameLogicTree("Trade");
        GameLogicTree pickPlayer = new GameLogicTree("PickPlayer");
        GameLogicTree pickItemOp = new GameLogicTree("PickItemOp");
        GameLogicTree pickItemSelf = new GameLogicTree("PickItemSelf");
        GameLogicTree sendTrade = new GameLogicTree("SendTrade");

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);

        GameLogicTree manageProperty = new GameLogicTree("ManageProperty");
        GameLogicTree selectProperty = new GameLogicTree("SelectProperty");
        GameLogicTree mortgage = new GameLogicTree("Mortgage");
        GameLogicTree buildProperty = new GameLogicTree("BuildProperty");

        selectProperty.addChild(mortgage);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);

        GameLogicTree roll = new GameLogicTree("Roll");
        GameLogicTree buy = new GameLogicTree("Buy");
        GameLogicTree auction = new GameLogicTree("Auction");

        roll.addChild(buy);
        roll.addChild(auction);

        GameLogicTree steal = new GameLogicTree("Steal");
        GameLogicTree choosePlayer = new GameLogicTree("ChoosePlayer");

        steal.addChild(choosePlayer);

        GameLogicTree endTurn = new GameLogicTree("EndTurn");

        GameLogicTree settingsMenu = new GameLogicTree("SettingsMenu");
        GameLogicTree exitGame = new GameLogicTree("ExitGame");
        GameLogicTree saveGame = new GameLogicTree("SaveGame");

        settingsMenu.addChild(exitGame);
        settingsMenu.addChild(saveGame);
        settingsMenu.setIsSwitchBlock(true);

        main.addChild(trade);
        main.addChild(manageProperty);
        main.addChild(roll);
        main.addChild(steal);
        main.addChild(endTurn);
        main.addChild(settingsMenu);

        main.setIsSwitchBlock(true);
        selectProperty.setIsSwitchBlock(true);
        roll.setIsSwitchBlock(true);
        trees[0] = main;

        //Creating the trading tree
        GameLogicTree tradeTree = new GameLogicTree("TradeTree");
        GameLogicTree acceptTrade = new GameLogicTree("AcceptTrade");
        GameLogicTree declineTrade = new GameLogicTree("DeclineTrade");
        trade.addChild(acceptTrade);
        tradeTree.addChild(declineTrade);
        tradeTree.setIsSwitchBlock(true);
        trees[1] = tradeTree;

        //Creating the auction tree
        GameLogicTree auctionTree = new GameLogicTree("AuctionTree");
        GameLogicTree lowOption = new GameLogicTree("LowOption");
        GameLogicTree mediumOption = new GameLogicTree("MediumOption");
        GameLogicTree highOption = new GameLogicTree("HighOption");
        GameLogicTree fold = new GameLogicTree("Fold");

        auctionTree.setIsSwitchBlock(true);
        auctionTree.addChild(lowOption);
        auctionTree.addChild(mediumOption);
        auctionTree.addChild(highOption);
        auctionTree.addChild(fold);
        trees[2] = auctionTree;

        currentTree = main;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public State performInput(int input){
        //Moving through the tree depending on the input and the node
        if (input == -1){
            //Move backwards in tree
            currentTree = (GameLogicTree) currentTree.getParent();
        }
        else if (currentTree.isSwitchBlock()){
            //Move forward in tree to one of the branches
            currentTree = (GameLogicTree) currentTree.getChildren().get(input);
        }
        else{
            //Move forward in tree
            currentTree = (GameLogicTree) currentTree.getChildren().get(0);
        }

        return handleTree(input);
    }
    public State getInitialState(){
        State currentState = new State();
        for (MenuTree tree: currentTree.getChildren()){
            currentState.addOptions(tree.getName());
        }
        return currentState;
    }
    public State handleTree(int input){
        State currentState = new State();
        switch (currentTree.getName()){
            case "MainTree":
                return getInitialState();
            case "Trade":
                //Case trade selected
                //provide a list of all possible players considering the current player is not an option
                ArrayList<Player> playerCopy = new ArrayList<Player>(Arrays.asList(this.board.getPlayers()));
                playerCopy.remove(this.currentPlayer);
                for(int i = 0; i < playerCopy.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickPlayer":
                //Case player picked
                selectedOptions.add(input);
                //provide item options from the inventory of the selected player
                Player selectedPlayer = board.getPlayers()[input];
                ArrayList<Property> playerProperties = selectedPlayer.properties;
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < playerProperties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickItemOp":
                //Case picking the item of the opponent
                selectedOptions.add(input);
                //provide item options from the current player's inventory
                ArrayList<Property> currentPlayerInventory = this.currentPlayer.properties;
                //using "i" starting from 0 to number of properties the player has - 1
                for(int i = 0; i < this.currentPlayer.properties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "PickItemSelf":
                //Case picking the item of the player
                //send the trade offer using selectedOptions. Index 0 will be the selected item from opponent and
                //index 1 will be the selected item from the current player.
                break;
            case "SendTrade":
                //Case sending the trade
                //the input should be 0 or 1. 0 if the trade was accepted, 1 if the trade was declined.
                if(input == 0){
                    //TODO: Process the trade.

                }
                currentState.setEndNode(true);
                break;
            case "ManageProperty":
                //Case manage property selected
                //provide options on the properties available
                ArrayList<Property> currentPlayerProperties = this.currentPlayer.properties;
                for(int i = 0; i < currentPlayerProperties.size(); i++){
                    currentState.addOptions(Integer.toString(i));
                }
                break;
            case "SelectProperty":
                //Case property selected (adds the property index)
                selectedOptions.add(input);
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
                this.currentPlayer.buildHouse(targetProperty);
                currentState.setEndNode(true);
            case "Roll":
                //Case roll selected
                //We can determine if a player lands on a property by checking if the position of
                //the player is on one with a property on it (not 0,2,7,10,17,20,22,30,33,36,38).
                Cell landedOnCell = board.getCell(this.currentPlayer.position);
                if(landedOnCell instanceof Property &&
                        ((Property) landedOnCell).getOwner() == null) {
                    currentState.setOnProperty(true);
                }
                else {
                    landedOnCell.performAction();
                }
                break;
            case "Buy":
                //Case buy selected
                //player buys the property that the player lands on
                targetProperty = board.getProperties()[this.currentPlayer.position];
                if(this.currentPlayer.money >= targetProperty.getPrice()){
                    //indicates that the player can afford it and sets the property owner as the current player and
                    //deducts the player's money.
                    this.currentPlayer.pay(targetProperty.getPrice());
                    this.currentPlayer.properties.add(targetProperty);
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
                //TODO: Change the turn
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
        }

        return currentState;
    }

    public Object[][] playersToArray(){
        Object[][] playersArray = new Object[players.length][6];
        for(int i = 0; i < players.length; i++){
            playersArray[i][0] = players[i].name;
            playersArray[i][1] = players[i].money;
            playersArray[i][2] = players[i].properties;
            playersArray[i][3] = players[i].inJail;
            playersArray[i][4] = players[i].jailCards;
            playersArray[i][5] = players[i].position;
        }
        return playersArray;
    }

    public Object[] boardToArray(){
        Object[] boardArray = new Object[4];
        boardArray[0] = board.getPlayers();
        boardArray[1] = board.getCells();
        boardArray[2] = board.getPlayerPositions();
        boardArray[3] = board.getProperties();
        return boardArray;
    }

    public void movePlayer(int cell_number){
        int total_squares = 40;
        int current_player_position = this.currentPlayer.position;
        if (cell_number - current_player_position < 0) {
            this.currentPlayer.money = this.currentPlayer.money + 200;
        }
        this.currentPlayer.position = cell_number;
        this.board.updatePlayerPosition(this.currentPlayer);
    }

}


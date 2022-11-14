package Interactors;

import Entities.Board;
import Entities.GameLogicTree;
import Entities.Player;
import Entities.State;
import UseCases.OutputTree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class GameLogic {

    Player currentPlayer;
    Board board;
    GameLogicTree currentTree;

    //add a method to move the player to a specific cell

    public GameLogic(Player currentPlayer, Board board){
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.currentTree = createTree();
    }
    public GameLogicTree createTree(){
        //ID = 0
        GameLogicTree main = new GameLogicTree("Main Tree");
        //ID = 1
        GameLogicTree trade = new GameLogicTree("trade");
        //ID = 2
        GameLogicTree pickPlayer = new GameLogicTree("pickPlayer");
        //ID = 3
        GameLogicTree pickItemOp = new GameLogicTree("pickItemOp");
        //ID = 4
        GameLogicTree pickItemSelf = new GameLogicTree("pickItemSelf");
        //ID = 5
        GameLogicTree sendTrade = new GameLogicTree("sendTrade");

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);

        //ID = 6
        GameLogicTree manageProperty = new GameLogicTree("manageProperty");
        //ID = 7
        GameLogicTree selectProperty = new GameLogicTree("selectProperty");
        //ID = 8
        GameLogicTree mortgage = new GameLogicTree("mortgage");
        //ID = 9
        GameLogicTree sell = new GameLogicTree("sell");
        //ID = 10
        GameLogicTree buildProperty = new GameLogicTree("buildProperty");

        selectProperty.addChild(mortgage);
        selectProperty.addChild(sell);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);

        //ID = 11
        GameLogicTree roll = new GameLogicTree("roll");
        //ID = 12
        GameLogicTree buy = new GameLogicTree("buy");
        //ID = 13
        GameLogicTree auction = new GameLogicTree("auction");

        roll.addChild(buy);
        roll.addChild(auction);

        //ID = 14
        GameLogicTree steal = new GameLogicTree("steal");
        //ID = 15
        GameLogicTree choosePlayer = new GameLogicTree("choosePlayer");

        steal.addChild(choosePlayer);

        main.addChild(trade);
        main.addChild(manageProperty);
        main.addChild(roll);
        main.addChild(steal);

        main.setIsSwitchBlock(true);
        selectProperty.setIsSwitchBlock(true);
        roll.setIsSwitchBlock(true);

        return main;
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
    public State handleTree(int input){
        State currentState = new State();
        switch (currentTree.getID()){
            case 1:
                //Case trade selected
                break;
            case 2:
                //Case player picked
                break;
            case 3:
                //Case item picked opponent
                break;
            case 4:
                //Case item picked player
                break;
            case 5:
                //Case trade sent
                break;
            case 6:
                //Case manage property selected
                break;
            case 7:
                //Case property selected
                break;
            case 8:
                //Case mortgage selected
                break;
            case 9:
                //Case sell selected

                break;
            case 10:
                //Case build house selected
                break;
            case 11:
                //Case roll selected

                /*
                TODO: IF THE PLAYER LANDS ON A PROPERTY,
                DO currentState.getOnProperty(true); !!!!!!
                 */
                break;
            case 12:
                //Case buy selected
                break;
            case 13:
                //Case auction selected
                break;
            case 14:
                //Case steal selected
                break;
            case 15:
                //Case player to steal from selected
                break;




        }

        return currentState;
    }

    public Object[][] playersToArray(){
        Player[] players = this.board.getPlayers();
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
        Board board = this.board;
        Object[] boardArray = new Object[4];
        boardArray[0] = board.getPlayers();
        boardArray[1] = board.getCells();
        boardArray[2] = board.getPlayerPositions();
        boardArray[3] = board.getProperties();
        return boardArray;
    }

    public void movePlayer(int steps){

    }

    public void rollDice(){}

}

// taking the instances of the player created and the board and convert it to board and player (2d)
// keep track of the roll dice


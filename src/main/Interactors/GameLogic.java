package Interactors;

import Entities.Board;
import Entities.Player;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class GameLogic {

    Player currentPlayer;
    Board board;
    TreeNode currentTree;

    //add a method to move the player to a specific cell

    public GameLogic(Player currentPlayer, Board board, TreeNode currentTree){
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.currentTree = currentTree;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public ArrayList<String> performPlayerAction(Player player){
        // this one is broken rn
        return new ArrayList<String>();
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


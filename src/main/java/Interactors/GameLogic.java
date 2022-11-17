package Interactors;

import Entities.Board;
import Entities.Player;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

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
        List<Player> players = this.board.getPlayers();
        Object[][] playersArray = new Object[players.size()][6];
        for(int i = 0; i < players.size(); i++){
            playersArray[i][0] = players.get(i).name;
            playersArray[i][1] = players.get(i).money;
            playersArray[i][2] = players.get(i).properties;
            playersArray[i][3] = players.get(i).inJail;
            playersArray[i][4] = players.get(i).jailCards;
            playersArray[i][5] = players.get(i).position;
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


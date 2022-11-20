package TreeHandlers;

import Entities.*;
import Interactors.GameLogic;

import java.util.HashMap;

/**
 *
 */
public class TreeHandler {

    GameLogic gameLogicInteractor;
    Player currentPlayer;
    Board board;
    HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    int returnPlayerIndex = -1;
    Player[] players;

    GameLogicTree returnTree;

    String description;



    public void initialize(Player currentPlayer, Board board){
        players = board.getPlayers();
        this.currentPlayer = currentPlayer;
        this.board = board;
    }
    public void setGameLogicInteractor(GameLogic interactor){
        gameLogicInteractor = interactor;
    }

    public State getInitialState(){
        State currentState = new State();
        currentState.setDescription(gameLogicInteractor.getCurrentTree().getPrompt());
        for (MenuTree tree: gameLogicInteractor.getCurrentTree().getChildren()){
            currentState.addOptions(tree.getName());
        }
        return currentState;
    }
    public void addSwitchOptions(State currentState){
        for (MenuTree tree: gameLogicInteractor.getCurrentTree().getChildren()){
            currentState.addOptions(tree.getName());
        }
    }
    public int getCurrentPlayerIndex(){
        for (int i = 0; i< players.length; i++){
            if (currentPlayer == players[i]){
                return i;
            }
        }
        throw new RuntimeException("Player not in array");
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

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public GameLogicTree getReturnTree() {
        return returnTree;
    }

    public void setReturnTree(GameLogicTree returnTree) {
        this.returnTree = returnTree;
    }

}

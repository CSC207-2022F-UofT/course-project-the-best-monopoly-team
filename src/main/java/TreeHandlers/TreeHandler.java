package TreeHandlers;

import Entities.*;
import Interactors.GameLogic;

import java.util.HashMap;
import java.util.List;

public class TreeHandler {
    GameLogic gameLogicInteractor;
    Player currentPlayer;
    Board board;
    HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    int returnPlayerIndex = -1;
    List<Player> players;

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
        for (MenuTree tree: gameLogicInteractor.currentTree.getChildren()){
            currentState.addOptions(tree.getName());
        }
        return currentState;
    }
    public int getCurrentPlayerIndex(){
        for (int i = 0; i< players.size(); i++){
            if (currentPlayer == players.get(i)){
                return i;
            }
        }
        throw new RuntimeException("Player not in array");
    }
    public Object[][] playersToArray(){
        Object[][] playersArray = new Object[players.size()][6];
        for(int i = 0; i < players.size(); i++){
            playersArray[i][0] = players.get(i).getName();
            playersArray[i][1] = players.get(i).getMoney();
            playersArray[i][2] = players.get(i).getProperties();
            players.get(i).setInJail((Boolean) playersArray[i][3]);
            playersArray[i][4] = players.get(i).getJailCards();
            playersArray[i][5] = players.get(i).getPosition();
        }
        return playersArray;
    }

    public Object[] boardToArray(){
        Object[] boardArray = new Object[4];
        boardArray[0] = board.getPlayers();
        boardArray[1] = board.getCells();
        boardArray[2] = board.getPlayerPositions();
        // boardArray[3] = board.getProperties();
        return boardArray;
    }

    public void movePlayer(int cell_number){
        int total_squares = 40;
        int current_player_position = this.currentPlayer.getPosition();
        if (cell_number - current_player_position < 0) {
            this.currentPlayer.setMoney(this.currentPlayer.getMoney() + 200);
        }
        this.currentPlayer.setPosition(cell_number);
        this.board.updatePlayerPosition(this.currentPlayer);
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

}

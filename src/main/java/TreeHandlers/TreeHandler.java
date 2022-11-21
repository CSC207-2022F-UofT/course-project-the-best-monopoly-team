package TreeHandlers;

import Entities.*;
import Interactors.GameLogic;

import java.util.HashMap;
import java.util.List;

/** This class is the parent class of the three tree handlers used during the game phase of the application.
 *  <br> Each of the subclasses coordinate the game logic of the application.
 */
public class TreeHandler {
    final int LOW_OPTION = 20;
    final int MEDIUM_OPTION = 80;
    final int HIGH_OPTION = 160;
    //Static variables used by all the subclasses
    static GameLogic gameLogicInteractor;
    static Player currentPlayer;
    static Board board;
    static HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    static int returnPlayerIndex = -1;
    static List<Player> players;
    static GameLogicTree returnTree;
    static String descriptionOtherTrees;
    static Property biddingProperty;


    /**
     * This method initializes variables used by the tree handlers
     * @param currentPlayer1 - The current player of the game
     * @param board1 - the current board being used in the game
     */
    public void initialize(Player currentPlayer1, Board board1){
        players = board1.getPlayers();
        currentPlayer = currentPlayer1;
        board = board1;
    }

    /**
     * This method gives the TreeHandlers a reference to the GameLogicInteractor
     * @param interactor - the gameLogicInteractor
     */
    public void setGameLogicInteractor(GameLogic interactor){
        gameLogicInteractor = interactor;
    }

    /**
     * This method gets the current state object of the program.
     * @return the current state of the program.
     */
    public State getInitialState(){
        State currentState = new State();
        if (gameLogicInteractor.getCurrentTreeID() == 0) {
            currentState.setDescription(currentPlayer.getName() + " " + gameLogicInteractor.getCurrentTree().getPrompt() + " You currently" +
                    "have " + currentPlayer.getMoney() + " dollars");
            addSwitchOptions(currentState);
        }
        else {
            currentState.setDescription(currentPlayer.getName() + " " + descriptionOtherTrees);
            currentState.addOptions(""+LOW_OPTION);
            currentState.addOptions(""+MEDIUM_OPTION);
            currentState.addOptions(""+HIGH_OPTION);
            currentState.addOptions("Fold");
        }
        gameLogicInteractor.getCurrentTree().setPreviousState(currentState);
        return currentState;
    }

    /**
     * This method mutates the current state object by adding all the subtrees of the current tree as options.
     * @param currentState - the state object to be mutated
     */
    public void addSwitchOptions(State currentState){
        for (MenuTree tree: gameLogicInteractor.getCurrentTree().getChildren()){
            currentState.addOptions(tree.getName());
        }
    }

    /**
     * This method gets the index of the current player in the players arraylist.
     * @return the integer index of the current player.
     */
    public int getCurrentPlayerIndex(){
        for (int i = 0; i< players.size(); i++){
            if (currentPlayer == players.get(i)){
                return i;
            }
        }
        throw new RuntimeException("Player not in array");
    }

    /**
     * This method generates an array which holds all the instance attributes of the players in the game.
     * @return an object array holding all the items.
     */
    public Object[][] playersToArray(){
        Object[][] playersArray = new Object[players.size()][6];
        for(int i = 0; i < players.size(); i++){
            playersArray[i][0] = players.get(i).getName();
            playersArray[i][1] = players.get(i).getMoney();
            playersArray[i][2] = players.get(i).getProperties();
            playersArray[i][3] = players.get(i).isInJail();
            playersArray[i][4] = players.get(i).getJailCards();
            playersArray[i][5] = players.get(i).getPosition();
        }
        return playersArray;
    }

    /**
     * This array generates an array which holds all the instance attributes of the board in the game.
     * @return an object array holding all the items.
     */
    public Object[] boardToArray(){
        Object[] boardArray = new Object[4];
        boardArray[0] = board.getPlayers();
        boardArray[1] = board.getCells();
        boardArray[2] = board.getPlayerPositions();
        return boardArray;
    }

    /**
     * This array moves the current player along the board.
     * @param cell_number how many spaces to move the player by
     */
    public void movePlayer(int cell_number){
        int total_squares = 40;
        int current_player_position = currentPlayer.getPosition();
        if (cell_number - current_player_position < 0) {
            currentPlayer.setMoney(currentPlayer.getMoney() + 200);
        }
        currentPlayer.setPosition(cell_number);
        board.updatePlayerPosition(currentPlayer);
    }

    /**
     * Setter method for the current player instance attribute
     * @param player - the player to set
     */
    public void setCurrentPlayer(Player player){currentPlayer = player;
    }

    /**
     * getter method for the current player instance attribute
     * @return the current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * getter method for the return tree instance attribute
     * @return the return tree
     */
    public GameLogicTree getReturnTree() {
        return returnTree;
    }

    /**
     * setter method for the return tree instance attribute
     * @param returnTree1 - the tree to set
     */
    public void setReturnTree(GameLogicTree returnTree1) {
        returnTree = returnTree1;
    }
    public void changePlayers(){
        currentPlayer = players.get((getCurrentPlayerIndex() + 1) % players.size());
    }

}

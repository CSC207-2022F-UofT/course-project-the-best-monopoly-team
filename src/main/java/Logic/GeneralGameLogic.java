package Logic;

import Entities.*;
import Interactors.GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** This class is the parent class of the three tree handlers used during the game phase of the application.
 *  <br> Each of the subclasses coordinate the game logic of the application.
 */
public class GeneralGameLogic {

    //Static variables used by all the subclasses
    static GameLogic gameLogicInteractor;
    static Player currentPlayer;
    static Board board;
    static HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    static int returnPlayerIndex = -1;
    static List<Player> players;
    static GameLogicTree returnTree;
    static String answer;
    static GameLogicTree confirmationReturn;

    private String name;
    public GeneralGameLogic(String name){
        this.name = name;
    }


    public void setAnswer(String answer1){
        answer = answer1;
    }
    public String getAnswer(){
        return answer;
    }
    public void setConfirmationReturn(GameLogicTree tree){
        confirmationReturn = tree;
    }
    public GameLogicTree getConfirmationReturn(){
        return confirmationReturn;
    }
    public List<Player> getPlayers(){
        return players;
    }
    public void setReturnPlayerIndex(int index){
        returnPlayerIndex = index;
    }
    public int getReturnPlayerIndex(){
        return returnPlayerIndex;
    }
    public static Board getBoard(){
        return board;
    }
    public GameLogic getGameLogicInteractor(){
        return gameLogicInteractor;
    }
    public HashMap<String, Integer> getSelectedOptions(){
        return selectedOptions;
    }
    /**
     * Constructor for the class
     */
    public GeneralGameLogic(){
    }

    /**
     * This method initializes variables used by the tree handlers
     * @param currentPlayer1 - The current player of the game
     * @param board1 - the current board being used in the game
     */
    public static void initialize(Player currentPlayer1, Board board1, GameLogic interactor){
        players = board1.getPlayers();
        currentPlayer = currentPlayer1;
        board = board1;
        gameLogicInteractor = interactor;
    }

    /**
     * This method gives the TreeHandlers a reference to the GameLogicInteractor
     * @param interactor - the gameLogicInteractor
     */
    public void setGameLogicInteractor(GameLogic interactor){
        gameLogicInteractor = interactor;
    }

    /**
     * Returns the state object which contains the properties of the current tree and
     * sets the current tree to the root
     * @return state object
     */
    public State getCurrentState(){
        State currentState = gameLogicInteractor.getCurrentTree().getUseCase().create_state(0);

        //mutating the state to have memory of its state, useful for backwards transversal
        gameLogicInteractor.getCurrentTree().setPreviousState(currentState);
        return currentState;
    }

    /**
     * This method mutates the current state object by adding all the subtrees of the current tree as options.
     * @param currentState - the state object to be mutated
     */
    public void addSwitchOptions(State currentState){
        for (MenuTree tree: gameLogicInteractor.getCurrentTree().getChildren()){

            currentState.addOptions(((GeneralGameLogic)((GameLogicTree)tree).getUseCase()).getName());
        }
    }


    /**
     * This method gets the index of the current player in the players arraylist.
     * @return the integer index of the current player.
     */
    public static int getCurrentPlayerIndex(){
        for (int i = 0; i< players.size(); i++){
            if (currentPlayer == players.get(i)){
                return i;
            }
        }
        throw new RuntimeException("Player not in array");
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

    /**
     * Method to change players when their turn is over
     */
    public void changePlayers(){
        currentPlayer = players.get((getCurrentPlayerIndex() + 1) % players.size());
    }
    /**
     * Sets the tree back to its top position and returns the current state of the tree
     * @return state object
     */

    /**
     * Method returning the current state after the bottom node has been reached.
     * @return a State object representing the current state after the bottom node has been reached.
     */
    public State afterBottomNode(){
        gameLogicInteractor.setCurrentTreeToMaxParent();
        return getCurrentState();
    }

    /**
     * This method adds players other than the current player to the list of options in a State object.
     * @param currentState a State object that we want to add the players as options to.
     */
    public void addPlayersState(State currentState){
        ArrayList<Player> playerCopy = new ArrayList<Player>(board.getPlayers());
        playerCopy.remove(currentPlayer);
        for (Player player : playerCopy) {
            currentState.addOptions(player.getName());
        }

    }

    /**
     * This method returns a String representing the name of the node.
     * @return a String representing the name of the node.
     */
    public String getName() {
        return name;
    }

}

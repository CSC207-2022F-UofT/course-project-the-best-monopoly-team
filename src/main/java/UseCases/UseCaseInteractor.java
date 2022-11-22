
package UseCases;

import Entities.Board;
import Entities.GameLogicTree;
import Entities.MenuTree;
import Entities.State;
import Interactors.DataAccess;
import Interactors.GameCreation;
import Interactors.GameLogic;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class creates a UseCaseInteractor which coordinates the inputs to the entities layer of the program.
 */
public class UseCaseInteractor{

    private GameLogicTree currentTree;
    private boolean menuTreeActive = true;
    private InitialTreeHandler treeHandler;
    private GameLogic logicInteractor;
    private DataAccess dataAccess;
    private GameCreation gameCreation;
    private State currentState;

    /**
     * Constructor for the UseCaseInteractor.
     */
    public UseCaseInteractor(DataAccess dataAccess){
        this.dataAccess = dataAccess;
        createTrees();
        currentState = getInitialState();
        treeHandler = new InitialTreeHandler(this);
        this.gameCreation = new GameCreation();
    }

    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(int input){
        //If the initial menu is open, transverse through this tree
        if (menuTreeActive){
            //Moving through the tree depending on the input and the node
            if (input == -1){
                //Move backwards in tree
                currentTree = (GameLogicTree) currentTree.getParent();
                return currentTree.getPreviousState();
            }
            else if (currentTree.isSwitchBlock()){
                //Move forward in tree to one of the branches
                currentTree = (GameLogicTree) currentTree.getChildren().get(input);
            }
            else{
                //Move forward in tree
                currentTree = (GameLogicTree) currentTree.getChildren().get(0);
            }
            //performs logic when inside nodes
            currentState = treeHandler.handleTree(input);
        }
        else{
            //forwards input to GameLogic once the game has started
            currentState = handleOtherTrees(input);
        }
        //returns to the menu screen
        if (currentState.isExitToMenu()){
            return returnToStart();
        }
        return currentState;

    }

    /**
     * This method allows the user to exit from the game and return to the initial menu.
     * @return a State object which gives the current state of the program
     */
    public State returnToStart(){
        menuTreeActive = true;
        currentTree = (GameLogicTree) currentTree.getMaxParent();
        return getInitialState();
    }
    /**
     * This method creates the initial menu tree for the program <br>
     * The tree is for the initialization part of the program, allowing the user
     * to choose game modes, number of players, etc.
     */
    public void createTrees(){
        //creating first tree
        GameLogicTree initialMenu = new GameLogicTree("InitialMenu", "Welcome to Monopoly++, Would you like to: ");
        GameLogicTree newGame = new GameLogicTree("NewGame");
        GameLogicTree chooseGameMode = new GameLogicTree("ChooseGameMode");
        GameLogicTree numPlayers = new GameLogicTree("NumberOfPlayers");
        GameLogicTree gameLength = new GameLogicTree("GameLength");
        GameLogicTree createNewGame = new GameLogicTree("CreateNewGame");
        GameLogicTree loadGame = new GameLogicTree("LoadGame");
        GameLogicTree chooseSave = new GameLogicTree("ChooseSave");
        GameLogicTree createGame = new GameLogicTree("CreateLoadedGame");

        //creating the tree structure
        gameLength.addChild(createNewGame);
        numPlayers.addChild(gameLength);

        chooseGameMode.addChild(numPlayers);
        newGame.addChild(chooseGameMode);

        chooseSave.addChild(createGame);
        loadGame.addChild(chooseSave);

        initialMenu.addChild(newGame);
        initialMenu.addChild(loadGame);

        //indicates that this tree switches with input
        initialMenu.setIsSwitchBlock(true);

        //adding the tree into an array for later retrieval
        currentTree = initialMenu;

    }

    /**
     * Returns the state object which contains the properties of the root of the current tree and
     * sets the current tree to the root
     * @return state object
     */
    public State getInitialState(){
        State currentState = new State();

        //makes the currentTree the root
        currentTree = (GameLogicTree) currentTree.getMaxParent();

        //mutates the state object with all of its properties
        currentState.setId(currentTree.getName());
        currentState.setDescription(currentTree.getPrompt());
        for (MenuTree tree: currentTree.getChildren()){
            currentState.addOptions(tree.getName());
        }
        currentTree.setPreviousState(currentState);
        return currentState;
    }


    /**
     * This method handles the input of the user in the game part of the program.
     * <p>
     */
    public State handleOtherTrees(int input){
        //deciding what to do based on node visited
        return logicInteractor.performInput(input);

    }

    /**
     * This method allows the user to create a game, new or loaded, by loading the files
     * that deal with the game
     * @param newGame - whether we are creating a new game or old game
     */
    public void createGame(boolean newGame){
        Board loadedBoard;
        if (newGame){
            loadedBoard = loadFiles();
            if (loadedBoard == null){
                //TODO: do something when it fails to load
            }
            else {
                logicInteractor = new GameLogic(loadedBoard.getPlayers().get(0), loadedBoard);
                menuTreeActive = false;
            }
        }
        else {
            //TODO: DO SOMETHING IF ITS NOT A NEW GAME

        }

    }

    /**
     * This method loads the files used in the program and creates an object which contains all the necessary game
     * files to begin a game
     * @return the object which will supply the data to create the game
     */
    public Board loadFiles() {
        //TODO load files for game creation
        //TODO correctly implement this method
        try {
            ArrayList<String[]> newProperties = this.dataAccess.loadProperties();
            ArrayList<String> playerNames = new ArrayList<>();
            for (int i = 0; i < treeHandler.selectedOptions.get("NumberOfPlayers") + 2; i++) {
                playerNames.add("Player " + i);
            }
            Board newGame = this.gameCreation.createNewGame(playerNames, newProperties);
            return newGame;
        }
        catch (Exception IOException){
            return null;
        }
    }
    /**
     * This method loads the files used in the program and the save file and
     * an object which contains all the necessary game files to load a previous game
     * @return the object which will supply the data to create the game
     */
    public Board loadSavedGame(String filepath) throws IOException{
        ArrayList<ArrayList<String[]>> loadedGame = this.dataAccess.loadGame();
        ArrayList<String[]> newProperties = this.dataAccess.loadProperties();

        Board savedGame = this.gameCreation.createSavedGame(loadedGame, newProperties);
        return savedGame;
    }

    /**
     * Returns the current tree used in this program
     * @return the current tree
     */
    public GameLogicTree getCurrentTree(){
        return currentTree;
    }

    /**
     * Returns the GameLogic instance used in this object
     * @return the GameLogic instance
     */
    public GameLogic getLogicInteractor() {
        return logicInteractor;
    }

    /**
     * Sets the current tree of the class
     * @param currentTree - the current tree to set
     */
    public void setCurrentTree(GameLogicTree currentTree) {
        this.currentTree = currentTree;
    }

}

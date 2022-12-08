
package UseCases;

import Entities.Board;
import Entities.GameLogicTree;
import Entities.State;
import Interactors.SavePackager;
import Persistence.LoadAccess;
import Interactors.GameCreation;
import Interactors.GameLogic;
import Persistence.SaveAccess;

import TreeHandlers.MainTreeNodeLogic.MainTreeNodeLogic;
import UseCases.InitialNodeLogic.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class creates a UseCaseInteractor which coordinates the inputs to the entities layer of the program.
 */
public class UseCaseInteractor{

    private GameLogicTree currentTree;
    private boolean menuTreeActive = true;
    private GameLogic logicInteractor;
    private LoadAccess loadAccess;
    private SaveAccess saveAccess;
    private GameCreation gameCreation;
    private SavePackager savePackager;
    private State currentState;

    /**
     * Constructor for the UseCaseInteractor.
     */
    public UseCaseInteractor(LoadAccess loadAccess, SaveAccess saveAccess){
        this.saveAccess = saveAccess;
        this.loadAccess = loadAccess;
        createTrees();
        this.gameCreation = new GameCreation();
        this.savePackager = new SavePackager();
        InitialLogic.setCaseInteractor(this);
    }

    /**
     * This method returns the state that the game is in
     * @return the state that the game is in
     */
    public State getCurrentState() {
        return this.currentState;
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
            currentState = currentTree.getUseCase().create_state(input);
        }
        else{
            //forwards input to GameLogic once the game has started
            currentState = handleOtherTrees(input);
        }
        //returns to the menu screen
        if (currentState.isExitToMenu()){
            return returnToStart();
        }
        else if(currentState.isSaveGame()){
            saveGame();
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
        GameLogicTree initialMenu = new GameLogicTree(new InitialParentNode());
        GameLogicTree newGame = new GameLogicTree(new NewGame());
        GameLogicTree chooseGameMode = new GameLogicTree(new ChooseGameMode());
        GameLogicTree numPlayers = new GameLogicTree( new NumberOfPlayers());
        GameLogicTree gameLength = new GameLogicTree(new GameLength());
        GameLogicTree createNewGame = new GameLogicTree(new CreateNewGame());
        GameLogicTree loadGame = new GameLogicTree(new LoadGame());
        GameLogicTree chooseSave = new GameLogicTree(new ChooseSave());
        GameLogicTree createLoadedGame = new GameLogicTree(new CreateLoadedGame());

        //creating the tree structure
        gameLength.addChild(createNewGame);
        numPlayers.addChild(gameLength);

        chooseGameMode.addChild(numPlayers);
        newGame.addChild(chooseGameMode);

        chooseSave.addChild(createLoadedGame);
        loadGame.addChild(chooseSave);

        initialMenu.addChild(newGame);
        initialMenu.addChild(loadGame);

        //indicates that this tree switches with input
        initialMenu.setIsSwitchBlock(true);

        //adding the tree into an array for later retrieval
        currentTree = initialMenu;

    }
    public void saveGame(){
        try{
            saveAccess.saveGameNewFile(savePackager.getPlayerPropertyData(), savePackager.getStates());
            currentState.setDescription("Successful save");
        }
        catch (Exception IOException){
            currentState.setDescription("Save failed");
        }
    }

    /**
     * Returns the state object which contains the properties of the root of the current tree and
     * sets the current tree to the root
     * @return state object
     */
    public State getInitialState(){
        //State currentState = new State();

        //makes the currentTree the root
        currentTree = (GameLogicTree) currentTree.getMaxParent();
        return currentTree.getUseCase().create_state(0);
    }


    /**
     * This method handles the input of the user in the game part of the program.
     * <p>
     */
    public State handleOtherTrees(int input){
        //deciding what to do based on node visited
        return logicInteractor.performInput(input);

    }

    public void createNewGame(int[] states){
        Board loadedBoard;
        loadedBoard = loadFiles(states[0], states[2]);
        createGame(loadedBoard, states);
    }

    /**
     * This method allows the user to create a game, new or loaded, by loading the files
     * that deal with the game
     * @param board is the Board instance containing the save file information
     * @param states is an int[] containing save file information
     */
    public void createGame(Board board, int[] states){
        logicInteractor = new GameLogic(board, states);
        menuTreeActive = false;
    }

    /**
     * This method loads the files used in the program and creates an object which contains all the necessary game
     * files to begin a game
     * @return the object which will supply the data to create the game
     */
    public Board loadFiles(int numberOfPlayers, int mode) {
        try {
            ArrayList<String[]> newProperties = this.loadAccess.loadProperties();
            ArrayList<String> playerNames = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++) {
                playerNames.add("Player " + i);
            }
            Board newBoard = this.gameCreation.createNewBoard(playerNames, newProperties);
            gameCreation.initializeMode(newBoard, mode);
            return newBoard;
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
    public Board loadSavedBoard(String filepath) throws IOException{
        loadAccess.setFile(filepath);
        ArrayList<ArrayList<String[]>> loadedGame = this.loadAccess.loadGame();
        ArrayList<String[]> newProperties = this.loadAccess.loadProperties();

        return this.gameCreation.createSavedBoard(loadedGame, newProperties);
    }

    /**
     * This method loads the save file and returns the initial states required for the
     * Tree when creating a game
     * @return an Integer[] of initial states
     * @throws FileNotFoundException
     */
    public int[] loadInitialStates() throws FileNotFoundException {
        ArrayList<ArrayList<String[]>> loadedGame = this.loadAccess.loadGame();
        return this.gameCreation.getInitialStates(loadedGame);
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



}

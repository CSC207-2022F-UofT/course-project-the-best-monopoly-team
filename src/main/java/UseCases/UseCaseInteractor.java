
package UseCases;

import Entities.GameLogicTree;
import Entities.MenuTree;
import Entities.State;
import Interactors.GameLogic;
public class UseCaseInteractor{
    private GameLogicTree currentTree;
    private boolean menuTreeActive = true;

    //This array contains various states for the program which will be used for various calculations
    private int[] globalStates = new int[5];
    private GameLogic logicInteractor;
    private State currentState;
    public UseCaseInteractor(){
        createTrees();
        currentState = getInitialState();
        updateOutput(currentState);
//        currentState = logicInteractor.getInitialState();
//        updateOutput(currentState);
    }

    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public State handleInput(int input){
        if (menuTreeActive){
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
            currentState = handleInitialTree(input);
        }
        else{
            currentState = handleOtherTrees(input);
        }
        return currentState;
    }

    /**
     * This method creates the initial menu tree for the program <br>
     * The tree is for the initialization part of the program, allowing the user
     * to choose game modes, number of players, etc.
     */
    public void createTrees(){
        //creating first tree
        GameLogicTree initialMenu = new GameLogicTree("InitialMenu");
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
    public State getInitialState(){
        State currentState = new State();
        for (MenuTree tree: currentTree.getChildren()){
            currentState.addOptions(tree.getName());
        }
        return currentState;
    }

    /**
     * This method handles the input of the user in the initialization part of the program.
     * <p>
     * Usage of globalStates array:<br>
     * ------------------------------------------------------------------------------------- <br>
     * globalStates[0]: If the user is loading a game or starting a new game. Can be 1 or 0.
     * <p>
     * If the user decides to create a new game: <br>
     * globalStates[1]: An integer indicating what game mode the user wishes to load.<br>
     * globalStates[2]: An integer indicating the number of players in the game.<br>
     * globalStates[3]: An integer indicating the choice for the length of the game.<br>
     * <p>
     * If the user decides to load a past game:<br>
     * globalStates[4]: An integer indicating the choice of saved games to load<br>
     * @param input the translated input of the user from the input interface
     */
    public State handleInitialTree(int input){
        //deciding what to do based on node visited
        State state = new State();
        switch (currentTree.getName()){
            case "NewGame":
                //in "New Game" node
                globalStates[0] = 1;
                break;
            case "ChooseGameMode":
                //in "Choose Game mode" node
                globalStates[1] = input;
                break;
            case "NumberOfPlayers":
                //in "Number of Players" node
                globalStates[2] = input;
                break;
            case "GameLength":
                //in "Game Length" node
                globalStates[3] = input;
                break;
            case "CreateNewGame":
                //in "Create new Game" node
                //TODO: CREATE THE GAME
            case "LoadGame":
                //in "Load Game" node
                globalStates[0] = 0;
                break;
            case "ChooseSave":
                //in "Choose Save" node
                globalStates[4] = input;
                break;
            case "CreateLoadedGame":
                //in "Create Loaded Game" node
                //TODO: CREATE THE GAME
                break;
        }
        return state;
    }
    /**
     * This method handles the input of the user in the game part of the program.
     * <p>
     */
    public State handleOtherTrees(int input){
        //deciding what to do based on node visited
        return logicInteractor.performInput(input);

    }
    public void updateOutput(State currentState){
        //TODO update the user interface
    }
    public void loadFiles(String filepath){
        //TODO load files for game creation
    }





}


package UseCases;

import Entities.GameLogicTree;
import Entities.State;
import Interactors.GameLogic;
public class UseCaseInteractor{
    private GameLogicTree currentTree;
    private boolean menuTreeActive = true;

    //This array contains various states for the program which will be used for various calculations
    private int[] globalStates = new int[5];
    private GameLogic logicInteractor;
    public UseCaseInteractor(GameLogic logicInteractor){
        createTrees();
        this.logicInteractor = logicInteractor;
    }

    /**
     * This method handles the input of the user. <br>It moves through the current tree with the user's input,
     * and uses helper methods to deal with the logic afterwards.
     * @param input the translated input of the user from the input interface
     */
    public void handleInput(int input){
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

        //Handling different tree input
        if (menuTreeActive){
            handleInitialTree(input);
        }
        else{
            handleOtherTrees(input);
        }

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
//
//        //creating second tree (the game logic tree)
//        GameLogicTree gameOutputMenu = new GameLogicTree("GameOutputMenu");
//        GameLogicTree trade = new GameLogicTree("Trade");
//        GameLogicTree pickPlayer = new GameLogicTree("PickPlayer");
//        GameLogicTree chooseItemOpponent = new GameLogicTree("ChooseItemFromOpponent");
//        GameLogicTree chooseItemFromSelf = new GameLogicTree("ChooseItemFromSelf");
//        GameLogicTree sendTrade = new GameLogicTree("SendTradeOffer");
//
//        GameLogicTree manageProperty = new GameLogicTree("ManageProperty");
//        GameLogicTree selectProperty = new GameLogicTree("SelectProperty");
//        GameLogicTree mortgage = new GameLogicTree("MortgageProperty");
//        GameLogicTree sell = new GameLogicTree("SellProperty");
//        GameLogicTree buildHouses = new GameLogicTree("BuildHouses");
//
//        GameLogicTree roll = new GameLogicTree("Roll");
//        GameLogicTree buy = new GameLogicTree("Buy");
//        GameLogicTree auction = new GameLogicTree("Auction");
//
//        GameLogicTree steal = new GameLogicTree("Steal");
//        GameLogicTree choosePlayer = new GameLogicTree("ChoosePlayer");
//
//        GameLogicTree settings = new GameLogicTree("Settings");
//        GameLogicTree exitToMenu = new GameLogicTree("ExitToMenu");
//        GameLogicTree saveGame = new GameLogicTree("SaveGame");
//        GameLogicTree selectSaveFile = new GameLogicTree("SelectSaveFile");
//
//        //creating the tree structure
//        chooseItemFromSelf.addChild(sendTrade);
//        chooseItemOpponent.addChild(chooseItemFromSelf);
//        pickPlayer.addChild(chooseItemOpponent);
//        trade.addChild(pickPlayer);
//
//        selectProperty.addChild(mortgage);
//        selectProperty.addChild(sell);
//        selectProperty.addChild(buildHouses);
//        manageProperty.addChild(selectProperty);
//
//        roll.addChild(buy);
//        roll.addChild(auction);
//
//        steal.addChild(choosePlayer);
//
//        saveGame.addChild(selectSaveFile);
//        settings.addChild(exitToMenu);
//        settings.addChild(saveGame);
//
//        gameOutputMenu.addChild(trade);
//        gameOutputMenu.addChild(manageProperty);
//        gameOutputMenu.addChild(roll);
//        gameOutputMenu.addChild(steal);
//        gameOutputMenu.addChild(settings);
//
//        //indicates that these trees switch with input
//        selectProperty.setIsSwitchBlock(true);
//        gameOutputMenu.setIsSwitchBlock(true);
//        settings.setIsSwitchBlock(true);
//        roll.setIsSwitchBlock(true);
//
//
//        trees[1] = gameOutputMenu;

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
    public void handleInitialTree(int input){
        //deciding what to do based on node visited
        switch (currentTree.getName()){
            case "NewGame":
                //in "New Game" node
                globalStates[0] = 1;
                return;
            case "ChooseGameMode":
                //in "Choose Game mode" node
                globalStates[1] = input;
                return;
            case "NumberOfPlayers":
                //in "Number of Players" node
                globalStates[2] = input;
                return;
            case "GameLength":
                //in "Game Length" node
                globalStates[3] = input;
                return;
            case "CreateNewGame":
                //in "Create new Game" node
                //TODO: CREATE THE GAME
            case "LoadGame":
                //in "Load Game" node
                globalStates[0] = 0;
                return;
            case "ChooseSave":
                //in "Choose Save" node
                globalStates[4] = input;
                return;
            case "CreateLoadedGame":
                //in "Create Loaded Game" node
                //TODO: CREATE THE GAME
        }
    }
    /**
     * This method handles the input of the user in the game part of the program.
     * <p>
     */
    public void handleOtherTrees(int input){
        //deciding what to do based on node visited
        State currentState = logicInteractor.performInput(input);

    }
    public void updateOutput(){
        //TODO update the user interface
    }
    public void loadFiles(String filepath){
        //TODO load files for game creation
    }





}


package UseCases;

import Interactors.GameLogic;
public class UseCaseInteractor{

    private OutputTree[] trees = new OutputTree[2];
    private OutputTree currentTree;

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
            currentTree = (OutputTree) currentTree.getParent();
        }
        else if (currentTree.isSwitchBlock()){
            //Move forward in tree to one of the branches
            currentTree = (OutputTree) currentTree.getChildren().get(input);
        }
        else{
            //Move forward in tree
            currentTree = (OutputTree) currentTree.getChildren().get(0);
        }

        //Handling different tree input
        if (currentTree == trees[0]){
            handleInitialTree(input);
        }
        else{
            handleGameTree(input);
        }

    }

    /**
     * This method creates the two trees required in the program. <br>
     * The first tree is for the initialization part of
     * the program and the second tree is for the game part of the program.
     */
    public void createTrees(){
        //creating first tree
        //ID = 0
        OutputTree initialMenu = new OutputTree("InitialMenu");
        //ID = 1
        OutputTree newGame = new OutputTree("NewGame");
        //ID = 2
        OutputTree chooseGameMode = new OutputTree("ChooseGameMode");
        //ID = 3
        OutputTree numPlayers = new OutputTree("NumberOfPlayers");
        //ID = 4
        OutputTree gameLength = new OutputTree("GameLength");
        //ID = 5
        OutputTree createNewGame = new OutputTree("CreateNewGame");
        //ID = 6
        OutputTree loadGame = new OutputTree("LoadGame");
        //ID = 7
        OutputTree chooseSave = new OutputTree("ChooseSave");
        //ID = 8
        OutputTree createGame = new OutputTree("CreateLoadedGame");

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
        initialMenu.setSwitchBlock(true);


        //adding the tree into an array for later retrieval
        trees[0] = initialMenu;
        currentTree = trees[0];

        //creating second tree
        OutputTree gameOutputMenu = new OutputTree("GameOutputMenu");
        OutputTree trade = new OutputTree("Trade");
        OutputTree pickPlayer = new OutputTree("PickPlayer");
        OutputTree chooseItemOpponent = new OutputTree("ChooseItemFromOpponent");
        OutputTree chooseItemFromSelf = new OutputTree("ChooseItemFromSelf");
        OutputTree sendTrade = new OutputTree("SendTradeOffer");

        OutputTree manageProperty = new OutputTree("ManageProperty");
        OutputTree selectProperty = new OutputTree("SelectProperty");
        OutputTree mortgage = new OutputTree("MortgageProperty");
        OutputTree sell = new OutputTree("SellProperty");
        OutputTree buildHouses = new OutputTree("BuildHouses");

        OutputTree roll = new OutputTree("Roll");
        OutputTree buy = new OutputTree("Buy");
        OutputTree auction = new OutputTree("Auction");

        OutputTree steal = new OutputTree("Steal");
        OutputTree choosePlayer = new OutputTree("ChoosePlayer");

        OutputTree settings = new OutputTree("Settings");
        OutputTree exitToMenu = new OutputTree("ExitToMenu");
        OutputTree saveGame = new OutputTree("SaveGame");
        OutputTree selectSaveFile = new OutputTree("SelectSaveFile");

        //creating the tree structure
        chooseItemFromSelf.addChild(sendTrade);
        chooseItemOpponent.addChild(chooseItemFromSelf);
        pickPlayer.addChild(chooseItemOpponent);
        trade.addChild(pickPlayer);

        selectProperty.addChild(mortgage);
        selectProperty.addChild(sell);
        selectProperty.addChild(buildHouses);
        manageProperty.addChild(selectProperty);

        roll.addChild(buy);
        roll.addChild(auction);

        steal.addChild(choosePlayer);

        saveGame.addChild(selectSaveFile);
        settings.addChild(exitToMenu);
        settings.addChild(saveGame);

        gameOutputMenu.addChild(trade);
        gameOutputMenu.addChild(manageProperty);
        gameOutputMenu.addChild(roll);
        gameOutputMenu.addChild(steal);
        gameOutputMenu.addChild(settings);

        //indicates that these trees switch with input
        selectProperty.setSwitchBlock(true);
        gameOutputMenu.setSwitchBlock(true);
        settings.setSwitchBlock(true);
        roll.setSwitchBlock(true);


        trees[1] = gameOutputMenu;

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
    public void handleGameTree(int input){
        //deciding what to do based on node visited
        switch (currentTree.getName()){
            case "ExitToMenu":
                currentTree = trees[0];
                globalStates = new int[10];
                break;
            case "SaveGame":
                //TODO: Save the game using the input

            default:
                logicInteractor.performInput(input);
        }

    }
    public void updateOutput(){
        //TODO update the user interface
    }
    public void loadFiles(String filepath){
        //TODO load files for game creation
    }





}

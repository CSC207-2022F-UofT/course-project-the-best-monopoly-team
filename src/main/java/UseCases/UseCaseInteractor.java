package UseCases;

public class UseCaseInteractor{

    private OutputTree[] trees = new OutputTree[2];
    private OutputTree currentTree;

    //This array contains various states for the program which will be used for various calculations
    private int[] globalStates = new int[5];

    public UseCaseInteractor(){
        createTrees();
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
        OutputTree initialMenu = new OutputTree("Initial Menu");
        //ID = 1
        OutputTree newGame = new OutputTree("New Game");
        //ID = 2
        OutputTree chooseGameMode = new OutputTree("Choose Game Mode");
        //ID = 3
        OutputTree numPlayers = new OutputTree("number of players");
        //ID = 4
        OutputTree gameLength = new OutputTree("Game Length");
        //ID = 5
        OutputTree createNewGame = new OutputTree("Create New Game");
        //ID = 6
        OutputTree loadGame = new OutputTree("Load Game");
        //ID = 7
        OutputTree chooseSave = new OutputTree("Choose Save");
        //ID = 8
        OutputTree createGame = new OutputTree("Create Loaded Game");

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

        //Resetting the id counter
        initialMenu.setGlobalID(0);

        //adding the tree into an array for later retrieval
        trees[0] = initialMenu;
        currentTree = trees[0];

        //creating second tree
        //ID = 0
        OutputTree gameOutputMenu = new OutputTree("Game output menu");
        //ID = 1
        OutputTree trade = new OutputTree("Trade");
        //ID = 2
        OutputTree pickPlayer = new OutputTree("Pick player");
        //ID = 3
        OutputTree chooseItemOpponent = new OutputTree("Choose item from opponent");
        //ID = 4
        OutputTree chooseItemFromSelf = new OutputTree("Choose item from self");
        //ID = 5
        OutputTree sendTrade = new OutputTree("Send trade offer");

        //ID = 6
        OutputTree manageProperty = new OutputTree("Manage Property");
        //ID = 7
        OutputTree selectProperty = new OutputTree("Select Property");
        //ID = 8
        OutputTree mortgage = new OutputTree("Mortgage property");
        //ID = 9
        OutputTree sell = new OutputTree("Sell property");
        //ID = 10
        OutputTree buildHouses = new OutputTree("BuildHouses");

        //ID = 11
        OutputTree roll = new OutputTree("Roll");

        //ID = 12
        OutputTree steal = new OutputTree("Steal");
        //ID = 13
        OutputTree choosePlayer = new OutputTree("Choose player");

        //ID = 14
        OutputTree settings = new OutputTree("Settings");
        //ID = 15
        OutputTree exitToMenu = new OutputTree("Exit to menu");
        //ID = 16
        OutputTree saveGame = new OutputTree("Save game");
        //ID = 17
        OutputTree selectSaveFile = new OutputTree("Select save file");

        //creating the tree structure
        chooseItemFromSelf.addChild(sendTrade);
        chooseItemOpponent.addChild(chooseItemFromSelf);
        pickPlayer.addChild(chooseItemOpponent);
        trade.addChild(pickPlayer);

        selectProperty.addChild(mortgage);
        selectProperty.addChild(sell);
        selectProperty.addChild(buildHouses);
        manageProperty.addChild(selectProperty);

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

        //resetting the id counter
        gameOutputMenu.setGlobalID(0);

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
        switch (currentTree.getID()){
            case 1:
                //in "New Game" node
                globalStates[0] = 1;
                return;
            case 2:
                //in "Choose Game mode" node
                globalStates[1] = input;
                return;
            case 3:
                //in "Number of Players" node
                globalStates[2] = input;
                return;
            case 4:
                //in "Game Length" node
                globalStates[3] = input;
                return;
            case 5:
                //in "Create new Game" node
                //TODO: CREATE THE GAME
            case 6:
                //in "Load Game" node
                globalStates[0] = 0;
                return;
            case 7:
                //in "Choose Save" node
                globalStates[4] = input;
                return;
            case 8:
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
        switch (currentTree.getID()){
            case 15:
                currentTree = trees[0];
                globalStates = new int[10];
                break;
            case 17:
                //TODO: Save the game using the input

            default:
                //TODO pass arguments to game logic interactor

        }
    }
    public void updateOutput(){
        //TODO update the user interface
    }
    public void loadFiles(String filepath){
        //TODO load files for game creation
    }





}

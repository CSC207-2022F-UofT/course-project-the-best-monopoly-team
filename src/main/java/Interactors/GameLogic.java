package Interactors;

import Entities.*;
import TreeHandlers.AuctionNodeLogic.*;
import TreeHandlers.MainTreeNodeLogic.*;
import TreeHandlers.TradingNodeLogic.AcceptTrade;
import TreeHandlers.TradingNodeLogic.DeclineTrade;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.TradingNodeLogic.TradingParentNode;

/**
 * This class creates a GameLogic instance which coordinates the flow of the game.
 */
public class GameLogic {
    private GameLogicTree[] trees = new GameLogicTree[3];
    private GameLogicTree currentTree;

    /**
     * This is the constructor for creating a GameLogic instance.
     * @param currentPlayer A Player instance that is intended to be the current player of the game's turn.
     * @param board A board instance that the GameLogic instance will help govern.
     */
    public GameLogic(Player currentPlayer, Board board){
        initialize(currentPlayer, board);
    }
    public void initialize(Player currentPlayer, Board board){
        AuctionTreeNodeLogic.array_init(board.getPlayers().size());
        GeneralGameLogic.initialize(currentPlayer,board, this);
        createTrees();
    }

    /**
     * This is the constructor for creating a GameLogic instance.
     * @param board A board instance that the GameLogic instance will help govern.
     * @param states array of integers containing what is needed to create the game
     */
    public GameLogic(Board board, int[] states){
        Player currentPlayer = board.getPlayers().get(states[4]);
        initialize(currentPlayer,board);
        int [] gameStates = new int[5];
        gameStates[0] = 0;
        gameStates[1] = states[5];
        gameStates[2] = states[2];
        gameStates[3] = states[3];
        gameStates[4] = states[1];
        MainTreeNodeLogic.initializeStates(gameStates);
    }

    /**
     * This method returns a State object which represents the state of the game.
     * @return A State object representing the state of the game.
     */
    public State getCurrentState(){
        GeneralGameLogic temp = new GeneralGameLogic();
        return temp.getCurrentState();
    }

    /**
     * This method returns the instance attribute called trees in the GameLogic instance.
     * @return Returns a GameLogicTree array containing the trees stored in the GameLogic instance.
     */
    public GameLogicTree[] getTrees(){
        return this.trees;
    }

    /**
     * This method sets the value of the instance attribute trees.
     * @param newTrees A GameLogicTree array containing the trees to set the value of the tree instance attribute.
     */
    public void setTrees(GameLogicTree[] newTrees){
        this.trees = newTrees;
    }

    /**
     * This method returns a tree stored by the currentTree instance attribute. The currentTree instance attribute
     * stores the tree that determines the flow of the game at the current turn.
     * @return A GameLogicTree that determines the flow of the game at the current turn.
     */
    public GameLogicTree getCurrentTree(){
        return this.currentTree;
    }

    /**
     * This method sets the value of the currentTree instance attribute.
     * @param newCurrentTree a GameLogicTree that will be set as the currentTree.
     */
    public void setCurrentTree(GameLogicTree newCurrentTree){
        this.currentTree = newCurrentTree;
    }

    /**
     * This method sets the currentTree instance attribute to the highest possible node of the currenTree.
     */
    public void setCurrentTreeToMaxParent(){
        currentTree = (GameLogicTree) currentTree.getMaxParent();
    }

    /**
     * This method creates the GameLogic trees for the different scenarios in the game.
     */
    public void createTrees(){
        MenuTree[] temp;
        //Creating the game loop tree
        GameLogicTree main = new GameLogicTree( new MainParentNode());
        GameLogicTree trade = new GameLogicTree(new Trade());
        GameLogicTree pickPlayer = new GameLogicTree(new PickPlayer());
        GameLogicTree pickItemOp = new GameLogicTree(new PickItemOp());
        GameLogicTree pickItemSelf = new GameLogicTree(new PickItemSelf());
        GameLogicTree sendTrade = new GameLogicTree(new SendTrade());

        GameLogicTree nothingToTrade = new GameLogicTree(new NothingToTrade());

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);
        pickPlayer.addChild(nothingToTrade);

        GameLogicTree manageProperty = new GameLogicTree(new ManageProperty());
        GameLogicTree selectProperty = new GameLogicTree(new SelectProperty());
        GameLogicTree mortgage = new GameLogicTree(new Mortgage());
        GameLogicTree unMortgage = new GameLogicTree(new UnMortgage());
        GameLogicTree buildProperty = new GameLogicTree(new BuildProperty());

        GameLogicTree noProperties = new GameLogicTree(new NoProperties());

        temp = new MenuTree[]{mortgage, unMortgage, buildProperty};
        addParentToMultiple(selectProperty,temp);

        temp = new MenuTree[]{selectProperty, noProperties};
        addParentToMultiple(manageProperty,temp);

        GameLogicTree roll = new GameLogicTree(new Roll());
        GameLogicTree callAction = new GameLogicTree(new CallAction());
        GameLogicTree emptyPropertySpace = new GameLogicTree(new EmptyPropertySpace());
        GameLogicTree buy = new GameLogicTree(new Buy());
        GameLogicTree auction = new GameLogicTree(new Auction());
        GameLogicTree alreadyRolled = new GameLogicTree(new AlreadyRolled());

        temp = new MenuTree[]{emptyPropertySpace, callAction, alreadyRolled};
        addParentToMultiple(roll, temp);

        temp = new MenuTree[]{buy, auction};
        addParentToMultiple(emptyPropertySpace, temp);

        GameLogicTree steal = new GameLogicTree(new Steal());
        GameLogicTree choosePlayer = new GameLogicTree(new ChoosePlayerUseCase());

        steal.addChild(choosePlayer);

        GameLogicTree endTurn = new GameLogicTree(new EndTurnUseCase());

        GameLogicTree settingsMenu = new GameLogicTree(new SettingsMenuUseCase());
        GameLogicTree exitGame = new GameLogicTree(new ExitGameUseCase());
        GameLogicTree saveGame = new GameLogicTree(new SaveGameUseCase());

        GameLogicTree bankruptcy = new GameLogicTree(new BankruptcyUseCase());

        GameLogicTree confirmationNode = new GameLogicTree(new ConfirmationUseCase());
        GameLogicTree informationNode = new GameLogicTree(new InformationUseCase());
        GameLogicTree finishGameNode = new GameLogicTree(new FinishGameUseCase());

        finishGameNode.addChild(exitGame);

        temp = new MenuTree[]{choosePlayer, callAction, buildProperty, alreadyRolled,
        saveGame, endTurn, auction, noProperties, nothingToTrade};
        addChildToMultiple(temp,informationNode);

        temp = new MenuTree[]{mortgage, exitGame, bankruptcy};
        addChildToMultiple(temp, confirmationNode);

        temp = new MenuTree[]{bankruptcy, endTurn};
        addChildToMultiple(temp, finishGameNode);

        settingsMenu.addChild(exitGame);
        settingsMenu.addChild(saveGame);
        settingsMenu.setIsSwitchBlock(true);

        temp = new MenuTree[]{trade,manageProperty,roll,steal,endTurn,settingsMenu,bankruptcy};
        addParentToMultiple(main, temp);

        main.setIsSwitchBlock(true);
        selectProperty.setIsSwitchBlock(true);
        emptyPropertySpace.setIsSwitchBlock(true);
        trees[0] = main;

        //Creating the trading tree
        GameLogicTree tradeTree = new GameLogicTree(new TradingParentNode());
        GameLogicTree acceptTrade = new GameLogicTree(new AcceptTrade());
        GameLogicTree declineTrade = new GameLogicTree(new DeclineTrade());

        temp = new MenuTree[]{acceptTrade, declineTrade};
        addParentToMultiple(tradeTree, temp);
        tradeTree.setIsSwitchBlock(true);
        trees[1] = tradeTree;

        //Creating the auction tree
        GameLogicTree auctionTree = new GameLogicTree(new AuctionParentNode());
        GameLogicTree lowOption = new GameLogicTree(new LowOption());
        GameLogicTree mediumOption = new GameLogicTree(new MediumOption());
        GameLogicTree highOption = new GameLogicTree(new HighOption());
        GameLogicTree fold = new GameLogicTree(new Fold());

        auctionTree.setIsSwitchBlock(true);

        temp = new MenuTree[]{lowOption, mediumOption, highOption, fold};
        addParentToMultiple(auctionTree, temp);
        trees[2] = auctionTree;

        currentTree = main;
    }
    public void addChildToMultiple(MenuTree[] trees, MenuTree child){
        for (MenuTree tree: trees){
            tree.addChild(child);
        }
    }
    public void addParentToMultiple(MenuTree parent, MenuTree[] trees){
        for (MenuTree tree: trees){
            parent.addChild(tree);
        }
    }


    /**
     * This method traverses through the GameLogicTrees that handle the state of the program.
     * @param input an int value that represents the direction of which the GameLogicTrees are to be traversed. -1
     *              represents traversing backwards. Otherwise, it will traverse forwards.
     * @return A state object indicating the current state of the program
     */
    public State performInput(int input){
        //Moving through the tree depending on the input and the node
        if (input == -1){
            //Move backwards in tree
            currentTree = (GameLogicTree) currentTree.getParent();
            return currentTree.getPreviousState();
        }
        else if (currentTree.isSwitchBlock()){
            //Move forward in tree to one of the branches
            transverseCurrentTree(input);
        }
        else{
            //Move forward in tree
            currentTree = (GameLogicTree) currentTree.getChildren().get(0);
        }

        return handleTree(input);
    }

    /**
     * This method sets the currentTree instance attribute to a GameLogicTree which is a child of the currentTree
     * selected through the parameter.
     * @param branchNumber an int value that identifies the GameLogicTree that is to be set as currentTree.
     */
    public void transverseCurrentTree(int branchNumber){
        currentTree =  (GameLogicTree) currentTree.getChildren().get(branchNumber);
    }

    /**
     * This method handles the GameLogicTree that corresponds to the current state of the game and returns a State
     * instance containing relevant information related to the action performed.
     * @param input an int value corresponding to a specific action for mainTreeHandler to handle.
     * @return a State instance containing relevant information related to the action handled.
     */
    public State handleTree(int input){

            State returnState =  currentTree.getUseCase().create_state(input);
            currentTree.setPreviousState(returnState);
            return returnState;

    }
    /**
     * This method returns the currentTree instance attribute's ID.
     * @return an int value representing the currentTree's ID. It will return -1 if the ID failed to be retrieved.
     */
    public int getCurrentTreeID(){
        for (int  i = 0; i<trees.length; i++){
            if (trees[i] == currentTree.getMaxParent()){
                return i;
            }
        }
        return -1;
    }

}


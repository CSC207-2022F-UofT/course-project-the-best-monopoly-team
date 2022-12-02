package Interactors;

import Entities.*;
import TreeHandlers.AuctionNodeLogic.AuctionTreeNodeLogic;
import TreeHandlers.AuctionTreeHandler;
import TreeHandlers.MainTreeHandler;
import TreeHandlers.TradingTreeHandler;
import TreeHandlers.GeneralGameLogic;

/**
 * This class creates a GameLogic instance which coordinates the flow of the game.
 */
public class GameLogic {
    private GameLogicTree[] trees = new GameLogicTree[3];
    private GameLogicTree currentTree;
    AuctionTreeHandler auctionTreeHandler;
    MainTreeHandler mainTreeHandler;
    TradingTreeHandler tradingTreeHandler;

    /**
     * This is the constructor for creating a GameLogic instance.
     * @param currentPlayer A Player instance that is intended to be the current player of the game's turn.
     * @param board A board instance that the GameLogic instance will help govern.
     */
    public GameLogic(Player currentPlayer, Board board){
        mainTreeHandler = new MainTreeHandler(this);
        AuctionTreeNodeLogic.array_init(board.getPlayers().size());
        auctionTreeHandler = new AuctionTreeHandler(this);
        tradingTreeHandler = new TradingTreeHandler(this);
        GeneralGameLogic.initialize(currentPlayer,board, this);

        createTrees();
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
        //Creating the game loop tree
        GameLogicTree main = new GameLogicTree("MainTree","It's your turn! What do you want to do?");
        GameLogicTree trade = new GameLogicTree("Trade");
        GameLogicTree pickPlayer = new GameLogicTree("PickPlayer");
        GameLogicTree pickItemOp = new GameLogicTree("PickItemOp");
        GameLogicTree pickItemSelf = new GameLogicTree("PickItemSelf");
        GameLogicTree sendTrade = new GameLogicTree("SendTrade");

        GameLogicTree nothingToTrade = new GameLogicTree("NothingToTrade");

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);
        pickPlayer.addChild(nothingToTrade);

        GameLogicTree manageProperty = new GameLogicTree("ManageProperty");
        GameLogicTree selectProperty = new GameLogicTree("SelectProperty");
        GameLogicTree mortgage = new GameLogicTree("Mortgage");
        GameLogicTree unMortgage = new GameLogicTree("UnMortgage");
        GameLogicTree buildProperty = new GameLogicTree("BuildProperty");

        GameLogicTree noProperties = new GameLogicTree("NoProperties");

        selectProperty.addChild(mortgage);
        selectProperty.addChild(unMortgage);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);
        manageProperty.addChild(noProperties);


        GameLogicTree roll = new GameLogicTree("Roll");
        GameLogicTree callAction = new GameLogicTree("CallAction");
        GameLogicTree emptyPropertySpace = new GameLogicTree("EmptyPropertySpace");
        GameLogicTree buy = new GameLogicTree("Buy");
        GameLogicTree auction = new GameLogicTree("Auction");
        GameLogicTree alreadyRolled = new GameLogicTree("AlreadyRolled");

        roll.addChild(emptyPropertySpace);
        roll.addChild(callAction);
        roll.addChild(alreadyRolled);

        emptyPropertySpace.addChild(buy);
        emptyPropertySpace.addChild(auction);

        GameLogicTree steal = new GameLogicTree("Steal");
        GameLogicTree choosePlayer = new GameLogicTree("ChoosePlayer");

        steal.addChild(choosePlayer);

        GameLogicTree endTurn = new GameLogicTree("EndTurn");

        GameLogicTree settingsMenu = new GameLogicTree("SettingsMenu");
        GameLogicTree exitGame = new GameLogicTree("ExitGame");
        GameLogicTree saveGame = new GameLogicTree("SaveGame", "Game saved!");

        GameLogicTree bankruptcy = new GameLogicTree("Bankruptcy", "Are you sure you want to declare bankruptcy?");

        GameLogicTree confirmationNode = new GameLogicTree("Confirmation");
        GameLogicTree informationNode = new GameLogicTree("Information");

        nothingToTrade.addChild(informationNode);
        noProperties.addChild(informationNode);
        sendTrade.addChild(informationNode);
        choosePlayer.addChild(informationNode);
        callAction.addChild(informationNode);
        buildProperty.addChild(informationNode);
        alreadyRolled.addChild(informationNode);
        saveGame.addChild(informationNode);
        endTurn.addChild(informationNode);
        auction.addChild(informationNode);

        mortgage.addChild(confirmationNode);
        exitGame.addChild(confirmationNode);
        bankruptcy.addChild(confirmationNode);

        settingsMenu.addChild(exitGame);
        settingsMenu.addChild(saveGame);
        settingsMenu.setIsSwitchBlock(true);

        main.addChild(trade);
        main.addChild(manageProperty);
        main.addChild(roll);
        main.addChild(steal);
        main.addChild(endTurn);
        main.addChild(settingsMenu);
        main.addChild(bankruptcy);

        main.setIsSwitchBlock(true);
        selectProperty.setIsSwitchBlock(true);
        emptyPropertySpace.setIsSwitchBlock(true);
        trees[0] = main;

        //Creating the trading tree
        GameLogicTree tradeTree = new GameLogicTree("TradeTree");
        GameLogicTree acceptTrade = new GameLogicTree("AcceptTrade");
        GameLogicTree declineTrade = new GameLogicTree("DeclineTrade");
        tradeTree.addChild(acceptTrade);
        tradeTree.addChild(declineTrade);
        tradeTree.setIsSwitchBlock(true);
        trees[1] = tradeTree;

        //Creating the auction tree
        GameLogicTree auctionTree = new GameLogicTree("AuctionTree");
        GameLogicTree lowOption = new GameLogicTree("LowOption");
        GameLogicTree mediumOption = new GameLogicTree("MediumOption");
        GameLogicTree highOption = new GameLogicTree("HighOption");
        GameLogicTree fold = new GameLogicTree("Fold");

        auctionTree.setIsSwitchBlock(true);
        auctionTree.addChild(lowOption);
        auctionTree.addChild(mediumOption);
        auctionTree.addChild(highOption);
        auctionTree.addChild(fold);
        trees[2] = auctionTree;

        currentTree = main;
    }

    /**
     * This method traverses through the GameLogicTrees that handle the state of the program.
     * @param input an int value that represents the direction of which the GameLogicTrees are to be traversed. -1
     *              represents traversing backwards. Otherwise, it will traverse forwards.
     * @return
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
        if (currentTree.getMaxParent() == trees[0]){
            State returnState =  mainTreeHandler.getUseCase().create_state(input);
            //mutating the state to have memory of its state, useful for backwards transversal
            currentTree.setPreviousState(returnState);
            return returnState;
        }
        else if (currentTree.getMaxParent() == trees[1]){
            return tradingTreeHandler.getUseCase().create_state(input);
        }
        else{
            return auctionTreeHandler.getUseCase().create_state(input);
        }

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


    /**
     * This method returns a State object representing the current condition of the game in relation to the auctionTreeHandler instance attribute.
     * @return Returns a State object representing the current condition of the game.
     */

}


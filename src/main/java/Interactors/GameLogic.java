package Interactors;

import Entities.*;
import TreeHandlers.AuctionTreeHandler;
import TreeHandlers.MainTreeHandler;
import TreeHandlers.TradingTreeHandler;

public class GameLogic {
    private GameLogicTree[] trees = new GameLogicTree[3];
    private GameLogicTree currentTree;
    AuctionTreeHandler auctionTreeHandler;
    MainTreeHandler mainTreeHandler;
    TradingTreeHandler tradingTreeHandler;

    public GameLogic(Player currentPlayer, Board board){
        mainTreeHandler = new MainTreeHandler();
        auctionTreeHandler = new AuctionTreeHandler(board.getPlayers().size());
        tradingTreeHandler = new TradingTreeHandler();
        mainTreeHandler.setGameLogicInteractor(this);
        mainTreeHandler.initialize(currentPlayer,board);
        createTrees();
    }

    public GameLogicTree[] getTrees(){
        return this.trees;
    }

    public void setTrees(GameLogicTree[] newTrees){
        this.trees = newTrees;
    }

    public GameLogicTree getCurrentTree(){
        return this.currentTree;
    }

    public void setCurrentTree(GameLogicTree newCurrentTree){
        this.currentTree = newCurrentTree;
    }
    public void setCurrentTreeToMaxParent(){
        currentTree = (GameLogicTree) currentTree.getMaxParent();
    }

    public void createTrees(){
        //TODO: make a tree node at the end of most end nodes to show the information after.
        //Creating the game loop tree
        GameLogicTree main = new GameLogicTree("MainTree","It's your turn! What do you want to do?");
        GameLogicTree trade = new GameLogicTree("Trade");
        GameLogicTree pickPlayer = new GameLogicTree("PickPlayer");
        GameLogicTree pickItemOp = new GameLogicTree("PickItemOp");
        GameLogicTree pickItemSelf = new GameLogicTree("PickItemSelf");

        //TODO: make the prompt say trade complete/failed
        GameLogicTree sendTrade = new GameLogicTree("SendTrade");

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);

        GameLogicTree manageProperty = new GameLogicTree("ManageProperty");
        GameLogicTree selectProperty = new GameLogicTree("SelectProperty");
        GameLogicTree mortgage = new GameLogicTree("Mortgage");
        GameLogicTree buildProperty = new GameLogicTree("BuildProperty");

        selectProperty.addChild(mortgage);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);

        //TODO: prompt has to alter based on what the player lands on
        GameLogicTree roll = new GameLogicTree("Roll");
        GameLogicTree callAction = new GameLogicTree("CallAction");
        GameLogicTree emptyPropertySpace = new GameLogicTree("EmptyPropertySpace");
        GameLogicTree buy = new GameLogicTree("Buy");
        GameLogicTree auction = new GameLogicTree("Auction");

        roll.addChild(emptyPropertySpace);
        roll.addChild(callAction);

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

        sendTrade.addChild(informationNode);
        choosePlayer.addChild(informationNode);
        callAction.addChild(informationNode);
        buildProperty.addChild(informationNode);
        roll.addChild(informationNode);
        saveGame.addChild(informationNode);

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

        //TODO: SHOW THE TRADED PROPERTIES
        //Creating the trading tree
        GameLogicTree tradeTree = new GameLogicTree("TradeTree");
        GameLogicTree acceptTrade = new GameLogicTree("AcceptTrade");
        GameLogicTree declineTrade = new GameLogicTree("DeclineTrade");
        trade.addChild(acceptTrade);
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
    public State performInput(int input){
        //Moving through the tree depending on the input and the node
        if (input == -1){
            //Move backwards in tree
            currentTree = (GameLogicTree) currentTree.getParent();
        }
        else if (currentTree.isSwitchBlock()){
            //Move forward in tree to one of the branches
            transverseCurrentTree(input);
        }
        else{
            //Move forward in tree
            currentTree = (GameLogicTree) currentTree.getChildren().get(0);
        }

        if (currentTree.getChildren().isEmpty()){
            setCurrentTreeToMaxParent();
        }
        return handleTree(input);
    }
    public void transverseCurrentTree(int branchNumber){
        currentTree =  (GameLogicTree) currentTree.getChildren().get(branchNumber);
    }
    public State handleTree(int input){
        if (currentTree.getMaxParent() == trees[0]){
            return mainTreeHandler.handleInput(input);
        }
        else if (currentTree.getMaxParent() == trees[1]){
            return tradingTreeHandler.handleInput();
        }
        else{
            return auctionTreeHandler.handleInput();
        }

    }

}


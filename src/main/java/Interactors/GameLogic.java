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
        auctionTreeHandler = new AuctionTreeHandler(board.getPlayers().length);
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

    public void createTrees(){
        //Creating the game loop tree
        GameLogicTree main = new GameLogicTree("MainTree");
        GameLogicTree trade = new GameLogicTree("Trade");
        GameLogicTree pickPlayer = new GameLogicTree("PickPlayer");
        GameLogicTree pickItemOp = new GameLogicTree("PickItemOp");
        GameLogicTree pickItemSelf = new GameLogicTree("PickItemSelf");
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

        GameLogicTree roll = new GameLogicTree("Roll");
        GameLogicTree buy = new GameLogicTree("Buy");
        GameLogicTree auction = new GameLogicTree("Auction");

        roll.addChild(buy);
        roll.addChild(auction);

        GameLogicTree steal = new GameLogicTree("Steal");
        GameLogicTree choosePlayer = new GameLogicTree("ChoosePlayer");

        steal.addChild(choosePlayer);

        GameLogicTree endTurn = new GameLogicTree("EndTurn");

        GameLogicTree settingsMenu = new GameLogicTree("SettingsMenu");
        GameLogicTree exitGame = new GameLogicTree("ExitGame");
        GameLogicTree saveGame = new GameLogicTree("SaveGame");
        GameLogicTree bankruptcy = new GameLogicTree("Bankruptcy");

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
        roll.setIsSwitchBlock(true);
        trees[0] = main;

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
            currentTree = (GameLogicTree) currentTree.getChildren().get(input);
        }
        else{
            //Move forward in tree
            currentTree = (GameLogicTree) currentTree.getChildren().get(0);
        }

        return handleTree(input);
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


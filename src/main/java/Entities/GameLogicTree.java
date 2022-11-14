package Entities;
import java.util.ArrayList;
import java.util.List;

public class GameLogicTree extends MenuTree{
    private boolean didRoll;
    public GameLogicTree(String name) {
        didRoll = false;
        this.name = name;
    }

    public GameLogicTree() {
        GameLogicTree trade = new GameLogicTree("trade");
        GameLogicTree pickPlayer = new GameLogicTree("pickPlayer");
        GameLogicTree pickItemOp = new GameLogicTree("pickItemOp");
        GameLogicTree pickItemSelf = new GameLogicTree("pickItemSelf");
        GameLogicTree sendTrade = new GameLogicTree("sendTrade");

        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);

        GameLogicTree manageProperty = new GameLogicTree("manageProperty");
        GameLogicTree selectProperty = new GameLogicTree("selectProperty");
        GameLogicTree mortgage = new GameLogicTree("mortgage");
        GameLogicTree sell = new GameLogicTree("sell");
        GameLogicTree buildProperty = new GameLogicTree("buildProperty");

        selectProperty.addChild(mortgage);
        selectProperty.addChild(sell);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);

        GameLogicTree roll = new GameLogicTree("roll");
        GameLogicTree buy = new GameLogicTree("buy");
        GameLogicTree auction = new GameLogicTree("auction");

        roll.addChild(buy);
        roll.addChild(auction);

        GameLogicTree choosePlayer = new GameLogicTree("choosePlayer");
        GameLogicTree steal = new GameLogicTree("steal");
        steal.addChild(choosePlayer);

        GameLogicTree main = new GameLogicTree("main");
        main.addChild(trade);
        main.addChild(manageProperty);
        main.addChild(roll);
        main.addChild(steal);

    }

    @Override
    public MenuTree getParent() {
        return this.parent;
    }

    @Override
    public List<MenuTree> getChildren() {
        return this.children;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setParent(MenuTree parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(MenuTree child) {
        child.setParent(this);
        this.children.add(child);
    }

    public boolean didRoll(){
        return this.didRoll;
    }
    public void rolled(){
        this.didRoll = true;
    }
    public void gotDoubles(){
        this.didRoll = false;
    }
}

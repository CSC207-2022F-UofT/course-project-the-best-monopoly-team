package Entities;
import java.util.ArrayList;
import java.util.List;

public class GameLogicTree extends MenuTree{
    private boolean didRoll;
    public GameLogicTree(String name) {
        didRoll = false;
        this.id = name;
    }

    public GameLogicTree() {
        GameLogicTree sendTrade = new GameLogicTree("sendTrade");
        GameLogicTree pickItemSelf = new GameLogicTree("pickItemSelf");
        GameLogicTree pickItemOp = new GameLogicTree("pickItemOp");
        GameLogicTree pickPlayer = new GameLogicTree("pickPlayer");
        GameLogicTree trade = new GameLogicTree("trade");
        pickItemSelf.addChild(sendTrade);
        pickItemOp.addChild(pickItemSelf);
        pickPlayer.addChild(pickItemOp);
        trade.addChild(pickPlayer);

        GameLogicTree mortgage = new GameLogicTree("mortgage");
        GameLogicTree sell = new GameLogicTree("sell");
        GameLogicTree buildProperty = new GameLogicTree("buildProperty");
        GameLogicTree selectProperty = new GameLogicTree("selectProperty");
        GameLogicTree manageProperty = new GameLogicTree("manageProperty");
        selectProperty.addChild(mortgage);
        selectProperty.addChild(sell);
        selectProperty.addChild(buildProperty);
        manageProperty.addChild(selectProperty);

        GameLogicTree roll = new GameLogicTree("roll");

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
    public String getId() {
        return this.id;
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

package Entities;
import java.util.ArrayList;
import java.util.List;

public class GameLogicTree extends MenuTree{
    private boolean didRoll;

    public void setIsSwitchBlock(boolean switchBlock) {
        this.switchBlock = switchBlock;
    }

    private boolean switchBlock = false;

    public GameLogicTree(String name) {
        didRoll = false;
        this.name = name;
    }

    @Override
    public MenuTree getParent() {
        return this.parent;
    }

    @Override
    public MenuTree getMaxParent(){
        if (this.parent == null){
            return this;
        }
        else{
            return this.parent.getMaxParent();
        }
    }
    @Override
    public List<MenuTree> getChildren() {
        return this.children;
    }

    @Override
    public String getName() {
        return name;
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

    public boolean isSwitchBlock() {
        return switchBlock;
    }
}

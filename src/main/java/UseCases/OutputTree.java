package UseCases;
import Entities.MenuTree;

import java.util.ArrayList;
import java.util.List;

public class OutputTree extends MenuTree {
    private ArrayList<Integer[]> positions = new ArrayList<>();
    static int globalID = 0;
    private boolean switchBlock = false;

    public OutputTree(String name){
        this.parent = null;
        createObject(name);
    }

    public int getID() {
        return id;
    }

    public void createObject(String name){
        this.name = name;
        this.id = globalID;
        globalID += 1;
    }
    public void setGlobalID(int num){
        globalID = num;
    }

    public void addButtonPosition(Integer[] position){
        this.positions.add(position);
    }
    public ArrayList<Integer[]> getButtonPosition(){
        return positions;
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
        return name;
    }

    @Override
    public void setParent(MenuTree parent) {
        this.parent = parent;
    }
    @Override
    public void addChild(MenuTree child) {
        this.children.add(child);
        child.setParent(this);
    }


    public boolean isSwitchBlock() {
        return switchBlock;
    }

    public void setSwitchBlock(boolean switchBlock) {
        this.switchBlock = switchBlock;
    }
}

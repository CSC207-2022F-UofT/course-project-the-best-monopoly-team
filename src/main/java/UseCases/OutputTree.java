package UseCases;
import Entities.MenuTree;

import java.util.ArrayList;
import java.util.List;

public class OutputTree extends MenuTree {
    private ArrayList<Integer[]> positions = new ArrayList<>();
    public OutputTree(String id){
        this.parent = null;
        this.id = id;
    }
    public OutputTree(String id, OutputTree parent){
        this.parent = parent;
        this.id = id;
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
    public String getId() {
        return id;
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



}

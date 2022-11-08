package Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuTree<T>{
    private List<MenuTree<T>> children;
    private MenuTree<T> parent;
    private String id;

    public MenuTree(String id){
        this.children = new ArrayList<>();
        this.parent = null;
        this.id = id;
    }
    abstract MenuTree<T> getParent();
    abstract List<MenuTree<T>> getChildren();
    abstract String getId();
    abstract void setParent(MenuTree<T> parent);
    abstract void addChild(MenuTree<T> child);
}

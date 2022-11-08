package Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuTree<T>{
    private List<OptionsMenuTree<T>> children = new ArrayList<>();
    private OptionsMenuTree<T> parent = null;
    private String id;

    public MenuTree(String id){
        this.id = id;
    }
    abstract OptionsMenuTree<T> getParent();
    abstract List<OptionsMenuTree<T>> getChildren();
    abstract String getId();
    abstract void setParent(OptionsMenuTree<T> parent);
    abstract void addChild(OptionsMenuTree<T> child);
}

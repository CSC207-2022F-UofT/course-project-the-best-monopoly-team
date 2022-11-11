package Entities;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuTree{

    protected List<MenuTree> children = new ArrayList<>();
    protected MenuTree parent;
    protected String name;
    //makes tree node handling easier
    protected int id;

    public abstract MenuTree getParent();
    public abstract List<MenuTree> getChildren();
    public abstract String getName();
    public abstract void setParent(MenuTree parent);
    public abstract void addChild(MenuTree child);
}

package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a tree representing the flow of the game.
 */
public abstract class MenuTree{

    protected List<MenuTree> children = new ArrayList<>();
    protected MenuTree parent;
    public abstract MenuTree getParent();
    public abstract MenuTree getMaxParent();
    public abstract List<MenuTree> getChildren();
    public abstract void setParent(MenuTree parent);
    public abstract void addChild(MenuTree child);
}

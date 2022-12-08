package Entities;
import Interface.NodeLogic;
import java.util.List;

/**
 * This class represents a Tree object pertaining to the flow of the game or logic of the game.
 */
public class GameLogicTree extends MenuTree{

    private boolean switchBlock = false;

    private State previousState;
    private NodeLogic useCase;

    /**
     * This is the constructor which creates an instance of GameLogicTree.
     * @param useCase a NodeLogic object representing the use case that the GameLogicTree pertains to.
     */
    public GameLogicTree(NodeLogic useCase) {
        this.useCase = useCase;
    }

    /**
     * This method returns a NodeLogic object representing the use case that the GameLogicTree pertains to.
     * @return a NodeLogic object representing the use case that the GameLogicTree pertains to.
     */
    public NodeLogic getUseCase(){
        return useCase;
    }

    /**
     * This method sets the parent instance attribute which represents the parent node of the tree.
     * @param parent a MenuTree instance attribute which represent the parent node of the tree.
     */
    @Override
    public void setParent(MenuTree parent) {
        this.parent = parent;
    }

    /**
     * This method returns a MenuTree instance representing the parent node of the tree.
     * @return a MenuTree instance representing the parent node of the tree.
     */
    @Override
    public MenuTree getParent() {
        return this.parent;
    }

    /**
     * This method returns a MenuTree instance that represents the highest parent node of the tree.
     * @return a MenuTree instance that represents the highest parent node of the tree.
     */
    @Override
    public MenuTree getMaxParent(){
        if (this.parent == null){
            return this;
        }
        else{
            return this.parent.getMaxParent();
        }
    }

    /**
     * This method returns a List containing the tree's children.
     * @return a List containing the tree's children.
     */
    @Override
    public List<MenuTree> getChildren() {
        return this.children;
    }

    /**
     * This method adds a MenuTree instance as a child of the tree.
     * @param child a MenuTree instance that is to be added as a child of the tree.
     */
    @Override
    public void addChild(MenuTree child) {
        child.setParent(this);
        this.children.add(child);
    }

    /**
     * This method sets the switchBlock instance attribute which represents if there are different scenarios to choose
     * from after the current one.
     * @param switchBlock a boolean representing if there are different scenarios to choose from after the current one.
     */
    public void setIsSwitchBlock(boolean switchBlock) {
        this.switchBlock = switchBlock;
    }

    /**
     * This method returns a boolean representing if there are different scenarios to choose from after the current one.
     * @return a boolean representing if there are different scenarios to choose from after the current one.
     */
    public boolean isSwitchBlock() {
        return switchBlock;
    }

    /**
     * This method returns a State object representing the previous state of the game.
     * @return a State object representing the previous state of the game.
     */
    public State getPreviousState() {
        return previousState;
    }

    /**
     * This method sets the previousState instance attribute which represents the previous state of the game.
     * @param previousState a State object representing the previous state of the game.
     */
    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }
}

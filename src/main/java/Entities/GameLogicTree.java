package Entities;
import Interface.NodeLogic;
import java.util.List;

public class GameLogicTree extends MenuTree{

    private boolean switchBlock = false;
    private String prompt;
    private State previousState;
    private NodeLogic useCase;

    public GameLogicTree(NodeLogic useCase) {
        this.useCase = useCase;
    }

    public NodeLogic getUseCase(){
        return useCase;
    }
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }
    public String getPrompt(){
        return prompt;
    }
    @Override
    public void setParent(MenuTree parent) {
        this.parent = parent;
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
    public void addChild(MenuTree child) {
        child.setParent(this);
        this.children.add(child);
    }
    public void setIsSwitchBlock(boolean switchBlock) {
        this.switchBlock = switchBlock;
    }
    public boolean isSwitchBlock() {
        return switchBlock;
    }
    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }
}

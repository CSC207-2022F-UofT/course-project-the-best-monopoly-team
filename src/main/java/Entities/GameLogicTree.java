package Entities;
import Interface.NodeLogic;
import java.util.List;

public class GameLogicTree extends MenuTree{
    private boolean didRoll;
    private boolean switchBlock = false;
    private String prompt;
    private State previousState;
    private NodeLogic useCase;

    public GameLogicTree(NodeLogic useCase) {
        didRoll = false;
  //      this.name = name;
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

//    @Override
//    public String getName() {
//        return name;
//    }

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

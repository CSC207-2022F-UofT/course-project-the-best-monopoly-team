package Logic.InitialNodeLogic;

import Entities.GameLogicTree;
import Entities.MenuTree;
import Entities.State;
import Logic.NodeLogic;

public class InitialParentNodeUseCase extends InitialLogic implements NodeLogic {
    public InitialParentNodeUseCase() {
        super("Initial Menu Parent Node");
    }

    @Override
    public State create_state(int input) {
        State currentState = beforeLogic();
        currentState.setId(getName());
        for (MenuTree tree: currentTree.getChildren()){
            currentState.addOptions(((InitialLogic)((GameLogicTree)tree).getUseCase()).getName());
        }
        return afterLogic(currentState);
    }
}

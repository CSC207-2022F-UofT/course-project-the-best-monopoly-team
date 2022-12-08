package NodeLogic.TradingNodeLogic;

import Entities.State;
import UseCases.NodeLogic;

public class TradingParentNode extends TradingTreeNodeLogic implements NodeLogic {
    public TradingParentNode() {
        super("Trading Parent Node");
    }

    @Override
    public State create_state(int input) {
        State currentState = new State();
        addSwitchOptions(currentState);
        currentState.setId(getName());
        return currentState;
    }
}

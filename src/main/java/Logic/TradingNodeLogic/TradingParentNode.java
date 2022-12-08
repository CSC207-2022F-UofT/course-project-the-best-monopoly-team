package Logic.TradingNodeLogic;

import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case where a trade is initiated.
 */
public class TradingParentNode extends TradingTreeNodeLogic implements NodeLogic {
    public TradingParentNode() {
        super("Trading Parent Node");
    }

    /**
     * This method creates and returns a State object containing relevant information on the trade. This includes the
     * options of what the player can do after the initiation of a trade.
     * @param input an integer representing the user's input. However, this parameter will not be used in this method.
     * @return a State object including relevant information on the trade like the options of what the player can do
     * after the initiation of a trade.
     */
    @Override
    public State create_state(int input) {
        State currentState = new State();
        addSwitchOptions(currentState);
        currentState.setId(getName());
        return currentState;
    }
}

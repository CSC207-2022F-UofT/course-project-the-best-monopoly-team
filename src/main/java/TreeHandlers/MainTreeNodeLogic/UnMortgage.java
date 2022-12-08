package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interface.NodeLogic;
import UseCases.PlayerLogic;

import java.util.HashMap;

public class UnMortgage extends MainTreeNodeLogic implements NodeLogic {
    public UnMortgage() {
        super("Un-Mortgage property");
    }

    @Override
    public State create_state(int input) {

        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();
        PlayerLogic currentPlayerLogic = new PlayerLogic(currentPlayer);
        State currentState = new State();
        currentState.setId(getName());
        Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("PropertySelected"));
        currentPlayerLogic.unmortgage(targetProperty);
        currentState = afterBottomNode();
        return currentState;
    }
}
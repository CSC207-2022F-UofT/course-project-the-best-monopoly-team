package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.NodeLogic;
import Logic.PlayerLogic;

import java.util.HashMap;

/**
 * This class represents the use case where the current player chooses to un-mortgage a chosen property.
 */
public class UnMortgageUseCase extends MainTreeNodeLogic implements NodeLogic {
    public UnMortgageUseCase() {
        super("Un-Mortgage property");
    }

    /**
     * This method creates a State object and returns it. It contains relevant information of the state of the game
     * after the player's property is un-mortgaged.
     * @param input an integer representing the user's input. This parameter will not be used for this method.
     * @return a State object containing relevant information after a player un-mortgages their property.
     */
    @Override
    public State create_state(int input) {

        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();
        PlayerLogic currentPlayerLogic = new PlayerLogic(currentPlayer);
        State currentState = new State();
        currentState.setId(getName());
        Property targetProperty = currentPlayer.getProperties().get(selectedOptions.get("PropertySelected"));
        if (targetProperty.getMortgageStatus()){
            currentPlayerLogic.unmortgage(targetProperty);
        }
        currentState = afterBottomNode();
        return currentState;
    }
}
package UseCases.Logic.MainTreeNodeLogic;

import Entities.*;
import UseCases.Logic.NodeLogic;

import java.util.HashMap;

/**
 * This class represents a use case where the current player selects a property that it owns that will then be either
 * mortgaged, un-mortgaged, or have a house built on it.
 */
public class SelectPropertyUseCase extends MainTreeNodeLogic implements NodeLogic {
    public SelectPropertyUseCase() {
        super("Select The Property (Manage Property)");
    }

    /**
     * This method creates a State object and adds the current player's selected option for which property it wants to
     * manage and adds the options to mortgage, un-mortgage, and to build a house to the State object.
     * @param input An integer representing the index of the property that is to be selected.
     * @return A State object containing options to mortgage, un-mortgage, or build a house and the chosen property.
     */
    @Override
    public State create_state(int input) {
        HashMap<String, Integer> selectedOptions = getSelectedOptions();
        State currentState = new State();
        currentState.setId(getName());
        currentState.setBackEnable(true);

        //Case property selected (adds the property index)
        selectedOptions.put("PropertySelected", input);

        //the player chooses what to do to the property
        currentState.addOptions("Mortgage");
        currentState.addOptions("Unmortgage");
        currentState.addOptions("Build a house");

        return currentState;
    }
}
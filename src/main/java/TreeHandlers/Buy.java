package TreeHandlers;

import Entities.Property;
import Entities.State;

public class Buy extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //player buys the property that the player lands on
                targetProperty = (Property) board.getCell(currentPlayer.getPosition());

        //indicates that the player can afford it and sets the property owner as the current player and
        //deducts the player's money.
        currentPlayer.pay(targetProperty.getPrice());
        currentPlayer.getProperties().add(targetProperty);
        targetProperty.setOwner(currentPlayer);
        currentState = afterBottomNode();

        return currentState;
    }
}
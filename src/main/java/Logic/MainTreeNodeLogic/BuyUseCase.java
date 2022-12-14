package Logic.MainTreeNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.Property;
import Entities.State;
import Logic.NodeLogic;

/**
 * This class represents the use case for when the current player wants to buy a property.
 */
public class BuyUseCase extends MainTreeNodeLogic implements NodeLogic {

    public BuyUseCase() {
        super("Buy Property");
    }

    /**
     * This method goes through the steps for the current player to buy the house if the current player has sufficient
     * funds and returns a State object with the necessary information on the game. If the purchase was successful, it
     * indicates the end of the tree path.
     * @param input An integer representing the user's input. However, this will not be used for this method.
     * @return A State object containing the necessary information on the game. If the purchase was successful it will
     * indicate the end of a tree path.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        Property targetProperty;

        State currentState = new State();
        currentState.setId(getName());
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
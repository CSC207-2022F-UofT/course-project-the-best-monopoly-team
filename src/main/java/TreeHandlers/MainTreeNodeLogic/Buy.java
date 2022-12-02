package TreeHandlers.MainTreeNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.Property;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class Buy extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
         Property targetProperty = getTargetProperty();

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
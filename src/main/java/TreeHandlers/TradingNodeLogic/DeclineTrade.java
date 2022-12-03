package TreeHandlers.TradingNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

/**
 * This class represents the use case when a player declines a trade proposal from another player.
 */
public class DeclineTrade extends TradingTreeNodeLogic implements NodeLogic {

    /**
     * This method reverts the game to its previous state before the trade was attempted. It returns a State object
     * containing information to continue the game.
     * @param input An integer representing the user's input. This parameter will not be used for this method.
     * @return State object containing information to continue the game.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        int returnPlayerIndex = getReturnPlayerIndex();
        GameLogic gameLogicInteractor = getGameLogicInteractor();

        State currentState = new State();
        Player firstTrader = board.getPlayers().get(returnPlayerIndex);

        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        // swap control back to the original player
        setCurrentPlayer(firstTrader);
        //goes back to initial tree
        gameLogicInteractor.setCurrentTree(getReturnTree());

        //option for return node
        currentState.addOptions("Ok");
        return currentState;
    }
}

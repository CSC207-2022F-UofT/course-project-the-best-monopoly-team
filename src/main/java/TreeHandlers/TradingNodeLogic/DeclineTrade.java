package TreeHandlers.TradingNodeLogic;

import Entities.Board;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class DeclineTrade extends TradingTreeNodeLogic implements NodeLogic {
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

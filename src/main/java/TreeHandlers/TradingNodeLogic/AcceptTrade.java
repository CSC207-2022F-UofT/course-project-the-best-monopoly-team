package TreeHandlers.TradingNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class AcceptTrade extends TradingTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        int returnPlayerIndex = getReturnPlayerIndex();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        GameLogicTree returnTree = getReturnTree();

        State currentState = new State();
        Player firstTrader = board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = getCurrentPlayer();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        Property secondTraderProperty = secondTrader.getProperties().get(getSelectedOptions().get("PickItemOp"));
        Property firstTraderProperty = firstTrader.getProperties().get(getSelectedOptions().get("PickPlayer"));

        // swap the asset owners
        secondTraderProperty.setOwner(firstTrader);
        firstTraderProperty.setOwner(secondTrader);
        secondTrader.getProperties().remove(secondTraderProperty);
        secondTrader.getProperties().add(firstTraderProperty);
        firstTrader.getProperties().remove(firstTraderProperty);
        firstTrader.getProperties().add(secondTraderProperty);
        // swap control back to the original player
        setCurrentPlayer(firstTrader);
        //goes back to initial tree
        gameLogicInteractor.setCurrentTree(returnTree);
        setCurrentPlayer(getPlayers().get(returnPlayerIndex));

        //option for return node
        currentState.addOptions("Ok");
        return currentState;
    }
}

package Logic.TradingNodeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.NodeLogic;

/**
 * This class represents the use case where a player accepts a trade proposal from another player.
 */
public class AcceptTrade extends TradingTreeNodeLogic implements NodeLogic {

    public AcceptTrade() {
        super("Accept The Trade");
    }

    /**
     * This method handles the trade when it is accepted and moves their property items from one player to another.
     * It returns a State object containing an "Ok" option and all the necessary information to continue.
     * @param input - unused in this node
     * @return A state object representing the current state of the program
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        int returnPlayerIndex = getReturnPlayerIndex();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        GameLogicTree returnTree = getReturnTree();

        State currentState = new State();
        Player firstTrader = board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = getCurrentPlayer();
        currentState.setId(getName());
        Property secondTraderProperty = secondTrader.getProperties().get(getSelectedOptions().get("PickItemOp"));
        Property firstTraderProperty = firstTrader.getProperties().get(getSelectedOptions().get("PickItemSelf"));

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

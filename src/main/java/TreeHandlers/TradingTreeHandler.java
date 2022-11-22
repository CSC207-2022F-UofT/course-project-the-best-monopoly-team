package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

/**
 * This tree handler handles the input when trading properties
 */
public class TradingTreeHandler extends TreeHandler {
    /**
     * Method to handle input inside this tree.
     * @return the current state of the program
     */
    public State handleInput(){
        //setting up the objects that deal with the trade
        State currentState = new State();
        Player firstTrader = board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = currentPlayer;
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());

        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "AcceptTrade":
                Property secondTraderProperty = secondTrader.getProperties().get(selectedOptions.get("PickItemOp"));
                Property firstTraderProperty = firstTrader.getProperties().get(selectedOptions.get("PickPlayer"));

                // swap the asset owners
                secondTraderProperty.setOwner(firstTrader);
                firstTraderProperty.setOwner(secondTrader);
                secondTrader.getProperties().remove(secondTraderProperty);
                secondTrader.getProperties().add(firstTraderProperty);
                firstTrader.getProperties().remove(firstTraderProperty);
                firstTrader.getProperties().add(secondTraderProperty);
                // swap control back to the original player
                currentPlayer = firstTrader;
                break;
            case "DeclineTrade":
                // swap control back to the original player
                currentPlayer = firstTrader;
                break;
        }
        //goes back to initial tree
        gameLogicInteractor.setCurrentTree(getReturnTree());
        currentPlayer = players.get(returnPlayerIndex);

        //option for return node
        currentState.addOptions("Ok");
        return currentState;
    }

}

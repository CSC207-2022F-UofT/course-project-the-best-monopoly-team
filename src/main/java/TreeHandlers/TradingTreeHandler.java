package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

public class TradingTreeHandler extends TreeHandler {

    public State handleInput(){
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
                currentPlayer = firstTrader;
                break;
            case "DeclineTrade":
                currentPlayer = firstTrader;
                break;
        }
        gameLogicInteractor.setCurrentTree(getReturnTree());
        currentPlayer = players.get(returnPlayerIndex);
        currentState.addOptions("Ok");
        return currentState;
    }

}

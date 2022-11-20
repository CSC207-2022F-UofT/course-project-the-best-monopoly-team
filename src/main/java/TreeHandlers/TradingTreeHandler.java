package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

public class TradingTreeHandler extends TreeHandler {
    int[] tradingStates = new int[5];

    public State handleInput(){
        State currentState = new State();
        Player firstTrader = this.board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = this.currentPlayer;
        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "AcceptTrade":
                Property secondTraderProperty = secondTrader.getProperties().get(selectedOptions.get("PickPlayer"));
                Property firstTraderProperty = firstTrader.getProperties().get(selectedOptions.get("PickItemOp"));
                // swap the asset owners
                secondTraderProperty.setOwner(firstTrader);
                firstTraderProperty.setOwner(secondTrader);
                secondTrader.getProperties().remove(secondTraderProperty);
                secondTrader.getProperties().add(firstTraderProperty);
                firstTrader.getProperties().remove(firstTraderProperty);
                firstTrader.getProperties().add(secondTraderProperty);
                this.currentPlayer = firstTrader;
                gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[0]);
                description = "Trade successful";
                break;
            case "DeclineTrade":
                this.currentPlayer = firstTrader;
                gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[0]);
                description = "Trade failed";
                break;
        }
        return currentState;
    }

}

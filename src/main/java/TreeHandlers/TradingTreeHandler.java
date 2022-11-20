package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

public class TradingTreeHandler extends TreeHandler {
    int[] tradingStates = new int[5];

    public State handleInput(){
        State currentState = new State();
        Player firstTrader = this.board.getPlayers()[returnPlayerIndex];
        Player secondTrader = this.currentPlayer;
        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "AcceptTrade":
                Property secondTraderProperty = secondTrader.properties.get(selectedOptions.get("PickItemOp"));
                Property firstTraderProperty = firstTrader.properties.get(selectedOptions.get("PickItemSelf"));
                // swap the asset owners
                secondTraderProperty.setOwner(firstTrader);
                firstTraderProperty.setOwner(secondTrader);
                secondTrader.properties.remove(secondTraderProperty);
                secondTrader.properties.add(firstTraderProperty);
                firstTrader.properties.remove(firstTraderProperty);
                firstTrader.properties.add(secondTraderProperty);
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

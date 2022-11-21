package TreeHandlers;

import Entities.Player;
import Entities.Property;
import Entities.State;

public class TradingTreeHandler extends TreeHandler {

    public State handleInput(){
        Player firstTrader = board.getPlayers().get(returnPlayerIndex);
        Player secondTrader = currentPlayer;
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
                currentPlayer = firstTrader;
                gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[0]);
                descriptionOtherTrees = "Trade successful";
                break;
            case "DeclineTrade":
                currentPlayer = firstTrader;
                gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[0]);
                descriptionOtherTrees = "Trade failed";
                break;
        }
        gameLogicInteractor.setCurrentTree(getReturnTree());
        currentPlayer = players.get(returnPlayerIndex);
        return gameLogicInteractor.handleTree(0);
    }

}

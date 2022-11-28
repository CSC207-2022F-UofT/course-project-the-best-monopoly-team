package TreeHandlers;

import Entities.GameLogicTree;
import Entities.Player;
import Entities.State;

public class SendTrade extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        if (input == 0) {
            Player tradingOpponent = board.getPlayers().get(selectedOptions.get("PickPlayer"));
            currentState.setTradingOpponent(tradingOpponent);
            currentState.setPlayer(currentPlayer);
            currentState.setCurrentPlayerProperty(currentPlayer.getProperties().get(selectedOptions.get("PickItemSelf")));
            currentState.setTradingPlayerProperty(tradingOpponent.getProperties().get(selectedOptions.get("PickItemOp")));
            returnTree = currentTree;
            returnPlayerIndex = getCurrentPlayerIndex();
            //returnPlayerAddress will hold the original player index in this.board.getPlayers()
            currentPlayer = tradingOpponent;
            gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
            addSwitchOptions(currentState);
        }
        else{
            currentState = afterBottomNode();
        }
        return currentState;
    }
}
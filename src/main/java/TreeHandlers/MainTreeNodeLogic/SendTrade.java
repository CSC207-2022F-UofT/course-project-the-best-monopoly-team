package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

import java.util.HashMap;

public class SendTrade extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        if (input == 0) {
            Player tradingOpponent = board.getPlayers().get(selectedOptions.get("PickPlayer"));
            currentState.setTradingOpponent(tradingOpponent);
            currentState.setPlayer(currentPlayer);
            currentState.setCurrentPlayerProperty(currentPlayer.getProperties().get(selectedOptions.get("PickItemSelf")));
            currentState.setTradingPlayerProperty(tradingOpponent.getProperties().get(selectedOptions.get("PickItemOp")));
            setReturnTree(currentTree);
            setReturnPlayerIndex(getCurrentPlayerIndex());
            //returnPlayerAddress will hold the original player index in this.board.getPlayers()
            setCurrentPlayer(tradingOpponent);
            gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[1]);
            addSwitchOptions(currentState);
        }
        else{
            currentState = afterBottomNode();
        }
        return currentState;
    }
}
package Logic.MainTreeNodeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.NodeLogic;

import java.util.HashMap;

/**
 * This class represents a use case where the current user send a trade to the targeted player.
 */

public class SendTradeUseCase extends MainTreeNodeLogic implements NodeLogic {

    public SendTradeUseCase() {
        super("Send The Trade");
    }

    /**
     * This method creates a State
     * @param input An integer representing if the trade is to be sent. 0 means that the trade will be sent over.
     * @return A State object containing either one that represents the end of a tree path, or one that contains
     * required to propose the trade.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();
        HashMap<String, Integer> selectedOptions = getSelectedOptions();

        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(getName());
        // if the input is 0, is if the trade is to be sent. Otherwise, the trade will not be sent.
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
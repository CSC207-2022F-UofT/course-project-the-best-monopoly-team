package TreeHandlers.MainTreeNodeLogic;

import Entities.GameLogicTree;
import Entities.State;
import Interactors.GameLogic;
import Interface.NodeLogic;

public class Auction extends MainTreeNodeLogic implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
        //returnPlayerAddress will hold the original player index in this.board.getPlayers()
        setReturnPlayerIndex(getCurrentPlayerIndex());

        //change trees and start the auction
        setReturnTree(currentTree);
        gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[2]);
        setupAuction();
        currentState = getCurrentState();
        return currentState;
    }
}
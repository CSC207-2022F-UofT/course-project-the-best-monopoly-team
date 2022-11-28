package TreeHandlers;

import Entities.GameLogicTree;
import Entities.State;

public class Auction extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        //returnPlayerAddress will hold the original player index in this.board.getPlayers()
        returnPlayerIndex = getCurrentPlayerIndex();

        //change trees and start the auction
        returnTree = currentTree;
        gameLogicInteractor.setCurrentTree(gameLogicInteractor.getTrees()[2]);
        gameLogicInteractor.setupAuction();
        currentState = getCurrentState();
        return currentState;
    }
}
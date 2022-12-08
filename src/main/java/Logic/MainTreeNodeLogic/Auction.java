package Logic.MainTreeNodeLogic;

import Entities.GameLogicTree;
import Entities.State;
import Interactors.GameLogic;
import Logic.NodeLogic;

/**
 * This class represents the use case of when an auction is to be started.
 */
public class Auction extends MainTreeNodeLogic implements NodeLogic {
    public Auction() {
        super("Auction");
    }

    /**
     * This method returns a State object containing the original current player and the original tree node that the
     * game was on. It will then change the trees and start the auction.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object containing the current player and the node that the game was in prior to setting up the
     * auction.
     */
    @Override
    public State create_state(int input) {
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        State currentState = new State();
        currentState.setId(getName());
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
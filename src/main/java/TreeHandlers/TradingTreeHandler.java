package TreeHandlers;

import Entities.GameLogicTree;
import Interactors.GameLogic;
import Interface.ErrorCase;
import Interface.NodeLogic;
import TreeHandlers.TradingNodeLogic.AcceptTrade;
import TreeHandlers.TradingNodeLogic.DeclineTrade;

/**
 * This tree handler handles the input when trading properties
 */
public class TradingTreeHandler {
    /**
     * Method to handle input inside this tree.
     * @return the current state of the program
     */
    GameLogic gameLogicInteractor;
    public TradingTreeHandler(GameLogic gameLogicInteractor){
        this.gameLogicInteractor = gameLogicInteractor;
    }

    public NodeLogic getUseCase(){
        //setting up the objects that deal with the trade
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        switch (currentTree.getName()){
            case "AcceptTrade":
                return new AcceptTrade();
            case "DeclineTrade":
               return new DeclineTrade();
        }
        return new ErrorCase();

    }

}

package TreeHandlers;

import Interactors.GameLogic;
import Interface.ErrorCase;
import Interface.NodeLogic;
import TreeHandlers.AuctionNodeLogic.Fold;
import TreeHandlers.AuctionNodeLogic.HighOption;
import TreeHandlers.AuctionNodeLogic.LowOption;
import TreeHandlers.AuctionNodeLogic.MediumOption;

/**
 * This tree handler handles the input during the auction phase of the program.
 */
public class AuctionTreeHandler{

    /**
     * This method handles the choices of the user in the auction game part of the program.
     * <p>
     */

    GameLogic gameLogicInteractor;
    public AuctionTreeHandler(GameLogic gameLogicInteractor){
        this.gameLogicInteractor = gameLogicInteractor;
    }
    public NodeLogic getUseCase(){

        switch (gameLogicInteractor.getCurrentTree().getName()){
            case "LowOption":
                return new LowOption();
            case "MediumOption":
                return new MediumOption();
            case "HighOption":
                return new HighOption();
            case "Fold":
                return new Fold();
        }
        return new ErrorCase();
    }





}

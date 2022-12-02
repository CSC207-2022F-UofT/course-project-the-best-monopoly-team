package TreeHandlers;

import Entities.*;
import Interactors.GameLogic;
import Interface.ErrorCase;
import Interface.NodeLogic;
import TreeHandlers.MainTreeNodeLogic.*;


/**
 * This tree handler handles the input during main game phase of the program.
 */
public class MainTreeHandler {

    //mainStates[0]: variable reserved for confirmation node
    //mainStates[1]: variable reserved for roll
    GameLogic gameLogicInteractor;
    public MainTreeHandler(GameLogic gameLogicInteractor){
        this.gameLogicInteractor = gameLogicInteractor;
    }

    /**
     * This method handles the input of the user in the main game part of the program.
     * <p>
     *
     */
    public NodeLogic getUseCase(){
        GameLogicTree currentTree = gameLogicInteractor.getCurrentTree();
        switch (currentTree.getName()){
            case "Trade":
                return new Trade();
            case "PickPlayer":
                return new PickPlayer();
            case "NothingToTrade":
                return new NothingToTrade();
            case "AlreadyRolled":
                return new AlreadyRolled();
            case "PickItemOp":
                return new PickItemOp();
            case "PickItemSelf":
                return new PickItemSelf();
            case "SendTrade":
                return new SendTrade();
            case "ManageProperty":
                return new ManageProperty();
            case "NoProperties":
                return new NoProperties();
            case "SelectProperty":
                return new SelectProperty();
            case "Mortgage":
                return new Mortgage();
            case "UnMortgage":
                return new UnMortgage();
            case "BuildProperty":
                return new BuildProperty();
            case "Roll":
                return new Roll();
            case "CallAction":
                return new CallAction();
            case "EmptyPropertySpace":
                return new EmptyPropertySpace();
            case "Buy":
                return new Buy();
            case "Auction":
                return new Auction();
            case "Steal":
                return new Steal();
            case "ChoosePlayer":
                return new ChoosePlayerUseCase();
            case "EndTurn":
                return new EndTurnUseCase();
            case "SettingsMenu":
                return new SettingsMenuUseCase();
            case "ExitGame":
                return new ExitGameUseCase();
            case "SaveGame":
                return new SaveGameUseCase();
            case "Bankruptcy":
                return new BankruptcyUseCase();
            case "Confirmation":
                return new ConfirmationUseCase();
            case "Information":
                return new InformationUseCase();
        }
        return new ErrorCase();
    }
}

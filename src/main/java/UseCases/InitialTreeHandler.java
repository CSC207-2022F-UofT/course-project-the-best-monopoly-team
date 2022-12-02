package UseCases;

import Entities.GameLogicTree;
import Entities.State;
import Interface.ErrorCase;
import Interface.NodeLogic;
import UseCases.InitialNodeLogic.*;

import java.util.HashMap;

/**
 * This tree handler handles the input during the initial menu
 */
public class InitialTreeHandler {

    UseCaseInteractor caseInteractor;
    public InitialTreeHandler(UseCaseInteractor caseInteractor){
        this.caseInteractor = caseInteractor;
    }

    /**
     * This method handles the input of the user in the initialization part of the program.
     * <p>
     * The player's choices are stored in the selectedOptions map for usage later.
     *
     */
    public NodeLogic getUseCase(){
        GameLogicTree currentTree = caseInteractor.getCurrentTree();
        //deciding what to do based on node visited
        switch (currentTree.getName()){
            case "NewGame":
                return new NewGame();
            case "ChooseGameMode":
                return new ChooseGameMode();
            case "NumberOfPlayers":
                return new NumberOfPlayers();
            case "GameLength":
                return new GameLength();
            case "CreateNewGame":
                return new CreateNewGame();
            case "LoadGame":
                return new LoadGame();
            case "ChooseSave":
                return new ChooseSave();
            case "CreateLoadedGame":
                return new CreateLoadedGame();
        }
        return new ErrorCase();
    }
}

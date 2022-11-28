package TreeHandlers;

import Entities.*;

import java.util.ArrayList;


/**
 * This tree handler handles the input during main game phase of the program.
 */
public class MainTreeHandler extends TreeHandler {

    //mainStates[0]: variable reserved for confirmation node
    //mainStates[1]: variable reserved for roll

    public MainTreeHandler(){
    }

    /**
     * Constructor for the class when states are already known.
     * @param states
     */
    public MainTreeHandler(int[] states){
        mainStates = states;
    }

    /**
     * This method handles the input of the user in the main game part of the program.
     * <p>
     *
     * @param input the translated input of the user from the input interface
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

                break;
            case "PickItemSelf":


                break;
            case "SendTrade":

                break;
            case "ManageProperty":

                break;
            case "NoProperties":

                break;

            case "SelectProperty":

                break;
            case "Mortgage":

                break;
            case "UnMortgage":
                //TODO: ADD FUNCTIONALITY
                break;
            case "BuildProperty":

                break;
            case "Roll":

                break;
            case "CallAction":

                break;
            case "EmptyPropertySpace":

                break;
            case "Buy":

                break;
            case "Auction":

                break;
            case "Steal":

                break;
            case "ChoosePlayer":

                break;
            case "EndTurn":

                break;
            case "SettingsMenu":

                break;
            case "ExitGame":

                break;
            case "SaveGame":

                break;
            case "Bankruptcy":

                break;
            case "Confirmation":

                break;
            case "Information":

        }
        //TODO: PUT THIS SOMEWHERE
        //mutating the state to have memory of its state, useful for backwards transversal
        currentTree.setPreviousState(currentState);
        return currentState;
    }



}

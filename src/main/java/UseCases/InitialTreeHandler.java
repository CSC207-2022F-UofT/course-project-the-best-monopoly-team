package UseCases;

import Entities.GameLogicTree;
import Entities.State;

import java.util.HashMap;

/**
 * This tree handler handles the input during the initial menu
 */
public class InitialTreeHandler {
    //This array contains various states for the program which will be used for various calculations
    HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    UseCaseInteractor caseInteractor;
    public InitialTreeHandler(UseCaseInteractor caseInteractor){
        this.caseInteractor = caseInteractor;
    }

    /**
     * This method handles the input of the user in the initialization part of the program.
     * <p>
     * The player's choices are stored in the selectedOptions map for usage later.
     *
     * @param input the translated input of the user from the input interface
     */
    public State handleTree(int input){
        GameLogicTree currentTree = caseInteractor.getCurrentTree();
        State state = new State();
        state.setId(currentTree.getName());

        //deciding what to do based on node visited
        switch (currentTree.getName()){
            case "NewGame":
                //getting if the user wants to play a new game
                state.setBackEnable(true);
                selectedOptions.put("NewOrLoad",1);

                //options associated with the next node
                state.addOptions("Normal mode");
                break;
            case "ChooseGameMode":
                //getting the mode the user wants
                state.setBackEnable(true);
                selectedOptions.put(currentTree.getName(),input);

                //options associated with the next node
                for (int i = 2; i < 9; i++){
                    state.addOptions(i + " players");
                }
                break;
            case "NumberOfPlayers":
                //getting the number of players the user wants
                state.setBackEnable(true);
                selectedOptions.put(currentTree.getName(),input);

                //options associated with the next node
                state.addOptions("30 rounds");
                state.addOptions("60 rounds");
                state.addOptions("90 rounds");
                state.addOptions("no limit");
                break;
            case "GameLength":
                //getting the game length the user requested
                state.setBackEnable(true);
                selectedOptions.put(currentTree.getName(),input);

                //options associated with the next node
                state.addOptions("Yes");
                state.addOptions("No");
                break;
            case "CreateNewGame":
                //creating the game if confirmed
                if (input == 0) {
                    caseInteractor.createGame(true);
                    state = caseInteractor.getLogicInteractor().getCurrentState();
                }
                else{
                    state = caseInteractor.getInitialState();
                }
                break;
            case "LoadGame":
                //getting if the user wants to load an old one
                selectedOptions.put("NewOrLoad", 0);

                //options associated with the next node
                //TODO provide lists of saves
                break;
            case "ChooseSave":
                //getting the user's saved game choice
                selectedOptions.put(currentTree.getName(),input);

                //options associated with the next node
                state.addOptions("Yes");
                state.addOptions("No");
                break;
            case "CreateLoadedGame":
                //in "Create Loaded Game" node
                //TODO: CREATE THE GAME BY MAKING THE LOGIC INTERACTOR
                break;
        }
        currentTree.setPreviousState(state);
        return state;
    }
}

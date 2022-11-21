package UseCases;

import Entities.GameLogicTree;
import Entities.State;

import java.util.HashMap;

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
     * Usage of globalStates array:<br>
     * ------------------------------------------------------------------------------------- <br>
     * globalStates[0]: If the user is loading a game or starting a new game. Can be 1 or 0.
     * <p>
     * If the user decides to create a new game: <br>
     * globalStates[1]: An integer indicating what game mode the user wishes to load.<br>
     * globalStates[2]: An integer indicating the number of players in the game.<br>
     * globalStates[3]: An integer indicating the choice for the length of the game.<br>
     * <p>
     * If the user decides to load a past game:<br>
     * globalStates[4]: An integer indicating the choice of saved games to load<br>
     * @param input the translated input of the user from the input interface
     */
    public State handleTree(int input){
        GameLogicTree currentTree = caseInteractor.getCurrentTree();
        //deciding what to do based on node visited
        State state = new State();
        switch (currentTree.getName()){
            case "NewGame":
                state.setBackEnable(true);
                //in "New Game" node
                selectedOptions.put("NewOrLoad",1);
                state.setDescription("What mode would you like to play?");
                state.addOptions("Normal mode");
                break;
            case "ChooseGameMode":
                state.setBackEnable(true);
                //in "Choose Game mode" node
                selectedOptions.put(currentTree.getName(),input);
                state.setDescription("How many players?");
                for (int i = 2; i<9; i++){
                    state.addOptions(i + " players");
                }
                break;
            case "NumberOfPlayers":
                state.setBackEnable(true);
                //in "Number of Players" node
                selectedOptions.put(currentTree.getName(),input);
                state.setDescription("How many rounds?");
                state.addOptions("30 rounds");
                state.addOptions("60 rounds");
                state.addOptions("90 rounds");
                state.addOptions("no limit");
                break;
            case "GameLength":
                state.setBackEnable(true);
                //in "Game Length" node
                selectedOptions.put(currentTree.getName(),input);
                state.setDescription("Create the game?");
                state.addOptions("Yes");
                state.addOptions("No");
                break;
            case "CreateNewGame":
                //in "Create new Game" node
                if (input == 0) {
                    caseInteractor.loadGame(true);
                    state = caseInteractor.getLogicInteractor().getInitialState();
                }
                else{
                    caseInteractor.setCurrentTree((GameLogicTree) currentTree.getMaxParent());
                }
                break;
            case "LoadGame":
                //in "Load Game" node
                selectedOptions.put("NewOrLoad",input);
                state.setDescription("What save do you want to load");
                break;
            case "ChooseSave":
                //in "Choose Save" node
                selectedOptions.put(currentTree.getName(),input);
                state.setDescription("Confirm the load?");
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

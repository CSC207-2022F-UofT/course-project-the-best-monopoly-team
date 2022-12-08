package UseCases.InitialNodeLogic;

import Entities.GameLogicTree;
import Entities.State;
import UseCases.UseCaseInteractor;

import java.util.HashMap;

public class InitialLogic {
    //This array contains various states for the program which will be used for various calculations
    static HashMap<String, Integer> selectedOptions = new HashMap<String, Integer>();
    static UseCaseInteractor caseInteractor;
    static GameLogicTree currentTree;
    private String name;
    public static void setCaseInteractor(UseCaseInteractor interactor){
        caseInteractor = interactor;
    }

    public InitialLogic(String name){
        this.name = name;
    }
    public State beforeLogic(){
        currentTree = caseInteractor.getCurrentTree();
        State state = new State();
        state.setId(((InitialLogic)currentTree.getUseCase()).getName());
        return state;
    }

    public String getName() {
        return name;
    }

    public State afterLogic(State state){
        currentTree.setPreviousState(state);
        return state;
    }
    public HashMap<String, Integer> getSelectedOptions(){
        return selectedOptions;
    }


}

package Logic.InitialNodeLogic;

import Entities.GameLogicTree;
import Entities.State;
import UseCases.UseCaseInteractor;

import java.util.HashMap;

public class InitialLogic {
    //This array contains various states for the program which will be used for various calculations
    static HashMap<String, Integer> selectedOptions = new HashMap<>();
    static UseCaseInteractor caseInteractor;
    static GameLogicTree currentTree;
    private final String name;
    public static void setCaseInteractor(UseCaseInteractor interactor){
        caseInteractor = interactor;
    }

    public InitialLogic(String name){
        this.name = name;
    }

    /**
     * This method returns a State object before logic has been applied.
     * @return a State object before logic has been applied.
     */
    public State beforeLogic(){
        currentTree = caseInteractor.getCurrentTree();
        State state = new State();
        state.setId(((InitialLogic)currentTree.getUseCase()).getName());
        return state;
    }

    /**
     * This method returns a String stored in the name instance attribute.
     * @return a String stored in the name instance attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns a State object after logic has been applied.
     * @param state a State object representing a state before logic is applied.
     * @return a State object after logic has been applied.
     */
    public State afterLogic(State state){
        currentTree.setPreviousState(state);
        return state;
    }

    /**
     * This method returns a HashMap object stored in the selectedOptions instance attribute.
     * @return a HashMap object stored in the selectedOptions instance attribute.
     */
    public HashMap<String, Integer> getSelectedOptions(){
        return selectedOptions;
    }


}

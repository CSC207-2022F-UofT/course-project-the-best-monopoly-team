package UseCases.InitialNodeLogic;

import Entities.State;
import UseCases.NodeLogic;

import java.util.HashMap;


public class GameLength extends InitialLogic implements NodeLogic {
    HashMap<Integer, Integer> lengthMap = new HashMap<>();
    public GameLength() {
        super("Game Length");
        lengthMap.put(0,30);
        lengthMap.put(1,60);
        lengthMap.put(2,90);
    }


    @Override
    public State create_state(int input) {
        State state = beforeLogic();
        //getting the game length the user requested
        state.setBackEnable(true);
        if (input != 3){
            selectedOptions.put("GameLength", lengthMap.get(input) * getSelectedOptions().get("NumOfPlayers"));
        }
        else{
            selectedOptions.put("GameLength",-1);
        }

        //options associated with the next node
        state.addOptions("Yes");
        state.addOptions("No");
        return afterLogic(state);
    }
}

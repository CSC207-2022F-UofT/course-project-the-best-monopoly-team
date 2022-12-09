package Interactors;

import java.util.HashMap;

/**
 * Class that handles the input for the Display
 */
public class GameDisplayInputInteractor {
    /**
     * InstanceVar ButtonMap: A mapping between the button pressed and the
     * input that should be passed into the InputInteractor
     */
    private final HashMap<String, Integer> ButtonMap;

    /**
     * Constructor that sets up the fixed Mapping
     */
    public GameDisplayInputInteractor(){
        this.ButtonMap = new HashMap<>();
        for (int i = 0; i < 20; i++){
            this.ButtonMap.put("B" + String.valueOf(i + 1), i);
        }
    }

    /**
     * Function that returns the integer input value associated
     * with the button pushed
     * @param buttonAction: the String of the button pressed
     * @return the respective integer for the String inputted
     */
    public Integer getInputMapValue(String buttonAction){
        return this.ButtonMap.get(buttonAction);
    }


}

package Entities;

import java.util.HashMap;

/**
 * Output Entity stores all the output data that is to be presented to the user each move
 */
public class Output {
    /**
     * InstanceVar stateOutput: A hashmap that contains predefinied outputs for specific states
     * InstanceVar finalOutput: the string object containing the cumulative output
     */
    private HashMap<String, String> stateOutput;
    private String finalOutput;

    /**
     * The constructor for this class, initalizes the mapping between the state and
     * its ouput and the final output
     */
    public Output(){
        this.stateOutput = new HashMap<>();
        this.finalOutput = "";
    }

    /**
     * This function takes the initial states and their respective outputs that are parsed
     * from a different class
     * @param initStateHash: the statehash that is parsed in a different class
     */
    public void setInitStateHash(HashMap<String, String> initStateHash){
        this.stateOutput = initStateHash;
    }

    /**
     * Function that returns the value from the key-value pair of the hashmap
     * @param state: the key for the state that needs to be accessed
     * @return the value to be used in the output from the key used
     */
    public String getStateOutput(String state){
        return this.stateOutput.get(state);
    }

    /**
     * Function that modifies some key-value pairs as some outputs change based on state logic
     * @param state: the current state
     * @param output: the new output obtained from processing logic
     */
    public void modifyStateOutput(String state, String output){
        this.stateOutput.replace(state, output);
    }
}
package Interactors;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

/**
 * This function is used to create the initial state of the state hashmap
 * the initial outputs are all read from the file states.txt
 */
public class StateOutputReader {
    private HashMap<String, String> stateMap;

    public StateOutputReader(){
        this.stateMap = new HashMap<>();
    }

    /**
     * This is a function that reads the state file and creats the intial hash
     * throws an exception if the file is not found
     */
    public void initStateHash(){
        try {
            File stateFile = new File("src/initialoutputs/states.txt");
            Scanner stateFileReader = new Scanner(stateFile);
            while (stateFileReader.hasNextLine()){
                String stateString = stateFileReader.nextLine();
                String[] stateMapping = stateString.split(",", 2);
                this.stateMap.put(stateMapping[0], stateMapping[1]);
            }

        } catch (FileNotFoundException e){
            System.out.println("The file is not found");
            e.printStackTrace();
        }
    }

    /**
     * Function that outputs the state hash
     * @return the state hashmap
     */
    public HashMap<String, String> getStateMap(){
        return this.stateMap;
    }
}

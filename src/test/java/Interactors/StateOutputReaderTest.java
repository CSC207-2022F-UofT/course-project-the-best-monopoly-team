package Interactors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class StateOutputReaderTest {

    @Test
    public void fileReadTest(){
        StateOutputReader fileReader = new StateOutputReader();
        fileReader.initStateHash();
        HashMap<String, String> outputs = fileReader.getStateMap();

        Assertions.assertEquals(outputs.get("New Game"), "What mode would you like to play?");
        Assertions.assertEquals(outputs.get("Already Rolled"), "You already rolled this round!");
        Assertions.assertEquals(outputs.get("Settings Menu"), "Welcome to the settings menu!");
    }
}

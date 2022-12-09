package Interactors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameDisplayInputInteractorTest {

    @Test
    public void getInputMapValue() {
        GameDisplayInputInteractor inputDisplay = new GameDisplayInputInteractor();

        int result = inputDisplay.getInputMapValue("B3");
        int result1 = inputDisplay.getInputMapValue("B13");

        Assertions.assertEquals(2, result);
        Assertions.assertEquals(12, result1);

    }
}

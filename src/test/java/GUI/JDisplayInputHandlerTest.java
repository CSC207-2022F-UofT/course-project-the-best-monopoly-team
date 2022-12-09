package GUI;

import org.junit.jupiter.api.Test;

public class JDisplayInputHandlerTest {

    @Test
    public void getInputMapValue() {
        JDisplayInputHandler inputDisplay = new JDisplayInputHandler();

        int result = inputDisplay.getInputMapValue("B3");
        int result1 = inputDisplay.getInputMapValue("B13");

        assert result == 2;
        assert result1 == 12;

    }
}

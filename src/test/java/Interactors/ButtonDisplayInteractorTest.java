package Interactors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Buttons.ButtonFactory;

public class ButtonDisplayInteractorTest {

    @Test
    public void testGetButtons() {
        ButtonFactory buttonFactory = new ButtonFactory();
        buttonFactory.addButtons();

        ButtonDisplayInteractor buttonDisplay = new ButtonDisplayInteractor();

        Assertions.assertEquals(buttonFactory.getButtons(), buttonDisplay.getButtons());
    }
}

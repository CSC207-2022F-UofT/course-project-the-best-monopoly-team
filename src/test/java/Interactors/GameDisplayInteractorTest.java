package Interactors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameDisplayInteractorTest {

    @Test
    public void testSetOutputs() {
        GameDisplayInteractor gameDisplay = new GameDisplayInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");
        String outputText = "Hi";

        gameDisplay.setOutputs(options, outputText);
        String output = (String.valueOf(gameDisplay.getLabelSegments().getTextSegment().getText()));

        Assertions.assertEquals(options, gameDisplay.getLabelSegments().getOptions());
        Assertions.assertEquals(("<HTML>" + outputText + "<HTML>"), output);
    }

    @Test
    public void testClearLabels() {
        GameDisplayInteractor gameDisplay = new GameDisplayInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");
        String outputText = "Hi";

        gameDisplay.setOutputs(options, outputText);
        gameDisplay.clearLabels();

        ArrayList<String> optionsLabels = gameDisplay.getLabelSegments().getOptions();

        Assertions.assertEquals(0, optionsLabels.size());
    }
}

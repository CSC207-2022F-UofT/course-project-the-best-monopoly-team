package Interactors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplayOutputInteractorTest {

    @Test
    public void testSetOptions() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");

        displayOutput.setOptions(options);
        Assertions.assertEquals(options, displayOutput.getOptions());
    }

    @Test
    public void testClearOptions() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");

        displayOutput.setOptions(options);
        displayOutput.clearOptions();

        Assertions.assertEquals(displayOutput.getOptions(), new ArrayList<>());
    }

    @Test
    public void testGetOptionSegment() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");

        displayOutput.setOptions(options);
        displayOutput.createOptionSegment();

        Assertions.assertEquals(options.get(1), displayOutput.getOptionSegment().getText());
    }

    @Test
    public void testClearOptionSegment() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");

        displayOutput.setOptions(options);
        displayOutput.createOptionSegment();
        displayOutput.clearOptionSegment();
    }

    @Test
    public void testGetTextSegment() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        String input = "Hi";
        displayOutput.createTextSegment(input);

        String output = displayOutput.getTextSegment().getText();
        Assertions.assertEquals(("<HTML>" + input + "<HTML>"), output);
    }

    @Test
    public void testClearTextSegment() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        String input = "Hi";
        displayOutput.createTextSegment(input);
        displayOutput.clearTextSegment();

        Component[] comps = displayOutput.getTextSegment().getComponents();
        Assertions.assertEquals(0, comps.length);
    }
}

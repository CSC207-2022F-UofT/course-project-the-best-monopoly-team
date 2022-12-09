package Interactors;

//import org.junit.jupiter.api.Test;
import Interactors.GameDisplayOutputInteractor;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

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
        assertEquals(options, displayOutput.getOptions());
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

        assertEquals(displayOutput.getOptions(), new ArrayList<>());
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
        assertEquals(("<HTML>" + input + "<HTML>"), output);
    }

    @Test
    public void testClearTextSegment() {
        GameDisplayOutputInteractor displayOutput = new GameDisplayOutputInteractor();

        String input = "Hi";
        displayOutput.createTextSegment(input);
        displayOutput.clearTextSegment();

        Component[] comps = displayOutput.getTextSegment().getComponents();
        assertEquals(0, comps.length);
    }
}

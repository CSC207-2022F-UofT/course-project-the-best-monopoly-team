package GUI;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JDisplayOutputHandlerTest {

    @Test
    public void testSetOptions() {
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

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
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

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
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

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
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

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
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

        String input = "Hi";
        displayOutput.createTextSegment(input);

        String output = displayOutput.getTextSegment().getText();
        assertEquals(("<HTML>" + input + "<HTML>"), output);
    }

    @Test
    public void testClearTextSegment() {
        JDisplayOutputHandler displayOutput = new JDisplayOutputHandler();

        String input = "Hi";
        displayOutput.createTextSegment(input);
        displayOutput.clearTextSegment();

        String output = displayOutput.getTextSegment().getText();
        assertEquals("", output);
    }
}

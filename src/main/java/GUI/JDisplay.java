package GUI;

import Entities.GameScreen;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * JDisplay is a class that creates the Display to show the user
 */
public class JDisplay {

    /**
     * InstanceVar gameFrame: the Frame of the monopoly game
     * InstanceVar labelSegments: The OutputHandler for the Display
     * InstanceVar inputHandler: The InputHandler for the Display
     * InstanceVar selectedOutput: The String of the button that
     * is clicked by the user
     */
    private GameScreen gameFrame;
    private JDisplayOutputHandler labelSegments;
    private JDisplayInputHandler inputHandler;
    private String selectedOutput;

    /**
     * The constructor for this class, creates the frame and sets the permanent monopoly
     * background and fixes all its size and other properties
     */
    public JDisplay(){
        this.gameFrame = new GameScreen();
        this.labelSegments = new JDisplayOutputHandler();
        this.inputHandler = new JDisplayInputHandler();
        this.selectedOutput = "";
    }

    /**
     * Function that adds the outputs to the Display
     * @param options: The Arraylist of options that the user has
     * @param outputText: The String of Text shown to the user to
     *                  explain the current context of the game
     */
    public void setOutputs(ArrayList<String> options, String outputText){
        this.labelSegments.setOptions(options);
        this.labelSegments.createOptionSegment();
        this.labelSegments.createTextSegment(outputText);
        this.gameFrame.add(this.labelSegments.getTextSegment());
        this.gameFrame.add(this.labelSegments.getOptionSegment());
        this.labelSegments.drawSegments();
    }

    /**
     * Function to get the input from the user on the current turn
     * @return  if the user has clicked on the of buttons
     */
    public boolean waitForInput(){
        HashMap<String, Integer> buttonClicks = this.labelSegments.getButtonList();
        for (String button: buttonClicks.keySet()){
            if (buttonClicks.get(button) == 1){
                this.selectedOutput = button;
                this.labelSegments.updateButtonList();
                return true;
            }
        }
        return false;
    }

    /**
     * Function to clear the display outputs to create space for the next set of
     * outputs
     */
    public void clearLabels(){
        this.labelSegments.clearOptions();
        this.labelSegments.clearOptionSegment();
        this.labelSegments.clearTextSegment();
    }

    /**
     * Function that displays the screen to the user, Program ends when window
     * is closed
     */
    public void displayScreen(){
        this.gameFrame.display();
    }

    /**
     * Refresh the screen for the next set of outputs
     */
    public void refreshScreen(){
        clearLabels();
        this.gameFrame.remove(this.labelSegments.getTextSegment());
        this.gameFrame.remove(this.labelSegments.getOptionSegment());
        this.gameFrame.refresh();
    }

    /**
     * @return the input based on the button clicked
     */
    public Integer getInput(){
        return this.inputHandler.getInputMapValue(this.selectedOutput);
    }

}

package GUI;

import Entities.Description;
import Entities.Options;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that handles the output for the display
 */
public class JDisplayOutputHandler {
    /**
     * InstanceVar description: the section for the context shown to the user
     * InstanceVar optionSegment: the section for all the option buttons
     *
     * InstanceVar options: an ArrayList of all the options on this state
     * InstanceVar buttonDisplayHandler: the handler of the buttons on the display
     */
    private Description description;
    private Options optionSegment;
    private ArrayList<String> options;

    private ButtonDisplayHandler buttonDisplayHandler;

    /**
     * The constructor for this class
     */
    public JDisplayOutputHandler(){
        this.optionSegment = new Options();
        this.description = new Description();
        this.options = new ArrayList<>();
        this.buttonDisplayHandler = new ButtonDisplayHandler();
    }

    /**
     * Function to set options for this state
     * @param options: An ArrayList of the options for the current state
     */
    public void setOptions(ArrayList<String> options){
        this.options = options;
    }

    /**
     * Function to clear the ArrayList for the next states options
     */
    public void clearOptions(){
        this.options.clear();
    }

    /**
     * Function to set up the option segment through the creation of all the option
     * buttons
     */
    public void createOptionSegment(){
        this.buttonDisplayHandler.setButtonFactory(this.options);
        for (JButton button: this.buttonDisplayHandler.getButtons()){
            this.optionSegment.add(button);
        }
    }

    /**
     * Function to return the option segment
     * @return the JLabel that contains all the option buttons for the current state
     */
    public JLabel getOptionSegment(){
        return this.optionSegment.getOptions();
    }

    /**
     * Clear the option segment for the next state
     */
    public void clearOptionSegment(){
        this.optionSegment.clearOptions();
    }

    /**
     * Create the text segment to show the context to the user
     * @param textOutput the context String
     */
    public void createTextSegment(String textOutput) {
        this.description.setDescription(textOutput);
    }

    /**
     * Function to return the text segment
     * @return the JLabel that contains the context of the state
     */
    public JLabel getTextSegment(){
        return this.description.getDescription();
    }

    /**
     * Function that clears the text segment for the next state
     */
    public void clearTextSegment(){
        this.description.clear();
    }

    /**
     * Sets the positions for the segments with respect to the display
     */
    public void drawSegments(){
        this.description.setDescriptionBounds();
        this.optionSegment.setOptionsBounds();
    }

    /**
     * function to return the mapping between the buttons and how many times they are pressed
     * @return the mapping
     */
    public HashMap<String, Integer> getButtonList(){
        return this.buttonDisplayHandler.getButtonList();
    }

    /**
     * Function to update the current status of how many times each button was pressed
     */
    public void updateButtonList(){
        this.buttonDisplayHandler.updateClicks();
    }

    /**
     * Clear the mapping between the buttons and number of times pressed for the next state
     */
    public void clearInput(){
        this.buttonDisplayHandler.resetInputs();
    }
}

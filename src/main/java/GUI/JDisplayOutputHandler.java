package GUI;

import Entities.Description;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that handles the output for the display
 */
public class JDisplayOutputHandler {
    /**
     * InstanceVar textSegment: the section for the text that explains context
     * InstanceVar optionSegment: the section for all the option buttons
     * InstanceVar buttonList: a mapping between the buttons and how many times
     * they were pressed
     * InstanceVar options: an ArrayList of all the options on this state
     * InstanceVar buttons: the ButtonFactory that creates the buttons for each state
     */
    private Description description;
    private JLabel optionSegment;
    private HashMap<String, Integer> buttonList;
    private ArrayList<String> options;
    private ButtonFactory buttons;

    /**
     * The constructor for this class
     */
    public JDisplayOutputHandler(){
        this.optionSegment = new JLabel();
        this.optionSegment.setLayout(new FlowLayout());
        this.description = new Description();
        this.buttonList = new HashMap<>();
        this.options = new ArrayList<>();
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
        this.buttons = new ButtonFactory(this.options);
        ArrayList<JButton> optionButtons = this.buttons.getButtons();
        for (JButton button: optionButtons){
            this.optionSegment.add(button);
        }
        this.buttonList = this.buttons.getActionPerformed();
    }

    /**
     * Function to return the option segment
     * @return the JLabel that contains all the option buttons for the current state
     */
    public JLabel getOptionSegment(){
        return this.optionSegment;
    }

    /**
     * Clear the option segment for the next state
     */
    public void clearOptionSegment(){
        this.optionSegment.removeAll();
        this.optionSegment.setLayout(new FlowLayout());
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
        this.optionSegment.setBounds(600, 250, 300, 400);
        this.optionSegment.repaint();
    }

    /**
     * function to return the mapping between the buttons and how many times they are pressed
     * @return the mapping
     */
    public HashMap<String, Integer> getButtonList(){
        return this.buttonList;
    }

    /**
     * Function to update the current status of how many times each button was pressed
     */
    public void updateButtonList(){
        this.buttonList = this.buttons.getActionPerformed();
    }
}

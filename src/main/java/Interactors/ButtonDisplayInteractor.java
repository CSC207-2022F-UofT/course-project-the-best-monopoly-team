package Interactors;

import Buttons.ButtonFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that handles button creation and input on the display
 */
public class ButtonDisplayInteractor {
    /**
     * InstanceVar buttonFactory: the ButtonFactory that creates all the buttons
     * InstanceVar buttonList: the mapping between the buttons on the current state and how many times they are pressed
     */
    private ButtonFactory buttonFactory;
    private HashMap<String, Integer> buttonList;

    /**
     * Constructor for this class
     */
    public ButtonDisplayInteractor() {
        this.buttonFactory = new ButtonFactory();
        this.buttonList = new HashMap<>();
    }

    /**
     * Add the strings that need to be converted into buttons
     * @param options: the strings options to be converted to buttons
     */
    public void setButtonFactory(ArrayList<String> options) {
        this.buttonFactory.addStrings(options);
    }

    /**
     * returns all the buttons and sets up the hashmap
     * @return the buttons
     */
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> temp = this.buttonFactory.getButtons();
        this.buttonList = this.buttonFactory.getActionPerformed();
        return temp;
    }

    /**
     * Return the hashmap of buttons to times pressed
     * @return the hashmap
     */
    public HashMap<String, Integer> getButtonList(){
        return this.buttonList;
    }

    /**
     * Update the Hashmap to keep track of inputs
     */
    public void updateClicks(){
        this.buttonList = this.buttonFactory.getActionPerformed();
    }

    /**
     * Reset the Hashmap for the next state
     */
    public void resetInputs() {
        this.buttonList.clear();
    }
}

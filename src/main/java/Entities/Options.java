package Entities;

import javax.swing.*;
import java.awt.*;

/**
 * This class handles the section on the GUI for all the options the user has at a given state
 */
public class Options {
    /**
     * InstanceVar: the JLabel that contains all the options the user has
     */
    private final JLabel options;

    /**
     * The constructor for this class that sets the layout
     */
    public Options() {
        this.options = new JLabel();
        this.options.setLayout(new FlowLayout());
    }

    /**
     * Function to add a component to the JLabel
     * @param component: the component to be added
     */
    public void add(Component component) {
        this.options.add(component);
    }

    /**
     * Function to get the Label
     * @return the label
     */
    public JLabel getOptions() {
        return options;
    }

    /**
     * Function to clear the label for the next set of outputs
     */
    public void clearOptions() {
        this.options.removeAll();
        this.options.setLayout(new FlowLayout());
    }

    /**
     * Set the JLabel relative to the screen
     */
    public void setOptionsBounds() {
        this.options.setBounds(600, 250, 300, 400);
        this.options.repaint();
    }
}

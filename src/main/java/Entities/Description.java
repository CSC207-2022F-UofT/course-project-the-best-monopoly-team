package Entities;

import javax.swing.*;
import java.awt.*;

/**
 * Class that presents the description for the context to the user
 */
public class Description {
    /**
     * InstanceVar description: the JLabel that shows the context to the user
     */
    private JLabel description;

    /**
     * The constructor for this class that sets the layout
     */
    public Description() {
        this.description = new JLabel();
        this.description.setLayout(new FlowLayout());
    }

    /**
     * Sets the text in the Label to show to the use
     * @param textOutput: the text to be put into the Label
     */
    public void setDescription(String textOutput) {
        this.description.setText("<HTML>" + textOutput + "<HTML>");
    }

    /**
     * Function to get the context segment
     * @return the JLabel Segment
     */
    public JLabel getDescription() {
        return this.description;
    }

    /**
     * Reset the JLabel
     */
    public void clear(){
        this.description.setText("");
        this.description.removeAll();
        this.description.setLayout(new FlowLayout());
    }

    /**
     * Set the bounds of the JLabel
     */
    public void setDescriptionBounds(){
        this.description.setBounds(625, 25, 250, 225);
        this.description.repaint();
    }
}

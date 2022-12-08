package Entities;

import javax.swing.*;
import java.awt.*;

public class Description {
    private JLabel description;

    public Description() {
        this.description = new JLabel();
        this.description.setLayout(new FlowLayout());
    }

    public void setDescription(String textOutput) {
        this.description.setText("<HTML>" + textOutput + "<HTML>");
    }
    public JLabel getDescription() {
        return this.description;
    }
    public void clear(){
        this.description.removeAll();
        this.description.setLayout(new FlowLayout());
    }
    public void setDescriptionBounds(){
        this.description.setBounds(625, 25, 250, 225);
        this.description.repaint();
    }
}

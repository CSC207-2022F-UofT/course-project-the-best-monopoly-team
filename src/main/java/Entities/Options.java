package Entities;

import javax.swing.*;
import java.awt.*;

public class Options {
    private JLabel options;

    public Options() {
        this.options = new JLabel();
        this.options.setLayout(new FlowLayout());
    }
    public void add(Component component) {
        this.options.add(component);
    }

    public JLabel getOptions() {
        return options;
    }
    public void clearOptions() {
        this.options.removeAll();
        this.options.setLayout(new FlowLayout());
    }
    public void setOptionsBounds() {
        this.options.setBounds(600, 250, 300, 400);
        this.options.repaint();
    }
}

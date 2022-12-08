package GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ButtonDisplayHandler {
    private ButtonFactory buttonFactory;
    private HashMap<String, Integer> buttonList;

    public ButtonDisplayHandler() {
        this.buttonFactory = new ButtonFactory();
        this.buttonList = new HashMap<>();
    }
    public void setButtonFactory(ArrayList<String> options) {
        this.buttonFactory.addStrings(options);
    }
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> temp = this.buttonFactory.getButtons();
        this.buttonList = this.buttonFactory.getActionPerformed();
        return temp;
    }

    public HashMap<String, Integer> getButtonList(){
        return this.buttonList;
    }
    public void updateClicks(){
        this.buttonList = this.buttonFactory.getActionPerformed();
    }

    public void resetInputs() {
        this.buttonList.clear();
    }
}

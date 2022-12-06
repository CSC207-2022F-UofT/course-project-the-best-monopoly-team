package GUI;

import ButtonMappings.*;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

/**
 * ButtonFactory is a class that creates all the button instances that need to be
 * displayed to the screen with their respective action handling
 */
public class ButtonFactory {
    /**
     * InstanceVar buttonStrings: an ArrayList of Strings that contains all the output
     * strings that need to be displayed on each button
     * InstanceVar actionPerformed: A Mapping between the button string and the number
     * of times the button was pressed
     * InstanceVar buttonHandlers: an ArrayList of ButtonHandlers, which contains all the
     * different Button'x' objects to determine user input
     */
    private ArrayList<String> buttonStrings;
    private HashMap<String, Integer> actionPerformed;
    private ArrayList<ButtonHandler> buttonHandlers;

    /**
     * Constructor for this class
     * @param options: The options the user has on this turn
     */
    public ButtonFactory (ArrayList<String> options){
        this.buttonStrings = options;
        this.actionPerformed = new HashMap<>();
        this.buttonHandlers = new ArrayList<>();
        addButtons();
    }

    /**
     * Function that creates all the ButtonHandlers and adds them to the Arraylist
     * of ButtonHandlers
     */
    public void addButtons(){
        Button1 b1 = new Button1();
        this.buttonHandlers.add(b1);
        Button2 b2 = new Button2();
        this.buttonHandlers.add(b2);
        Button3 b3 = new Button3();
        this.buttonHandlers.add(b3);
        Button4 b4 = new Button4();
        this.buttonHandlers.add(b4);
        Button5 b5 = new Button5();
        this.buttonHandlers.add(b5);
        Button6 b6 = new Button6();
        this.buttonHandlers.add(b6);
        Button7 b7 = new Button7();
        this.buttonHandlers.add(b7);
        Button8 b8 = new Button8();
        this.buttonHandlers.add(b8);
        Button9 b9 = new Button9();
        this.buttonHandlers.add(b9);
        Button10 b10 = new Button10();
        this.buttonHandlers.add(b10);
        Button11 b11 = new Button11();
        this.buttonHandlers.add(b11);
        Button12 b12 = new Button12();
        this.buttonHandlers.add(b12);
        Button13 b13 = new Button13();
        this.buttonHandlers.add(b13);
        Button14 b14 = new Button14();
        this.buttonHandlers.add(b14);
        Button15 b15 = new Button15();
        this.buttonHandlers.add(b15);
        Button16 b16 = new Button16();
        this.buttonHandlers.add(b16);
    }

    /**
     * Function that creates all the buttons, assigns their action listeners
     * @return the arraylist of JButtons
     */
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> returnedButtons = new ArrayList<>();

        for (int i = 0; i < this.buttonStrings.size(); i++){
            JButton temp = new JButton(this.buttonStrings.get(i));
            int finalI = i;
            this.actionPerformed.put(this.buttonHandlers.get(i).pressedButton(), 0);
            temp.addActionListener(e -> this.actionPerformed.replace(this.buttonHandlers.get(finalI).pressedButton(), 1));
            returnedButtons.add(temp);
        }

        return returnedButtons;
    }

    /**
     * Function that returns the mapping of the buttons and how many times they are pressed
     * @return the hashmap of the buttons and number of times pressed
     */
    public HashMap<String, Integer> getActionPerformed() {
        return this.actionPerformed;
    }
}

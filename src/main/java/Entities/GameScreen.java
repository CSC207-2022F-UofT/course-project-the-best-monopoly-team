package Entities;

import javax.swing.*;
import java.awt.*;

/**
 * Class to keep track of the frame
 */
public class GameScreen {
    /**
     * InstanceVar gameFrame: JFrame that contains all the contents of the game
     */
    private final JFrame gameFrame;
    private final String imageFile = "src/initialoutputs/monopolyboard.jpg";

    /**
     * Constructor that configures the JFrame
     */
    public GameScreen(){
        this.gameFrame = new JFrame("Monopoly++");
        this.gameFrame.setLayout(null);
        this.gameFrame.setResizable(false);
        this.gameFrame.setSize(900, 630);
        setBackgroundImage(imageFile);
    }

    /**
     * Helper method that sets up the background image
     * @param imageLink the image to be used in the input
     */
    public void setBackgroundImage(String imageLink){
        ImageIcon board = new ImageIcon(imageLink);
        Image temp = board.getImage();
        Image temp2 = temp.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        board = new ImageIcon(temp2);
        JLabel boardLabel = new JLabel(board);
        this.gameFrame.add(boardLabel);
        boardLabel.setBounds(0, 0, 600, 600);
        boardLabel.repaint();
    }

    /**
     * Add a component to the JFrame
     * @param component: the thing to be added
     */
    public void add(Component component){
        this.gameFrame.add(component);
    }

    /**
     * Remove a component from the Frame
     * @param component: the thing to be removed
     */
    public void remove(Component component){
        this.gameFrame.remove(component);
    }

    /**
     * Function to show the frame and set it to close on exit
     */
    public void display(){
        this.gameFrame.setVisible(true);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Refresh the screen
     */
    public void refresh(){
        this.gameFrame.repaint();
    }

}

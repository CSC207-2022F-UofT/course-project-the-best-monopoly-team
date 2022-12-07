package Entities;

import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private JFrame gameFrame;

    public GameScreen(){
        this.gameFrame = new JFrame("Monopoly++");
        this.gameFrame.setLayout(null);
        this.gameFrame.setResizable(false);
        this.gameFrame.setSize(900, 630);
        setBackgroundImage("src/main/java/GUI/monopolyboard.jpg");
    }

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
    public void add(Component component){
        this.gameFrame.add(component);
    }
    public void remove(Component component){
        this.gameFrame.remove(component);
    }
    public void display(){
        this.gameFrame.setVisible(true);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void refresh(){
        this.gameFrame.repaint();
    }

}

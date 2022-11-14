package Entities;

import java.util.Scanner;

public class PresenterDisplay {
    private boolean isOver;

    public PresenterDisplay(){
        this.isOver = false;
    }

    public void playGame(){
        IOController inputOutput = new IOController();
        Scanner userIn = new Scanner(System.in);
        while (!this.isOver){
            System.out.println(inputOutput.presentOutput());
            int choice = userIn.nextInt();
            inputOutput.setInput(choice);
            finishGame();
        }
    }
    public void finishGame(){
        this.isOver = true;
    }
}

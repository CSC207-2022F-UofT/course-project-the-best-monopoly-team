package Main;
import Interactors.IOController;

import java.util.Scanner;

public class PresenterDisplay {
    private boolean isOver;

    public PresenterDisplay(){
        this.isOver = false;
    }

    public void playGame(){
        IOController controller = new IOController();
        controller.connectLogic();
        Scanner userIn = new Scanner(System.in);
        while (!this.isOver){
            System.out.println(controller.presentOutput());
            int choice = userIn.nextInt();
            controller.setInput(choice);
            controller.connectLogic();
//            finishGame();
        }
    }
    public void finishGame(){
        this.isOver = true;
    }
}

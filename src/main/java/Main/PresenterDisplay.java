package Main;
import Interactors.IOController;

import java.io.File;
import java.util.Scanner;

public class PresenterDisplay {
    private boolean isOver;

    public PresenterDisplay(){
        this.isOver = false;
    }

    public void playGame(File file){
        IOController controller = new IOController(file);
        Scanner userIn = new Scanner(System.in);
        controller.displayOptions();
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

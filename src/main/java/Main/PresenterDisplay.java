package Main;
import Interactors.IOController;

import java.util.Scanner;

/**
 * PresenterDisplay is a class that runs the game loop and presents the options of each turn to each
 * player in the game.
 **/
public class PresenterDisplay {
    /**
     * InstanceVar isOver: variable to check if the game is over
     **/
    private static boolean isOver;

    /**
     * This is the constructor for the PresenterDisplay Class
     **/
    public PresenterDisplay(){
        isOver = false;
    }

    /**
     * Function that runs the game loop by getting game data from the IOController and presenting that to the
     * user as their options on each turn, getting the user's input and sending that back to the IOController
     * to further handle state changes based on their option choice.
     **/
    public void playGame(){
        IOController controller = new IOController();
        controller.displayOptions();
        Scanner userIn = new Scanner(System.in);
        // TODO add sanitation for user input so make sure it is one of the options in present output
        while (!isOver){
            System.out.println(controller.presentOutput());
            int choice = userIn.nextInt();
            controller.setInput(choice);
            controller.connectLogic();
            // TODO Create the ability for game to end by calling finishGame()
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Static function to update that the game is over to exit the game loop
     */
    public static void finishGame(){
        isOver = true;
    }
}

package UseCases;
import Interactors.InputInteractor;
import Interactors.OutputInteractor;
import Persistence.LoadFile;

import java.io.File;
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
     * Function that runs the game loop by getting game data from the OutputInteractor and presenting that to the
     * user as their options on each turn, getting the user's input and sending that to the InputInteractor to get the
     * updated State, and sending that updated State to the OutputInteractor to present the user with more options until
     * the game ends
     **/
    public void playGame(File file){
        UseCaseInteractor interactor = new UseCaseInteractor(new LoadFile(file));
        InputInteractor inputControl = new InputInteractor(interactor);
        OutputInteractor outputControl = new OutputInteractor(interactor);
        Scanner userIn = new Scanner(System.in);
        outputControl.setFinalOutput();
        while (!this.isOver){
            System.out.println(outputControl.getOutput());
            int choice = userIn.nextInt();
            inputControl.getChoice(choice);
            outputControl.updateState(inputControl.getUpdatedState());
            outputControl.setFinalOutput();
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

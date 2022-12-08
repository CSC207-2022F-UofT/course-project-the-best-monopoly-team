package UseCases;
import Interactors.InputInteractor;
import Interactors.OutputInteractor;
import Persistence.LoadFile;
import Persistence.SaveFile;

import java.io.File;
import java.util.Scanner;

/**
 * UseCases.PresenterDisplay is a class that runs the game loop and presents the options of each turn to each
 * player in the game.
 **/
public class PresenterDisplay {
    /**
     * InstanceVar isOver: variable to check if the game is over
     **/
    private static boolean isOver;

    /**
     * This is the constructor for the UseCases.PresenterDisplay Class
     **/
    public PresenterDisplay(){
        isOver = false;
    }


    /**
     * Function that runs the game loop by getting game data from the IOController and presenting that to the
     * user as their options on each turn, getting the user's input and sending that back to the IOController
     * to further handle state changes based on their option choice.
     **/
    public void playGame(File file){
        UseCaseInteractor interactor = new UseCaseInteractor(new LoadFile(file), new SaveFile(file));
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

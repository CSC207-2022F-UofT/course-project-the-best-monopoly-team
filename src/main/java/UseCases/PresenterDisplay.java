package UseCases;
import Interactors.InputInteractor;
import Interactors.OutputInteractor;
import Persistence.LoadFile;

import GUI.JDisplay;
import java.io.File;

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
     * Function that runs the game loop by getting game data from the OutputInteractor and presenting that to the
     * user as their options on each turn through Conversion in the JDisplay,
     *  and then getting the user's input and sending that back to the InputInteractor
     * to further handle state changes based on their option choice.
     **/
    public void playGame(File file){
        UseCaseInteractor interactor = new UseCaseInteractor(new LoadFile(file));
        InputInteractor inputControl = new InputInteractor(interactor);
        OutputInteractor outputControl = new OutputInteractor(interactor);
        JDisplay gameFrame = new JDisplay();

        gameFrame.addLabelSegments(outputControl.getStateOptions(), outputControl.getOutputMessage());
        gameFrame.displayScreen();
        while (!isOver){
            boolean didInput = false;
            while (!didInput){
                didInput = gameFrame.waitForInput();
            }
            inputControl.getChoice(gameFrame.getInput());
            outputControl.updateState(inputControl.getUpdatedState());
            gameFrame.clearLabels();
            gameFrame.refreshScreen();
            gameFrame.addLabelSegments(outputControl.getStateOptions(), outputControl.getOutputMessage());
        }

    }

    /**
     * Static function to update that the game is over to exit the game loop
     */
    public static void finishGame(){
        isOver = true;
    }
}

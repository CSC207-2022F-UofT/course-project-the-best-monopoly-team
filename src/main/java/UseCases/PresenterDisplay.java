package UseCases;
import Interactors.InputInteractor;
import Interactors.OutputInteractor;
import Persistence.LoadFile;
import Persistence.SaveFile;

import Interactors.GameDisplayInteractor;
import java.io.File;

/**
 * UseCases.PresenterDisplay is a class that runs the game loop and presents the options of each turn to each
 * player in the game.
 **/
public class PresenterDisplay {

    /**
     * Function that runs the game loop by getting game data from the OutputInteractor and presenting that to the
     * user as their options on each turn through Conversion in the GameDisplayInteractor,
     *  and then getting the user's input and sending that back to the InputInteractor
     * to further handle state changes based on their option choice.
     **/
    boolean runGame = true;

    /**
     * Method to end the game.
     */
    public void endGame(){
        runGame = false;
    }
    public void playGame(File file){
        UseCaseInteractor interactor = new UseCaseInteractor(new LoadFile(file), new SaveFile(file));
        InputInteractor inputControl = new InputInteractor(interactor);
        OutputInteractor outputControl = new OutputInteractor(interactor);
        GameDisplayInteractor gameFrame = new GameDisplayInteractor();

        gameFrame.setOutputs(outputControl.getStateOptions(), outputControl.getOutputMessage());
        gameFrame.displayScreen();
         while (runGame){
             try {
                 boolean didInput = false;
                 while (!didInput) {
                     didInput = gameFrame.waitForInput();
                 }
                 inputControl.getChoice(gameFrame.getInput());
                 outputControl.updateState(inputControl.getUpdatedState());
                 gameFrame.refreshScreen();
                 gameFrame.setOutputs(outputControl.getStateOptions(), outputControl.getOutputMessage());
             }
             catch (Exception e){
                 System.out.println(e.getMessage());
             }
        }
    }
}

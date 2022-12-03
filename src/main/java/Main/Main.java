package Main;

import UseCases.PresenterDisplay;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PresenterDisplay display = new PresenterDisplay();
        //TODO GIVE A CORRECT FILEPATH
        File file = new File("");
        display.playGame(file);
    }
}


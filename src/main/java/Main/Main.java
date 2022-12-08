package Main;

import UseCases.PresenterDisplay;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PresenterDisplay display = new PresenterDisplay();
        File file = new File("src/gameData/");
        display.playGame(file);
    }
}


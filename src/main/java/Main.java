import UseCases.PresenterDisplay;
import java.io.File;

public class Main {
    private final static String persistenceFilePath = "src/gameSaves/";

    /**
     * Entry point to the application
     */
    public static void main(String[] args) {
        PresenterDisplay display = new PresenterDisplay();
        File file = new File(persistenceFilePath);
        display.playGame(file);
    }
}


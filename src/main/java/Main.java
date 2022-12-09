import UseCases.PresenterDisplay;
import java.io.File;
import java.io.IOException;

public class Main {
    private final static String persistenceFilePath = "src/gameSaves/";

    public static void main(String[] args) throws IOException {
        PresenterDisplay display = new PresenterDisplay();
        File file = new File(persistenceFilePath);
        display.playGame(file);
    }
}


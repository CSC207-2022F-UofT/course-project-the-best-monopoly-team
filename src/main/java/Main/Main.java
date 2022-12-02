package Main;

import java.io.File;

public class Main {
    public static void main(String[] args){
        PresenterDisplay display = new PresenterDisplay();
        //TODO GIVE A CORRECT FILEPATH
        File file = new File("");
        display.playGame(file);
    }
}


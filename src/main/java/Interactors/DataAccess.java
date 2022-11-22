package Interactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataAccess {
    ArrayList<ArrayList<String[]>> loadGame() throws FileNotFoundException;
    boolean saveGame(String[][] playerData, String[][] boardData, Integer[] mainStates) throws IOException;
    ArrayList<String[]> loadProperties() throws FileNotFoundException;
    File[] checkSaves(String folderPath);
}

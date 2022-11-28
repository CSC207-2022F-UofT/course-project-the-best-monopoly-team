package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataAccess {
    ArrayList<ArrayList<String[]>> loadGame() throws FileNotFoundException;
    boolean saveGame(String[][] playerData, String[][] boardData,
                     String[][] treeData, String[][] optionData) throws IOException;
    ArrayList<String[]> loadProperties() throws FileNotFoundException;

    List<String> loadCards(File file) throws IOException;

}
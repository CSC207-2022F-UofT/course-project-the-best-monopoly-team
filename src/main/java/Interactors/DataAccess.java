package Interactors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface DataAccess {
    ArrayList<String[]> loadGame(String filePath) throws FileNotFoundException;
    boolean saveGame(String fileName) throws IOException;
}

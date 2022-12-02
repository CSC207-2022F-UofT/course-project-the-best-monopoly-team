package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LoadAccess {
    ArrayList<ArrayList<String[]>> loadGame() throws FileNotFoundException;
    File[] checkSaves(String folderPath);
    ArrayList<String[]> loadProperties() throws FileNotFoundException;
    List<String> loadCards(File file) throws IOException;

}

package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface LoadAccess {
    ArrayList<ArrayList<String[]>> loadGame(String file) throws FileNotFoundException;
    String[] checkSaves(String folderPath);
    File getFile();

    void setFile(String newFile);

    ArrayList<String[]> loadProperties() throws FileNotFoundException;

    List<String> loadCards() throws IOException;
}

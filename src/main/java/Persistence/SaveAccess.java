package Persistence;

import java.io.IOException;

public interface SaveAccess {
    void saveGame(String[][] playerData, Integer[] mainStates) throws IOException;
    boolean checkFile();
}

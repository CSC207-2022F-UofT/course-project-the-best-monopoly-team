package Persistence;

import java.io.IOException;

public interface SaveAccess {
    void saveGameNewFile(String[][] playerData, int[] mainStates) throws IOException;
    void saveGame(String[][] playerData, int[] mainStates) throws IOException;
    boolean checkFile();
}

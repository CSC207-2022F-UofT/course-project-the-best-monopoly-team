package Persistence;

import java.io.IOException;

public interface SaveAccess {
    String saveGameNewFile(String[][] playerData, int[] mainStates) throws IOException;
    String saveGame(String[][] playerData, int[] mainStates) throws IOException;
    boolean checkFile();
}

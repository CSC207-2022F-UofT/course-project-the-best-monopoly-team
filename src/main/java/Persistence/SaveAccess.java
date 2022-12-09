package Persistence;

import java.io.File;
import java.io.IOException;

public interface SaveAccess {
    String saveGameNewFile(String[][] playerData, int[] mainStates) throws IOException;
    String saveGame(String[][] playerData, int[] mainStates) throws IOException;
    File getFile();
    boolean checkFile();
}

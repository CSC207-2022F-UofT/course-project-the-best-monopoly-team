package Persistence;

import java.io.File;
import java.io.IOException;

/**
 * General interface for saving with files
 */
public interface SaveAccess {
    String saveGameNewFile(String[][] playerData, int[] mainStates) throws IOException;
    String saveGame(String[][] playerData, int[] mainStates) throws IOException;
    File getFile();
    boolean checkFile();
}

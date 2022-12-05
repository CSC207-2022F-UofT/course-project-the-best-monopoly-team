package Interactors;

import Persistence.SaveAccess;
import Persistence.SaveFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SaveFileTest {
    @Test
    void testCheckExistingFile() {
        File existing = new File("src/gameData/test01.txt");
        SaveAccess testSave = new SaveFile(existing);

        assert testSave.checkFile();
    }

    @Test
    void testNotExistingFile() {
        File nonExisting = new File("src/gameData/doesnotexist.txt");
        SaveAccess dneTest = new SaveFile(nonExisting);

        assert !dneTest.checkFile();
    }

    @Test
    void testSaveGame() throws IOException {
        // declare expected values for the test
        // overwrite the file text to some random strings
        // assert the file contents are not equal to the expected values
        // call saveGame on the file to overwrite the values
        // assert the save file values are equal to the expected values
        File saveFile = new File("src/gameData/test01.txt");

        List<String> expectedLines = new ArrayList<>();
        expectedLines.add("playerStart");
        expectedLines.add("Player01,100,false,1,10");
        expectedLines.add("Mediterranean Avenue,Brown,60,50,2,10,30,90,160,250,Player01,30,0,true");
        expectedLines.add("Illinois Avenue,Red,240,150,20,100,300,750,925,1100,Player01,120,0,false");
        expectedLines.add("Player02,200,true,0,10");
        expectedLines.add("Boardwalk,Dark Blue,400,200,50,200,600,1400,1700,2000,Player02,200,0,false");
        expectedLines.add("playerEnd");
        expectedLines.add("treeStart");
        expectedLines.add("1,2,3,4,5");
        expectedLines.add("treeEnd");

        String[] random = new String[]{
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et efficitur tellus,",
                "a placerat nisi. Pellentesque eu scelerisque nibh. In ut lectus quis enim semper faucibus.",
                "Etiam est diam, viverra a arcu nec, sollicitudin viverra sem. Vestibulum convallis a tellus dignissim scelerisque.",
                "Donec sed massa eu dui lobortis lobortis. Etiam rhoncus lacus eget mauris finibus, id scelerisque elit vehicula.",
                "Nam sollicitudin eros non ligula consequat iaculis. Curabitur ac ipsum facilisis, pharetra lectus eu, finibus augue.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur et efficitur tellus,",
                "a placerat nisi. Pellentesque eu scelerisque nibh. In ut lectus quis enim semper faucibus.",
                "Etiam est diam, viverra a arcu nec, sollicitudin viverra sem. Vestibulum convallis a tellus dignissim scelerisque.",
                "Donec sed massa eu dui lobortis lobortis. Etiam rhoncus lacus eget mauris finibus, id scelerisque elit vehicula.",
                "Nam sollicitudin eros non ligula consequat iaculis. Curabitur ac ipsum facilisis, pharetra lectus eu, finibus augue."
        };

        FileWriter writer = new FileWriter(saveFile.getPath());
        for (String lorem: random){
            writer.write(lorem + "\n");
        }
        writer.close();

        List<String> randomLines = Files.readAllLines(saveFile.toPath());

        for (int j = 0; j < expectedLines.size(); j++){
            assert !(randomLines.get(j).equals(expectedLines.get(j)));
        }

        SaveAccess save = new SaveFile(saveFile);
        String[][] playerData = new String[][]{{"Player01", "100", "false", "1", "10"},
                {"Mediterranean Avenue","Brown","60","50","2","10","30","90","160","250","Player01","30","0","true"},
                {"Illinois Avenue","Red","240","150","20","100","300","750","925","1100","Player01","120","0","false"},
                {"Player02", "200", "true", "0", "10"},
                {"Boardwalk","Dark Blue","400","200","50","200","600","1400","1700","2000","Player02","200","0","false"}};
        Integer[] mainStates = new Integer[]{1, 2, 3, 4, 5};
        save.saveGame(playerData, mainStates);

        List<String> allLines = Files.readAllLines(saveFile.toPath());
        for (int i = 0; i < allLines.size(); i++){
            assert allLines.get(i).equals(expectedLines.get(i));
        }
    }
}

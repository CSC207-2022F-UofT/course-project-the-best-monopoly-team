package Entities;
import Interactors.GameCreation;
import Interactors.TextFileTranslator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CornerTilesTest {

    public CornerTilesTest() throws IOException {
    }

    private Player player1 = new Player("Player1");
    @Test
    public void performActionTestInJail() throws IOException {
        ArrayList<String> players = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
        GameCreation creation = new GameCreation();
        String[] property = {"0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayList<String[]> properties = new ArrayList<String[]>();
        for (int i = 0; i < 40; i++){
            properties.add(property);
        }
        ArrayList<String[]> strings = new ArrayList<>();
        Board bo = creation.createNewGame(players, properties);
        CornerTiles t = new CornerTiles("jail");
        player1.setInJail(true);
        assertEquals( "You are still in Jail", t.performAction(player1, bo));
    }

    @Test
    public void performActionTestNotInJail() throws IOException {
        ArrayList<String> players = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
        GameCreation creation = new GameCreation();
        String[] property = {"0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayList<String[]> properties = new ArrayList<String[]>();
        for (int i = 0; i < 40; i++){
            properties.add(property);
        }
        ArrayList<String[]> strings = new ArrayList<>();
        Board bo = creation.createNewGame(players, properties);
        CornerTiles t = new CornerTiles("jail");
        player1.setInJail(false);
        assertEquals("You're visiting Jail", t.performAction(player1, bo));
    }
    @Test
    public void performActionTestFreeParking() throws IOException {
        ArrayList<String> players = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
        GameCreation creation = new GameCreation();
        String[] property = {"0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayList<String[]> properties = new ArrayList<String[]>();
        for (int i = 0; i < 40; i++){
            properties.add(property);
        }
        ArrayList<String[]> strings = new ArrayList<>();
        Board bo = creation.createNewGame(players, properties);
        CornerTiles t = new CornerTiles("freeParking");
        assertEquals("You landed on Free Parking!", t.performAction(player1, bo));
    }
    @Test
    public void performActionGoToJail() throws IOException {
        ArrayList<String> players = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
        GameCreation creation = new GameCreation();
        String[] property = {"0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayList<String[]> properties = new ArrayList<String[]>();
        for (int i = 0; i < 40; i++){
            properties.add(property);
        }
        ArrayList<String[]> strings = new ArrayList<>();
        Board bo = creation.createNewGame(players, properties);
        CornerTiles t = new CornerTiles("goToJail");
        assertEquals("Go to Jail!", t.performAction(player1, bo));
        assertEquals(player1.getPosition(), 11);
        assertEquals(player1.isInJail(), true);
    }
}

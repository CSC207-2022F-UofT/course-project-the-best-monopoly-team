package Entities;
import Interactors.CornerTilePerformActionInteractor;
import Interactors.GameCreation;
import UseCases.CornerTilePerformActionUseCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
        String mystr = cornerTileInteractor.performAction(player1, bo, t);
        assertEquals( "You are still in Jail", cornerTileInteractor.performAction(player1, bo, t));
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
        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
        assertEquals("You're visiting Jail", cornerTileInteractor.performAction(player1, bo, t));
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
        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
        assertEquals("You landed on Free Parking!", cornerTileInteractor.performAction(player1, bo, t));
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
        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
        assertEquals("Go to Jail!", cornerTileInteractor.performAction(player1, bo, t));
        assertEquals(player1.getPosition(), 11);
        assertEquals(player1.isInJail(), true);
    }
}

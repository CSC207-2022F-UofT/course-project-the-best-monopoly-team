package UseCases;

import Interactors.GameCreation;
import Logic.PlayerLogic;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import Entities.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Entities.Player;
import Entities.Property;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import static org.junit.Assert.*;

public class PlayerLogicTest {

    @Test
    public void testCountPropertySetsBrown() {
        Player player1 = new Player("Player1");
        PlayerLogic pl1 = new PlayerLogic(player1);
        Property property1 = new Property("property1", "Brown",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        Property property2 = new Property("property2", "Brown",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        player1.addProperty(property1);
        player1.addProperty(property2);
        int brown = pl1.countPropertySets().get("Brown");
        assertEquals(2, brown);
    }

    @Test
    public void testCountPropertySetsRed() {
        Player player1 = new Player("Player1");
        PlayerLogic pl1 = new PlayerLogic(player1);
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        Property property3 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        player1.addProperty(property1);
        player1.addProperty(property2);
        player1.addProperty(property3);
        int brown = pl1.countPropertySets().get("Red");
        assertEquals(3, brown);
    }

    @Test
    public void testOwnedPropertySets() {
        Player player1 = new Player("Player1");
        PlayerLogic pl1 = new PlayerLogic(player1);
        Property property1 = new Property("property1", "Red",
                5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        Property property2 = new Property("property2", "Red",
                5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        Property property3 = new Property("property2", "Red",
                5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        player1.addProperty(property1);
        player1.addProperty(property2);
        player1.addProperty(property3);
        ArrayList<String> ownedsets = new ArrayList<>(List.of("Red"));
        assertEquals(ownedsets, pl1.ownedPropertySets());
    }


    @Test
    public void testMortgage() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red", 5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, false);
        player1.addProperty(property2);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.mortgage(property2);
        Assert.assertTrue(property2.getMortgageStatus());
    }

    @Test
    public void testMortgageLandedOn() throws IOException {
        File gamefile = new File("src/properties.txt");
        LoadAccess load = new LoadFile(gamefile);
        ArrayList<String[]> propertylist = load.loadProperties();
        GameCreation gameCreator = new GameCreation();
        Board board = gameCreator.createNewBoard(new ArrayList<>(Arrays.asList("player1", "player2")), propertylist);
        Player player1 = board.getPlayers().get(0);
        Player player2 = board.getPlayers().get(1);
        Property property1 = (Property) board.getCells().get(1);
        player1.addProperty(property1);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.mortgage(property1);
        player2.move(1);
        assertTrue(property1.getMortgageStatus());
        assertEquals(1500, player2.getMoney());
    }

    @Test
    public void testUnmortgage() throws IOException {
        File gamefile = new File("src/properties.txt");
        LoadAccess load = new LoadFile(gamefile);
        ArrayList<String[]> propertylist = load.loadProperties();
        GameCreation gameCreator = new GameCreation();
        Board board = gameCreator.createNewBoard(new ArrayList<>(Arrays.asList("player1", "player2")), propertylist);
        Player player1 = board.getPlayers().get(0);
        Property property1 = (Property) board.getCells().get(1);
        player1.addProperty(property1);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.mortgage(property1);
        pl1.unmortgage(property1);
        assertFalse(property1.getMortgageStatus());
    }

    @Test
    public void testBuildHouseSuccess() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red", 5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        Property property3 = new Property("property3", "Red", 5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        Property property4 = new Property("property4", "Red", 5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, true);
        player1.addProperty(property2);
        player1.addProperty(property3);
        player1.addProperty(property4);
        PlayerLogic pl1 = new PlayerLogic(player1);
        String returnString = pl1.buildHouse(property2, 2);
        Assert.assertEquals("2 houses have been built on property2", returnString);
        Assert.assertEquals(2L, property2.getHouses());
    }
}

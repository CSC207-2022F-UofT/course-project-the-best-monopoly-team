package UseCases;

import Entities.*;
import Interactors.GameCreation;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import UseCases.PlayerLogic;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Entities.Player;
import Entities.Property;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerLogicTest {

    @Test
    public void testTradeMoney() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        PlayerLogic pl1 = new PlayerLogic(player1);
        player1.setMoney(500);
        player2.setMoney(800);
        pl1.trade(player2, 100, new ArrayList<>(), 0);
        Assert.assertEquals(400L, player1.getMoney());
        Assert.assertEquals(900L, player2.getMoney());
    }

    @Test
    public void testTradeProperty() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,0,true);
        player1.addProperty(property1);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.trade(player2, 0, player1.getProperties(), 0);
        ArrayList<Property> p1NewProperty = new ArrayList<Property>(List.of());
        ArrayList<Property> p2NewProperty = new ArrayList<Property>(List.of(property1));
        Assert.assertEquals(p1NewProperty, player1.getProperties());
        Assert.assertEquals(p2NewProperty, player2.getProperties());
    }

    @Test
    public void testTradeJailCards() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setJailCards(2);
        player2.setJailCards(0);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.trade(player2, 0, new ArrayList<>(), 1);
        Assert.assertEquals(1L, player1.getJailCards());
        Assert.assertEquals(1L, player2.getJailCards());
    }

    @Test
    public void testMortgage() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red", 5, 8, new int[]{1, 2, 3, 4, 5, 6}, player1, 200, 0, false);
        player1.addProperty(property2);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.mortgage(property2);
        Assert.assertTrue(property2.getMortgageStatus());
        Assert.assertTrue(player1.getProperties().isEmpty());
    }

    @Test
    public void testMortgageLandedOn() throws IOException {
        File gamefile = new File("src/properties.txt");
        LoadAccess load = new LoadFile(gamefile);
        ArrayList<String[]> propertylist = load.loadProperties();
        GameCreation gameCreator = new GameCreation();
        Board board = gameCreator.createNewGame(new ArrayList<String>(Arrays.asList("player1", "player2")), propertylist);
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
        Board board = gameCreator.createNewGame(new ArrayList<String>(Arrays.asList("player1", "player2")), propertylist);
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
        Assert.assertEquals(2L, (long)property2.getHouses());
    }
}

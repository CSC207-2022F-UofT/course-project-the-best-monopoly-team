package Entities;

import Interactors.PropertyPerformActionInteractor;
import UseCases.PropertyPerformActionUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void testPerformActionOwner() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        player1.addProperty(property1);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        List<Cell> cells = new ArrayList<>();
        cells.add(property1);
        Board board = new Board(players, cells);
        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
        String returnString = propertyInteractor.performAction(property1, player1);
        assertEquals("Landed on a property you own", returnString);
        assertEquals(1500, player1.getMoney());
    }

    @Test
    public void testPerformActionNotOwner() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player2,200,2,true);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        List<Cell> cells = new ArrayList<>();
        cells.add(property1);
        Board board = new Board(players, cells);
        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
        String returnString = propertyInteractor.performAction(property1, player1);
        assertEquals("Paid $3 to Player2", returnString);
        assertEquals(1497, player1.getMoney());
    }

    @Test
    public void testSetHouses() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        property1.setHouses(4);
        assertEquals(4, property1.getHouses());
    }

    @Test
    public void testGetOwner() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(player1, property1.getOwner());
    }

    @Test
    public void testSetOwner() {
        Player player2 = new Player("Player2");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, null,200,2,true);
        property1.setOwner(player2);
        assertEquals(player2, property1.getOwner());
    }

    @Test
    public void testGetName() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6, 6}, player1,200,2,true);
        assertEquals("property1", property1.getName());
    }

    @Test
    public void testGetColour() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals("Red", property1.getColour());
    }

    @Test
    public void testGetPrice() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(5, property1.getPrice());
    }

    @Test
    public void testGetHousesRent() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(3, property1.getRent());
    }

    @Test
    public void testGetRentRailroad() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(100, property1.getRent());
    }

    @Test
    public void testGetRentUtility() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Utility",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(50, property1.getRent());
    }

    @Test
    public void testGetHouseCost() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(8, property1.getHouseCost());
    }

    @Test
    public void testGetMortgageValue() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        assertEquals(200, property1.getMortgageValue());
    }

    @Test
    public void testSetMortgageStatus() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        property1.setMortgageStatus(false);
        assertFalse(property1.getMortgageStatus());
    }

    @Test
    public void testGetHouses() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,4,true);
        assertEquals(4, property1.getHouses());
    }

}

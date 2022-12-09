package Entities;

import Interactors.PropertyPerformActionInteractor;
import UseCases.PropertyPerformActionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


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
        new Board(players, cells);
        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
        String returnString = propertyInteractor.performAction(property1, player1);
        Assertions.assertEquals(" You landed on a property you own", returnString);
        Assertions.assertEquals(1500, player1.getMoney());
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
        new Board(players, cells);
        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
        String returnString = propertyInteractor.performAction(property1, player1);
        Assertions.assertEquals(" Paid $3 to Player2 for landing on property1", returnString);
        Assertions.assertEquals(1497, player1.getMoney());
    }

    @Test
    public void testSetHouses() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        property1.setHouses(4);
        Assertions.assertEquals(4, property1.getHouses());
    }

    @Test
    public void testGetOwner() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(player1, property1.getOwner());
    }

    @Test
    public void testSetOwner() {
        Player player2 = new Player("Player2");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, null,200,2,true);
        property1.setOwner(player2);
        Assertions.assertEquals(player2, property1.getOwner());
    }

    @Test
    public void testGetName() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6, 6}, player1,200,2,true);
        Assertions.assertEquals("property1", property1.getName());
    }

    @Test
    public void testGetColour() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals("Red", property1.getColour());
    }

    @Test
    public void testGetPrice() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Red",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(5, property1.getPrice());
    }

    @Test
    public void testGetHousesRent() {
        int[] rentRed = new int[]{18,90,250,700,875,1050};
        int[] rentIllinois = new int[]{20,100,300,750,925,1100};
        Player player = new Player("landlord");
        Property illinois = new Property("Illinois Avenue","Red",240,150, rentIllinois, player,120,0,false);
        Property kentucky = new Property("Kentucky Avenue","Red",220,150,rentRed, player, 110,0, false);
        Property indiana = new Property("Indiana Avenue","Red",220,150,rentRed, player, 110,0, false);
        player.addProperty(illinois);
        player.addProperty(kentucky);
        player.addProperty(indiana);

        Assertions.assertEquals(20, illinois.getRent());
        illinois.addHouse(player, 1);
        Assertions.assertEquals(100, illinois.getRent());
        illinois.addHouse(player, 1);
        Assertions.assertEquals(300, illinois.getRent());
        illinois.addHouse(player, 1);
        Assertions.assertEquals(750, illinois.getRent());
        illinois.addHouse(player, 1);
        Assertions.assertEquals(925, illinois.getRent());
        illinois.addHouse(player, 1);
        Assertions.assertEquals(1100, illinois.getRent());
    }

    @Test
    public void testGetRentRailroad() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(100, property1.getRent());
    }

    @Test
    public void testGetRentUtility() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Utility",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(50, property1.getRent());
    }

    @Test
    public void testGetHouseCost() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(8, property1.getHouseCost());
    }

    @Test
    public void testGetMortgageValue() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        Assertions.assertEquals(200, property1.getMortgageValue());
    }

    @Test
    public void testSetMortgageStatus() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,2,true);
        property1.setMortgageStatus(false);
        Assertions.assertFalse(property1.getMortgageStatus());
    }

    @Test
    public void testGetHouses() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "Railroad",
                5,8, new int[]{1, 2, 3, 4, 5, 6}, player1,200,4,true);
        Assertions.assertEquals(4, property1.getHouses());
    }

    @Test
    public void testGetRentSave() {
        int[] rent = new int[]{20,100,300,750,925,1100};
        Property illinois = new Property("Illinois Avenue","Red",240,150, rent, null,120,3,false);
        int price = illinois.getRentSave(illinois.getHouses());
        Assertions.assertEquals(price, rent[3]);
    }

    @Test
    public void testAddHouse() {
        int[] rentRed = new int[]{18,90,250,700,875,1050};
        int[] rentIllinois = new int[]{20,100,300,750,925,1100};
        Player player = new Player("landlord");
        Property illinois = new Property("Illinois Avenue","Red",240,150, rentIllinois, null,120,0,false);
        String notOwned = illinois.addHouse(player, 1);
        assert notOwned.equals("not owned");
        illinois.setOwner(player);
        player.addProperty(illinois);
        String setNotOwned = illinois.addHouse(player, 1);
        assert setNotOwned.equals("not owned set");
        Property kentucky = new Property("Kentucky Avenue","Red",220,150,rentRed, player, 110,0, false);
        Property indiana = new Property("Indiana Avenue","Red",220,150,rentRed, player, 110,0, false);
        player.addProperty(kentucky);
        player.addProperty(indiana);
        player.setMoney(0);
        String notEnoughMoney = illinois.addHouse(player, 1);
        assert notEnoughMoney.equals("not enough money");
        player.setMoney(100000);
        String oneHouse = illinois.addHouse(player,1);
        assert oneHouse.equals("house");
        String hotel = illinois.addHouse(player, 4);
        assert hotel.equals("hotel");
    }
}

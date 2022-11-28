package Entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testStartingAttributes() {
        Player player1 = new Player("Player1");
        assertEquals("Player1", player1.getName());
        assertEquals(1500, player1.getMoney());
        assertEquals(new ArrayList<Property>(), player1.getProperties());
        assertFalse(player1.isInJail());
        assertEquals(0, player1.getJailCards());
        assertEquals(0, player1.getPosition());
    }

    @Test
    public void testSetPosition() {
        Player player1 = new Player("Player1");
        player1.setPosition(24);
        assertEquals(24, player1.getPosition());
    }

    @Test
    public void testSetInJail() {
        Player player1 = new Player("Player1");
        player1.setInJail(true);
        assertTrue(player1.isInJail());
        player1.setInJail(false);
        assertFalse(player1.isInJail());
    }

    @Test
    public void testSetJailCards() {
        Player player1 = new Player("Player1");
        player1.setJailCards(3);
        assertEquals(3, player1.getJailCards());
    }

    @Test
    public void testSetMoney() {
        Player player1 = new Player("Player1");
        player1.setMoney(6969);
        assertEquals(6969, player1.getMoney());
    }

    @Test
    public void testTradeMoneySuccess() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setMoney(500);
        player2.setMoney(800);
        String m = player1.trade(player2,100, new ArrayList<Property>(), 0);
        assertEquals(400, player1.getMoney());
        assertEquals(900, player2.getMoney());
        assertEquals("Trade successful", m);
    }

    @Test
    public void testTradeMoneyFail() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setMoney(500);
        player2.setMoney(800);
        String m = player1.trade(player2,600, new ArrayList<Property>(), 0);
        assertEquals(500, player1.getMoney());
        assertEquals(800, player2.getMoney());
        assertEquals("Inadequate amount of money", m);
    }

    @Test
    public void testTradeJailCards() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setJailCards(2);
        player2.setJailCards(0);
        player1.trade(player2, 0, new ArrayList<Property>(), 1);
        assertEquals(1, player1.getJailCards());
        assertEquals(1, player2.getJailCards());
    }

    @Test
    public void testMoveEntireBoard() {
        Player player1 = new Player("Player1");
        player1.move(40);
        assertEquals(0, player1.getPosition());
    }

    @Test
    public void testMoveSmall() {
        Player player1 = new Player("Player1");
        player1.move(5);
        assertEquals(5, player1.getPosition());
    }

//    Property property1 = new Property("property1", "yellow",
//            3,4, new int[]{1, 2, 3, 4, 5}, player1,100,4,true);
//    Property property2 = new Property("property2", "red",
//            5,8, new int[]{1, 2, 3, 4, 5}, player1,200,4,true);
//    Property property3 = new Property("property2", "red",
//            5,8, new int[]{1, 2, 3, 4, 5}, player1,200,4,true);
//    Property property4 = new Property("property2", "red",
//            5,8, new int[]{1, 2, 3, 4, 5}, player1,200,4,true);

    @Test
    public void testBuildHouseSuccess() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        Property property3 = new Property("property3", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        Property property4 = new Property("property4", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        player1.addProperty(property2);
        player1.addProperty(property3);
        player1.addProperty(property4);
        String returnString = player1.buildHouse(property2,2);
        assertEquals("2 houses have been built on property2", returnString);
        assertEquals(2, property2.getHouses());
    }

    @Test
    public void testBuildHouseHotel() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,2,true);
        Property property3 = new Property("property3", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        Property property4 = new Property("property4", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        player1.addProperty(property2);
        player1.addProperty(property3);
        player1.addProperty(property4);
        String returnString = player1.buildHouse(property2,3);
        assertEquals("A hotel has been built on property2", returnString);
        assertEquals(5, property2.getHouses());
    }

    @Test
    public void testBuildHouseFailNotOwned() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        String returnString = player1.buildHouse(property2,1);
        assertEquals("Player does not own property2", returnString);
        assertEquals(0, property2.getHouses());
    }

    @Test
    public void testBuildHouseFailNotOwnedSet2() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Dark Blue",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        player1.addProperty(property2);
        String returnString = player1.buildHouse(property2,2);
        assertEquals("Player does not own the full colour set of property2", returnString);
        assertEquals(0, property2.getHouses());
    }

    @Test
    public void testBuildHouseFailNotOwnedSet3() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        Property property3 = new Property("property3", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        player1.addProperty(property2);
        player1.addProperty(property3);
        String returnString = player1.buildHouse(property3,2);
        assertEquals("Player does not own the full colour set of property3", returnString);
        assertEquals(0, property2.getHouses());
    }

    @Test
    public void testBuildHouseFailMoney() {
        Player player1 = new Player("Player1");
        Property property2 = new Property("property2", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,2,true);
        Property property3 = new Property("property3", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        Property property4 = new Property("property4", "Red",
                5,8, new int[]{1, 2, 3, 4, 5}, player1,200,0,true);
        player1.addProperty(property2);
        player1.addProperty(property3);
        player1.addProperty(property4);
        player1.setMoney(10);
        String returnString = player1.buildHouse(property4,2);
        assertEquals("Player does not have enough money to build 2 houses on property4", returnString);
        assertEquals(0, property4.getHouses());
    }

    @Test
    public void testChangeMoney(){
        Player player1 = new Player("Player1");
        player1.setMoney(100);
        player1.changeMoney(30);
        assertEquals(130, player1.getMoney());
    }

    @Test
    public void testVoidPay() {
        Player player2 = new Player("Player2");
        player2.setMoney(100);
        player2.pay(30);
        assertEquals(70, player2.getMoney());
    }

    @Test
    public void testPay() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player2.setMoney(100);
        player1.setMoney(100);
        player2.pay(player1,30);
        assertEquals(70, player2.getMoney());
        assertEquals(130, player1.getMoney());
    }

    @Test
    public void testMortgage() {
        Player player1 = new Player("Player1");
        Property property1 = new Property("property1", "yellow",
                3,4, new int[]{1, 2, 3, 4, 5}, player1,100,4,true);
        player1.setMoney(100);
        player1.mortgage(property1);
        assertEquals(200, player1.getMoney());
        assertEquals(new ArrayList<Property>(), player1.getProperties());
    }
}

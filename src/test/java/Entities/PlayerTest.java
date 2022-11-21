package Entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player1 = new Player("Player1");
    private Player player2 = new Player("Player2");

    private void initPlayer() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
    }

    private Property property1 = new Property("property1", "yellow",
            3,4, new int[]{1, 2, 3, 4, 5}, player1,100,4,true
    );

    private Property property2 = new Property("property2", "red",
            5,8, new int[]{1, 2, 3, 4, 5}, player1,200,4,true
    );

    @Test
    public void testStartingAttributes() {
        initPlayer();
        assertEquals("Player1", player1.getName());
        assertEquals(1500, player1.getMoney());
        assertEquals(new ArrayList<Property>(), player1.getProperties());
        assertFalse(player1.isInJail());
        assertEquals(0, player1.getJailCards());
        assertEquals(0, player1.getPosition());
    }

    @Test
    public void testSetPosition() {
        initPlayer();
        player1.setPosition(24);
        assertEquals(24, player1.getPosition());
    }

    @Test
    public void testSetInJail() {
        initPlayer();
        player1.setInJail(true);
        assertTrue(player1.isInJail());
        player1.setInJail(false);
        assertFalse(player1.isInJail());
    }

    @Test
    public void testSetJailCards() {
        initPlayer();
        player1.setJailCards(3);
        assertEquals(3, player1.getJailCards());
    }

    @Test
    public void testSetMoney() {
        initPlayer();
        player1.setMoney(6969);
        assertEquals(6969, player1.getMoney());
    }

    @Test
    public void testTradeMoneySuccess() {
        initPlayer();
        player1.setMoney(500);
        player2.setMoney(800);
        String m = player1.trade(player2,100, new ArrayList<Property>(), 0);
        assertEquals(400, player1.getMoney());
        assertEquals(900, player2.getMoney());
        assertEquals("Trade successful", m);
    }

    @Test
    public void testTradeMoneyFail() {
        initPlayer();
        player1.setMoney(500);
        player2.setMoney(800);
        String m = player1.trade(player2,600, new ArrayList<Property>(), 0);
        assertEquals(500, player1.getMoney());
        assertEquals(800, player2.getMoney());
        assertEquals("Inadequate amount of money", m);
    }

    @Test
    public void testTradeJailCards() {
        initPlayer();
        player1.setJailCards(2);
        player2.setJailCards(0);
        player1.trade(player2, 0, new ArrayList<Property>(), 1);
        assertEquals(1, player1.getJailCards());
        assertEquals(1, player2.getJailCards());
    }

    @Test
    public void testMoveEntireBoard() {
        initPlayer();
        player1.move(40);
        assertEquals(0, player1.getPosition());
    }

    @Test
    public void testMoveSmall() {
        initPlayer();
        player1.move(5);
        assertEquals(5, player1.getPosition());
    }
    @Test
    public void testBuildHouse() {
        initPlayer();
        player1.addProperty(property1);
        player1.buildHouse(property1,1);
        assertEquals(5, property1.getHouses());
    }

    @Test
    public void testBuildHouseNull() {
        initPlayer();
        player2.buildHouse(property1,1);
        assertEquals(4, property1.getHouses());
    }

    @Test
    public void testChangeMoney(){
        initPlayer();
        player1.setMoney(100);
        player1.changeMoney(30);
        assertEquals(130, player1.getMoney());
    }

    @Test
    public void testVoidPay() {
        initPlayer();
        player2.setMoney(100);
        player2.pay(30);
        assertEquals(70, player2.getMoney());
    }

    @Test
    public void testPay() {
        initPlayer();
        player2.setMoney(100);
        player1.setMoney(100);
        player2.pay(player1,30);
        assertEquals(70, player2.getMoney());
        assertEquals(130, player1.getMoney());
    }

    @Test
    public void mortgage() {
        initPlayer();
        player1.setMoney(100);
        player1.mortgage(property1);
        assertEquals(200, player1.getMoney());
        assertEquals(new ArrayList<Property>(), player1.getProperties());
    }
}

package Entities;

import UseCases.PlayerLogic;
import org.junit.jupiter.api.Test;

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

}

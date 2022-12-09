package Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerTest {

    @Test
    public void testStartingAttributes() {
        Player player1 = new Player("Player1");
        Assertions.assertEquals("Player1", player1.getName());
        Assertions.assertEquals(1500, player1.getMoney());
        Assertions.assertEquals(new ArrayList<Property>(), player1.getProperties());
        Assertions.assertFalse(player1.isInJail());
        Assertions.assertEquals(0, player1.getJailCards());
        Assertions.assertEquals(0, player1.getPosition());
    }

    @Test
    public void testSetPosition() {
        Player player1 = new Player("Player1");
        player1.setPosition(24);
        Assertions.assertEquals(24, player1.getPosition());
    }

    @Test
    public void testSetInJail() {
        Player player1 = new Player("Player1");
        player1.setInJail(true);
        Assertions.assertTrue(player1.isInJail());
        player1.setInJail(false);
        Assertions.assertFalse(player1.isInJail());
    }

    @Test
    public void testSetJailCards() {
        Player player1 = new Player("Player1");
        player1.setJailCards(3);
        Assertions.assertEquals(3, player1.getJailCards());
    }

    @Test
    public void testSetMoney() {
        Player player1 = new Player("Player1");
        player1.setMoney(6969);
        Assertions.assertEquals(6969, player1.getMoney());
    }

    @Test
    public void testMoveEntireBoard() {
        Player player1 = new Player("Player1");
        player1.move(40);
        Assertions.assertEquals(0, player1.getPosition());
    }

    @Test
    public void testMoveSmall() {
        Player player1 = new Player("Player1");
        player1.move(5);
        Assertions.assertEquals(5, player1.getPosition());
    }
    @Test
    public void testChangeMoney(){
        Player player1 = new Player("Player1");
        player1.setMoney(100);
        player1.changeMoney(30);
        Assertions.assertEquals(130, player1.getMoney());
    }

    @Test
    public void testVoidPay() {
        Player player2 = new Player("Player2");
        player2.setMoney(100);
        player2.pay(30);
        Assertions.assertEquals(70, player2.getMoney());
    }

    @Test
    public void testPay() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player2.setMoney(100);
        player1.setMoney(100);
        player2.pay(player1,30);
        Assertions.assertEquals(70, player2.getMoney());
        Assertions.assertEquals(130, player1.getMoney());
    }

}

package UseCases;

import Entities.Player;
import Entities.Property;
import UseCases.PlayerLogic;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerLogicTest {

    @Test
    public void testTradeMoneySuccess() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setMoney(500);
        player2.setMoney(800);
        String m = player1.trade(player2,100, new ArrayList<Property>(), 0);
        assertEquals(400, player1.getMoney());
        assertEquals(900, player2.getMoney());
    }

    @Test
    public void testTradeMoneyFail() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setMoney(500);
        player2.setMoney(800);
        String m = PlayerLogic.trade(player1, player2, 600, new ArrayList<Property>(), 0);
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
}

package UseCases;

import Entities.Player;
import Entities.Property;
import UseCases.PlayerLogic;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import Entities.Player;
import Entities.Property;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class PlayerLogicTest {
    public PlayerLogicTest() {
    }

    @Test
    public void testTradeMoneySuccess() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        PlayerLogic pl1 = new PlayerLogic(player1);
        player1.setMoney(500);
        player2.setMoney(800);
        pl1.trade(player2, 100, new ArrayList(), 0);
        Assert.assertEquals(400L, (long)player1.getMoney());
        Assert.assertEquals(900L, (long)player2.getMoney());
    }

    @Test
    public void testTradeJailCards() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setJailCards(2);
        player2.setJailCards(0);
        PlayerLogic pl1 = new PlayerLogic(player1);
        pl1.trade(player2, 0, new ArrayList(), 1);
        Assert.assertEquals(1L, (long)player1.getJailCards());
        Assert.assertEquals(1L, (long)player2.getJailCards());
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

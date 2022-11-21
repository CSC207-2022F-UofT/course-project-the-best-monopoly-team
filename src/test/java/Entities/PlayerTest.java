package Entities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    private Player a = new Player("Player1");
    private Player b = new Player("Player2");

    private Property property = new Property("property1", "yello",
            3,4, new int[]{1, 2, 3, 4, 5},a,100,4,true
    );

    @Test
    public void testMove(){
        a.move(40);
        assertEquals(0, a.getPosition());
    }

    @Test
    public void testBuildHouse(){
        a.addProperty(property);
        a.buildHouse(property,1);
        assertEquals(5, property.getHouses());
    }

    @Test
    public void testBuildHouseNull(){
        b.buildHouse(property,1);
        assertEquals(4, property.getHouses());
    }

    @Test
    public void testChangeMoney(){
          a.setMoney(100);
          a.changeMoney(30);
          assertEquals(130, a.getMoney());
    }

    @Test
    public void testVoidPay() {
        b.setMoney(100);
        b.pay(30);
        assertEquals(70, b.getMoney());
    }

    @Test
    public void testPay() {
        b.setMoney(100);
        a.setMoney(100);
        b.pay(a,30);
        assertEquals(70, b.getMoney());
        assertEquals(130, a.getMoney());
    }

    @Test
    public void mortgage() {
        a.setMoney(100);
        a.mortgage(property);
        assertEquals(200, a.getMoney());
        assertEquals(new ArrayList<Property>(), a.getProperties());
    }
}


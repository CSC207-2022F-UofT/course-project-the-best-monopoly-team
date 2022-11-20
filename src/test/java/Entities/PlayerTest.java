package Entities;

import org.junit.*;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player a = new Player("a");

    @Test
    public void testMove(){
         a.move(40);
         assertEquals(0, a.getPosition());
    }

    @Test
    public void testBuildHouse(){
    }

    public static void main(String args[]){
        Player a = new Player("a");
        a.setInJail(true);
        System.out.println(a.rollDice(0));
        System.out.println("Position is " + a.getPosition());
        System.out.println("Injail is " + a.isInJail());

        Player b = new Player("b");
        b.setInJail(false);
        System.out.println(b.rollDice(0));
        System.out.println("Position is " + b.getPosition());
        System.out.println("Injail is " + b.isInJail());
    }

}


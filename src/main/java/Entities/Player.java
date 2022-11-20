package Entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Player {
    // Represents a player in the game

    private String name;

    private int money;
    private ArrayList<Property> properties;

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isInJail() {
        return inJail;
    }

    private boolean inJail;
    private int jailCards;
    private int position;

    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.jailCards = 0;
        this.position = 0;
    }

    public String getName() { return name; }

    public int getMoney() { return money; }

    public ArrayList<Property> getProperties() { return properties; }

    public int getJailCards() { return jailCards; }

    public int getPosition() { return position; }

    public String trade(Player tradee, int money, ArrayList<Property> properties, int jailcards) {
        if (money > this.money) {
            return "Inadequate amount of money";
        } else {
            this.money -= money;
            tradee.money += money;
            tradee.properties.addAll(properties);
            this.properties.removeAll(properties);
            this.jailCards -= jailcards;
            tradee.jailCards += jailCards;
            return "Trade successful";
        }
    }

    public void changeJailStatus(Player player) {
        this.inJail = !this.inJail;
    }

    public void addJailCards(int cards) {
        this.jailCards += cards;
    }

    /* UNCOMMENT THIS WHEN GameLogicTree IS MERGED INTO THE MAIN BRANCH
    public StringBuilder getPossibleActions() {
        StringBuilder actions = new StringBuilder();
        List<MenuTree> actionList = GameLogicTree.getChildren();
        for (MenuTree node: actionList) {
            String concat = node.id + ", ";
            actions.append(concat);
        }
        return actions;
    }
    */

    public void addProperty(Property property) {

        this.properties.add(property);
    }

    public String steal(Player victim) {
        double success = Math.random();
        if (success <= 0.3) {
            this.money += 100;
            victim.money -= 100;
            return this.name + " stole money from " + victim.name;
        } else {
            System.out.println("The police are looking for " + this.name);
            double jail = Math.random();
            if (jail <= 0.6) {
                this.inJail = true;
                return this.name + " is put in jail";
            } else {
                return this.name + " escaped from the police";
            }
        }
    }


    public void move(int step) {
        position += step;
        if (position > 39) {
            position -= 40;
        }
    }

    public String rollDice(int consecutive) {
        /**
         * Roll 2 dices at once and move the player. If the player is
         * in jail, and there is a double between 2 dices, player.inJail
         * is set to False and the player is moved.
         * If the player is not in jail, when a double
         * exists the player rolls the dice again. The maximum for consecutive
         * doubles is 3, and the player goes to jail if consecutive doubles == 3.
         * This method can be called recursively, and each time
         * the consecutive is the number of consecutive doubles.
         */
        int max = 6;
        int min = 1;
        int a =  (int) Math.floor(Math.random() * (max - min + 1) + min);
        int b = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (this.inJail){
            if(a == b){
                this.inJail = false;
                this.move(a + b);
                return (a + "\n" + b);
            }
        }
        else {
            if (a != b){
                this.move(a + b);
                return (a + "\n" + b);
            }
            else if (a == b && (consecutive + 1) < 3){
                this.rollDice((consecutive + 1));
            }
            else if(a == b && (consecutive + 1) == 3){
                // the player goes to jail
                this.setInJail(true);
                return (a + "\n" + b + "\n" + "player goes to jail");
            }
        }
        this.move(a + b);
        return (a + "\n" + b);
    }

    public void buildHouse(Property property, int houses) {
        if (properties.contains(property)) {
            //property.addHouse(houses);
        }
    }

    public void changeMoney(int change) {
        money += change;
    }

    public void pay(int money) {
        this.money -= money;
    }

    public void pay(Player player, int money) {
        this.money -= money;
        player.money += money;
    }

    public void mortgage(Property property) {
        this.properties.remove(property);
        this.money += property.getMortgageValue();
    }


    public void setMoney(int parseInt) {
    }

    public void setJailCards(int parseInt) {
    }

    public void setPosition(int parseInt) {
    }
}


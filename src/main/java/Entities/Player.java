package Entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Player {
    // Represents a player in the game

    public int getPosition() {
        return position;
    }

    public String name;
    public int money;
    public ArrayList<Property> properties;
    public boolean inJail;
    public int jailCards;
    public int position;

    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.jailCards = 0;
        this.position = 0;
    }

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
    public String getName(){
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }
    
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

    public String rollDice() {
        int max = 6;
        int min = 1;
        int a = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int b = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (this.inJail) {
            if (a == b) {
                this.inJail = false;
                this.move(a + b);
                return (a + "\n" + b);
            }
        } else {
            if (a == b) {
                this.move(a + b);
                this.rollDice();
            } else {
                this.move(a + b);
                return (a + "\n" + b);
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


}


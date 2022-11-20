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

    /**
     * This is the constructor method for new player instances that takes in more parameters. Useful when loading a
     * previously saved game
     * @param name the name of the player
     * @param money the amount of money in the player's balance
     * @param inJail the player's jail status
     * @param jailCards the number of get out of jail free cards owned by the player
     * @param position the player's current position on the board
     */
    public Player(String name, int money, boolean inJail, int jailCards, int position) {
        this.name = name;
        this.money = money;
        this.properties = new ArrayList<Property>();
        this.inJail = inJail;
        this.jailCards = jailCards;
        this.position = position;
    }

    /**
     * This basically sets the player attribute of inJail, which in this case is a boolean value
     * @param inJail the parameter for this is a boolean value that sets their inJail property to true or false
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
    public void setPosition(int position) {
        this.position = position;
    }


    public void setJailCards(int num) {
        this.jailCards = this.jailCards + num;
    }


    public int getJailCards() { return jailCards; }

    public int getPosition() { return position; }

    public boolean isInJail() { return inJail; }

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
    public void setMoney(int money) {
        this.money = money;
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
    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }


}


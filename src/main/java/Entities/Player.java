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

    /**
     * This is the constructor method for new player instances
     * @param name this parameter is for the name of the player
     */
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

    /**
     * This sets the position of the player
     * @param position the position of the player should be between 0 and 39 inclusive, as this dictates where on the
     *                 board the player is
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * This sets the number of get out of jail free cards the player has that was drawn from the community chest or
     * chance cards
     * @param num this parameter is the number of cards to increment by for player
     */
    public void setJailCards(int num) {
        this.jailCards = this.jailCards + num;
    }

    /**
     * The getter method for jailCards
     * @return the return value is of type int and returns the number of jail cards the current player has
     */
    public int getJailCards() { return jailCards; }

    /**
     * The getter method for position of the player
     * @return  returns the int that is from 0 to 39 inclusive corresponding to the player position
     */
    public int getPosition() { return position; }

    /**
     * The getter method to see if the player is in jail or not
     * @return returns a boolean value of False and True
     */
    public boolean isInJail() { return inJail; }

    /**
     * This method facilitates the trading of property, money and jailCards between two players.
     * @param tradee this parameter is the player that the current player wants to trade with
     * @param money this is the amount of money that the trader wants to trade
     * @param properties this is an ArrayList with property objects inside that the player wants to trade
     * @param jailcards this is the number of jail cards that the player wants to trade the jailCards with
     * @return returns a string if the trade was successful or not
     */
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

    /**
     * Changes the jail status of the player
     */
    public void changeJailStatus() {
        this.inJail = !this.inJail;
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

    /**
     * Getter method for the name of the player
     * @return returns a string, which is the name of the player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Setter method for setting the amount money
     * @param money the money that we want to set the money of the player to
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Getter method for getting the current amount of money that the player has.
     * @return returns and integer
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Adds a certain property to the current player
     * @param property the property to add to the properties list of the player
     */
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    /**
     * Method for stealing a players money and the player could be thrown in jail
     * @param victim the player that the current player wants to steal from
     * @return returns a string output to see if the player was successful or not
     */
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

    /**
     * Method to move the player by x number of steps
     * @param step the steps to move the player to
     */
    public void move(int step) {
        position += step;
        if (position > 39) {
            //Money on passing go
            money += 200;
            position -= 40;
        }
    }

    /**
     * Rigged roll for testing purposes
     * @param rig
     * @return
     */
    public String riggedRoll(int rig) {
        this.move(rig);
        return (""+rig);
    }

    /**
     * The method used to roll the dice for the player
     * @param i
     * @return returns the string of the number on the dice
     */
    public String rollDice(int i) {
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
                this.rollDice(b);
            } else {
                this.move(a + b);
                return (a + "\n" + b);
            }
        }
        this.move(a + b);
        return (a + "\n" + b);
    }

    /**
     * Builds a house on the current property
     * @param property the property to build the house on
     * @param houses the number of houses to build
     */
    public void buildHouse(Property property, int houses) {
        if (properties.contains(property)) {
            //property.addHouse(houses);
        }
    }

    /**
     * Increases the money of the player
     * @param change the integer to change the money by
     */
    public void changeMoney(int change) {
        money += change;
    }

    /**
     * Decrease the money of the player
     * @param money the amount of money to decrease by
     */
    public void pay(int money) {
        this.money -= money;
    }

    /**
     * Decrease the money of the current player and add it to another players money
     * @param player the player to add the money to
     * @param money the amount of money to decrease by for current player and increase by for the other player
     */
    public void pay(Player player, int money) {
        this.money -= money;
        player.money += money;
    }

    /**
     * Placing the property for mortgage
     * @param property the property to remove and to add the money to the current players balance
     */
    public void mortgage(Property property) {
        this.properties.remove(property);
        this.money += property.getMortgageValue();
    }

    /**
     * Gets all of the properties of the current player
     * @return returns and array list of properties
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the current players properties to another ArrayList of properties
     * @param properties the ArrayList of properties to change it to
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }


}


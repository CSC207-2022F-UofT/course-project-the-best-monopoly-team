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
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.money = 1500;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.jailCards = 0;
        this.position = 0;
    }

    public Player(String name, int money, boolean inJail, int jailCards, int position) {
        this.name = name;
        this.money = money;
        this.properties = new ArrayList<Property>();
        this.inJail = inJail;
        this.jailCards = jailCards;
        this.position = position;
    }
    /**
     * Set the player's position. This is useful when the player needs to move by a mean beside rolling the dice e.g.
     * action cards, or when we need to load a previous game.
     * @param position the position of the player on the board should be between 0 and 39 inclusive.
     */
    public void setPosition(int position) { this.position = position; }

    /**
     * Sets the player's inJail attribute, which in this case is a boolean value
     * @param inJail the parameter for this is a boolean value that sets their inJail property to true or false
     */
    public void setInJail(boolean inJail) { this.inJail = inJail; }

    /**
     * Sets the number of get out of jail free cards the player has that was drawn from the community chest or
     * chance cards
     * @param num this parameter is the number of cards to increment by for player
     */
    public void setJailCards(int num) { this.jailCards += num; }

    /**
     * Sets the amount of money owned by this player. Needed for loading previous games.
     * @param money the amount of money the player is having
     */
    public void setMoney(int money) { this.money = money; }

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
     * The getter method for this player's name
     * @return the name of the player
     */
    public String getName(){ return this.name; }

    /**
     * The getter method for this player's list of properties
     * @return an ArrayList of properties owned by this player
     */
    public ArrayList<Property> getProperties() { return properties; }

    /**
     * The getter method for this player's amount of money
     * @return the amount of money currently owned by this player
     */
    public int getMoney() { return this.money; }

    /**
     * This method determines whether this player is currently in jail
     * @return a boolean value determining whether the player is in jail, with true being the player is in jail
     */
    public boolean isInJail() { return inJail; }

    /**
     * Method allowing this player to trade assets with another player
     * @param tradee the player this player wants to trade with
     * @param money any sufficient amount of money this player wants to offer the tradee
     * @param properties properties owned by this player that they would like to offer the tradee
     * @param jailcards any sufficient amount of jail cards this player wants to offer to the tradee
     * @return a String indicating whether the player has an insufficient amount of money or if the trade was successful
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
     * Changes the jail status of this player
     * @param player the player whose jail status is being changed
     */
    public void changeJailStatus(Player player) {
        this.inJail = !this.inJail;
    }

    /**
     * This method adds a property to this player's list of owned properties
     * @param property the property to be added to the player's property list
     */
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    /**
     * Method that allows this player to steal 100 units of money from a player. The success of stealing relies on
     * a chance basis. Unsuccessfully stealing from the victim gives this player a chance of being put in jail.
     * @param victim the player whom this player is stealing from
     * @return returns a String statement indicating whether stealing was successful. If not, the statement indicates
     * whether this player is put in jail
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
            position -= 40;
        }
    }

    /**
     * This method rolls the dice for the player. The dice result can indicate this player's movement while
     * they are not in jail. If they are in jail, the dice result can determine if the player can get out of jail
     * @param consecutive the number of consecutive doubles
     * @return a String of two numbers indicating the numbers rolled from the dice
     */
    public String rollDice(int consecutive) {
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
        return (a + "\n" + b);
    }

    /**
     * This method allows this player to build houses on a currently owned property
     * @param property the property to build the house on
     * @param houses the number of houses to build
     */
    public void buildHouse(Property property, int houses) {
        if (properties.contains(property)) {
            property.addHouse(this, houses);
        }
    }

    /**
     * Increases the amount of money owned by this player
     * @param change an integer indicating how much money will be added to the player's balance
     */
    public void changeMoney(int change) {

        money += change;
    }

    /**
     * A payment method where money is deducted from the player's balance e.g. to pay tax
     * @param money the amount of money deducted from this player's balance
     */
    public void pay(int money) {

        this.money -= money;
    }

    /**
     * A payment method that allows this player to pay another player e.g. paying rent
     * @param player the player whom this player is giving money to
     * @param money the amount of money being paid by this player
     */
    public void pay(Player player, int money) {
        this.money -= money;
        player.money += money;
    }

    /**
     * This method allows the player to place one of their owned properties for mortgage
     * @param property the property to mortgaged by this player
     */
    public void mortgage(Property property) {
        this.properties.remove(property);
        this.money += property.getMortgageValue();
    }

}


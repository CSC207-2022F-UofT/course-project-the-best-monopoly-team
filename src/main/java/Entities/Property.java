package Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Property extends Cell {
    private Player ownedBy;
    private int houses;
    private int rent;
    private String name;
    private String colour;
    private int mortgageValue;
    private int houseCost;
    private int cost;
    private boolean mortgaged;

    public Property (String name, String colour, int cost, int rent, int mortgageValue, int houseCost) {
        this.ownedBy = null;
        this.houses = 0;
        this.cost = cost;
        this.name = name;
        this.colour = colour;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.mortgaged = false;
        this.houseCost = houseCost;
    }

    public String performAction(Player currentPlayer){
        // how do we get the current player?

        // set flag for player to know which branch of game logic tree we go down (property)
        if (this.getOwner() == null){
            // need to send back an option to get player input on if they will purchase the property or auction it

            // call some menu method to display the option to purchase or auction the property
            // assume the menu method returns some value to differentiate between purchase or auction
            // follow separate logic depending on the return of the method

            // purchase property
            // deduct the price of the property from the current players account
            // transfer the property to their ownership


            // auction property
            // display menu with total players - 1 input boxes
            // deduct the offer amount from the players account who made the highest offer
            // transfer the property to their ownership

        } else {
            // pay rent
            // call player pay method with owner of property and getrent price
            // if player balance is negative after paying, then give them option to mortgage properties, or declare bankruptcy
        }

        // temporary return
        return "";
    }

    public void setHouses(int houses){
        this.houses = houses;
    }

    public Player getOwner(){
        return this.ownedBy;
    }

    public void setOwner(Player player){ownedBy = player;}

    public String getName(){
        return this.name;
    }

    public String getColour(){
        return this.colour;
    }

    public int getPrice(){
        return this.cost;
    }

    public int getRent(){
        if (this.houses == 0) {
            return this.rent;
        } else {
            return (this.houses * 2) * this.rent;
        }
    }

    public int getHouseCost(){
        return this.houseCost;
    }

    public int getMortgageValue(){
        return this.mortgageValue;
    }

    public boolean getMortgageStatus(){return this.mortgaged;}

    public void setMortgageStatus(boolean status){this.mortgaged = status;}

    public int getHouses(){
        return this.houses;
    }

    public ArrayList<String[]> loadProperties() throws FileNotFoundException {
        // return array of all properties in txt file as Strings
        // GameCreationInteractor will parse Strings to create Property instances
        ArrayList<String[]> allProperties = new ArrayList<>();

        File properties = new File("../../../save/properties.txt");
        Scanner scan = new Scanner(properties);

        while (scan.hasNextLine()) {
            String property = scan.nextLine();
            String[] propertyAttributes = property.split(",");
            allProperties.add(propertyAttributes);
        }

        return allProperties;
    }
}

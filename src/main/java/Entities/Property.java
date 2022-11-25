package Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Property extends Cell {
    private String name;
    private String colour;
    private int cost;
    private int rent;
    private int rent1H;
    private int rent2H;
    private int rent3H;
    private int rent4H;
    private int rentHotel;
    private Player ownedBy;
    private int mortgageValue;
    private int houseCost;
    private int houses;
    private boolean mortgaged;

    public Property (String name, String colour, int cost, int houseCost, int[] rentValues,
                     Player owner, int mortgageValue, int houses, boolean mortgaged) {
        this.name = name;
        this.colour = colour;
        this.cost = cost;
        this.houseCost = houseCost;
        for (int i = 0; i < 5; i++){
            switch (i) {
                case 0:
                    this.rent = rentValues[i];
                    break;
                case 1:
                    this.rent1H = rentValues[i];
                    break;
                case 2:
                    this.rent2H = rentValues[i];
                    break;
                case 3:
                    this.rent3H = rentValues[i];
                    break;
                case 4:
                    this.rent4H = rentValues[i];
                    break;
                case 5:
                    this.rentHotel = rentValues[i];
                    break;
            }
        }
        this.ownedBy = owner;
        this.mortgageValue = mortgageValue;
        this.houses = houses;
        this.mortgaged = mortgaged;
    }
    @Override
    public String performAction(Player currentPlayer, Board board){
        // if player balance is negative after paying, then give them option to mortgage properties, or declare bankruptcy
        if (this.getOwner().equals(currentPlayer)){
            return "Landed on a property you own";
        } else {
            currentPlayer.pay(this.ownedBy, this.getRent());
            return "Paid $" + Integer.toString(this.getRent()) + " to " + this.ownedBy.getName();
        }
    }

    @Override
    public String getType() {
        return null;
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

        switch (this.colour) {
            case "Railroad":
                return 100;
            case "Utility":
                return 50;
            default:
                return this.getHousesRent();
        }
    }

    private int getHousesRent() {
        switch (this.houses) {
            case 1:
                return this.rent1H;
            case 2:
                return this.rent2H;
            case 3:
                return this.rent3H;
            case 4:
                return this.rent4H;
            case 5:
                return this.rentHotel;
            default:
                return this.rent;
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

    public boolean addHouse(Player currentPlayer, int houses){
        if (currentPlayer.getMoney() >= getHouseCost() * houses){
            this.houses += houses;
            currentPlayer.pay(getHouseCost());
            return true;
        } else {
            return false;
        }
    }

}

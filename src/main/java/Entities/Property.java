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

    public Property (String name, String colour, int cost, int houseCost, int rent,
                     int rent1H, int rent2H, int rent3H, int rent4H, int rentHotel,
                     int mortgageValue) {
        this.name = name;
        this.houseCost = houseCost;
        this.colour = colour;
        this.cost = cost;
        this.rent = rent;
        this.rent1H = rent1H;
        this.rent2H = rent2H;
        this.rent3H = rent3H;
        this.rent4H = rent4H;
        this.rentHotel = rentHotel;
        this.ownedBy = null;
        this.mortgageValue = mortgageValue;
        this.houses = 0;
    }

    public Property (String name, String colour, int cost, int houseCost, int rent,
                     int rent1H, int rent2H, int rent3H, int rent4H, int rentHotel,
                     Player owner, int mortgageValue, int houses) {
        this.name = name;
        this.colour = colour;
        this.cost = cost;
        this.houseCost = houseCost;
        this.rent = rent;
        this.rent1H = rent1H;
        this.rent2H = rent2H;
        this.rent3H = rent3H;
        this.rent4H = rent4H;
        this.rentHotel = rentHotel;
        this.ownedBy = owner;
        this.mortgageValue = mortgageValue;
        this.houses = houses;
    }

    public String performAction(Player currentPlayer){
        // set flag for player to know which branch of game logic tree we go down (property)
        // if player balance is negative after paying, then give them option to mortgage properties, or declare bankruptcy
        if (this.getOwner() == null){
            // need to send back an option to get player input on if they will purchase the property or auction it
            boolean purchaseBoolean = purchaseOrAuction();
            if (purchaseBoolean) {
                currentPlayer.pay(this.cost);
                currentPlayer.addProperty(this.name);
                this.ownedBy = currentPlayer;
            } else {
                Object[] highestOffer = displayAuctionMenu();
                Player newOwner = (Player) highestOffer[0];
                int offer = (Integer) highestOffer[1];
                newOwner.pay(offer);
                newOwner.addProperty(this.name);
                this.ownedBy = newOwner;
            }
        } else {
            currentPlayer.pay(this.ownedBy, this.getRent());
        }

        return "";
    }

    public Player getOwner(){
        return this.ownedBy;
    }

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
        if (this.colour.equals("Railroad")){
            return 100;
        } else if (this.colour.equals("Utility")) {
            return 50;
        } else if (this.houses==0){
            return rent;
        } else if (this.houses==1){
            return rent1H;
        } else if (this.houses==2){
            return rent2H;
        } else if (this.houses==3){
            return rent3H;
        } else if (this.houses==4){
            return rent4H;
        } else if (this.houses==5){
            return rentHotel;
        }
        return 0; // there was an error if we return 0
    }

    public int getHouseCost(){
        return this.houseCost;
    }

    public int getMortgageValue(){
        return this.mortgageValue;
    }

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

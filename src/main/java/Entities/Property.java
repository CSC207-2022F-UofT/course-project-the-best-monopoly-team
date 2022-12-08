package Entities;

import UseCases.Logic.PlayerLogic;

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

    /**
     * A constructor method for new Property instances
     * @param name the name of this property
     * @param colour the colour of this property
     * @param cost the cost to buy this property
     * @param houseCost the cost to build one house on this property
     * @param rentValues an integer list of rent for staying on this property, where the index correlates to the number
     *                   houses built on this property
     * @param owner the owner of this property
     * @param mortgageValue the mortgage value for this property
     * @param houses the number of houses currently built on this property
     * @param mortgaged a boolean determining whether this property is mortgaged
     */
    public Property (String name, String colour, int cost, int houseCost, int[] rentValues,
                     Player owner, int mortgageValue, int houses, boolean mortgaged) {
        this.name = name;
        this.colour = colour;
        this.cost = cost;
        this.houseCost = houseCost;
        for (int i = 0; i < 6; i++){
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

    /**
     * Sets the number of houses on this property
     * @param houses number of houses on this property
     */
    public void setHouses(int houses){
        this.houses = houses;
    }

    /**
     * Gets the owner of this property
     * @return the player who owns this property
     */
    public Player getOwner(){
        return this.ownedBy;
    }

    /**
     * Sets the owner of this property
     * @param player the player who owns this property
     */
    public void setOwner(Player player){ownedBy = player;}

    /**
     * Gets the property name
     * @return the name of the property
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the colour of the property
     * @return the colour correlated to this property
     */
    public String getColour(){
        return this.colour;
    }

    /**
     * Gets the buying price for this property
     * @return the price to buy this property
     */
    public int getPrice(){
        return this.cost;
    }

    /**
     * Gets the current rent for this property
     * @return the current rent players (beside the owner) need to pay when landing on this property
     */
    public int getRent(){
        switch (this.colour) {
            case "Railroad":
                return 100;
            case "Utility":
                return 50;
            default:
                return this.getHousesRent(this.houses);
        }
    }

    /**
     * Gets the current rent for this property based on the given amount of houses. Method specifically
     * used for saving function.
     * @param houses amount of houses on this property
     * @return a number indicating the rent cost
     */
    public int getRentSave(int houses){
        return getHousesRent(houses);
    }
    /**
     * Gets the current rent for this property, where this property isn't a Railroad or Utility property
     * @return the current rent players (beside the owner) need to pay when landing on this property, based on the
     * number of houses built on it
     */
    private int getHousesRent(int house) {
        switch (house) {
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

    /**
     * Gets the cost to build a house on this property
     * @return the cost to build a house on this property
     */
    public int getHouseCost(){
        return this.houseCost;
    }

    /**
     * Gets the mortgage value of this property
     * @return the mortgage value of this property
     */
    public int getMortgageValue(){
        return this.mortgageValue;
    }

    /**
     * Gets the mortgage status of this property, that is whether it has been mortgaged
     * @return a boolean showing true if this property has been mortgaged, and false otherwise
     */
    public boolean getMortgageStatus(){return this.mortgaged;}

    /**
     * Sets the mortgage status for this property
     * @param status the mortgage status to give this property
     */
    public void setMortgageStatus(boolean status){this.mortgaged = status;}

    /**
     * Gets the number of houses on this property
     * @return the number of houses built on this property
     */
    public int getHouses(){
        return this.houses;
    }

    /**
     * This method allows a player to build houses on legible properties
     * @param currentPlayer the player try to build the houses
     * @param houses the number of houses that the player wishes to build
     * @return a boolean determining whether the houses were built successfully
     */
    public String addHouse(Player currentPlayer, int houses) {
        PlayerLogic logic = new PlayerLogic(currentPlayer);
        if (!currentPlayer.getProperties().contains(this)) {
            return ("not owned");
        } else if (!logic.ownedPropertySets().contains(this.colour)) {
            return ("not owned set");
        } else if (currentPlayer.getMoney() < this.houseCost * houses) {
            return ("not enough money");
        } else {
            this.houses += houses;
            currentPlayer.pay(getHouseCost());
            if (this.houses == 5) {
                return ("hotel");
            } else {
                return ("house");
            }
        }
    }

    @Override
    public String getType() {
        return "Property";
    }
}

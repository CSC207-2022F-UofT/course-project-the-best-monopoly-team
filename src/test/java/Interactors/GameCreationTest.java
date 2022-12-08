package Interactors;

import Entities.Board;
import Entities.Cell;
import Entities.Player;
import Entities.Property;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import UseCases.GameCreation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class GameCreationTest {

    @Test
    void testCreateNewGame() throws IOException {
        // create a new game
        // assert that all the player instance attributes match default expected values
        // assert the board cells match expected value
        File file = new File("src/save/properties.txt");
        LoadAccess load = new LoadFile(file);

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Player01");
        playerNames.add("Player02");
        playerNames.add("Player03");
        ArrayList<String[]> properties = load.loadProperties();

        GameCreation gameCreator = new GameCreation();
        Board newGame = gameCreator.createNewBoard(playerNames, properties);

        List<Player> returnedPlayers = newGame.getPlayers();
        List<Cell> returnedCells = newGame.getCells();

        //create expected player values
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Player01"));
        expectedPlayers.add(new Player("Player02"));
        expectedPlayers.add(new Player("Player03"));

        for (int i = 0; i < returnedPlayers.size(); i++){
            assert expectedPlayers.get(i).getName().equals(returnedPlayers.get(i).getName());
            assert expectedPlayers.get(i).getMoney() == returnedPlayers.get(i).getMoney();
            assert expectedPlayers.get(i).getJailCards() == returnedPlayers.get(i).getJailCards();
            assert expectedPlayers.get(i).getPosition() == returnedPlayers.get(i).getPosition();
            assert expectedPlayers.get(i).isInJail() == returnedPlayers.get(i).isInJail();
        }

        //create expected cell values
        ArrayList<Cell> cellProperties = gameCreator.parsePropertyData(properties);
        List<Cell> expectedCells = gameCreator.createCells(cellProperties, cellProperties);

        for (int j = 0; j < returnedCells.size(); j++) {
            assert returnedCells.get(j).getType().equals(expectedCells.get(j).getType());
        }
    }

    @Test
    void testCreateSavedGame() throws IOException {
        // create a saved game
        // assert that the player instance attributes match the given player data
        // assert the board cells match the owned properties of the players
        // create a new game
        // assert that all the player instance attributes match default expected values
        // assert the board cells match expected value
        File file = new File("src/save/properties.txt");
        LoadAccess load = new LoadFile(file);
        ArrayList<ArrayList<String[]>> gameData = new ArrayList<>();

        ArrayList<String[]> playerData = new ArrayList<>();
        playerData.add(new String[] {"Player01", "100", "false", "1", "15"});
        playerData.add(new String[] {"Mediterranean Avenue","Brown","60","50","2","10","30","90","160","250","Player01","30","0","true"});
        playerData.add(new String[] {"Illinois Avenue","Red","240","150","20","100","300","750","925","1100","Player01","120","0","false"});
        playerData.add(new String[] {"Player02", "200", "true", "0", "10"});
        playerData.add(new String[] {"Boardwalk","Dark Blue","400","200","50","200","600","1400","1700","2000","Player02","200","0","false"});
        ArrayList<String[]> mainStates = new ArrayList<>();
        mainStates.add(new String[] {"1", "2", "3", "4", "5"});

        gameData.add(playerData);
        gameData.add(mainStates);
        ArrayList<String[]> properties = load.loadProperties();

        GameCreation gameCreator = new GameCreation();
        Board savedGame = gameCreator.createSavedBoard(gameData, properties);

        List<Player> returnedPlayers = savedGame.getPlayers();
        List<Cell> returnedCells = savedGame.getCells();

        //create expected player values
        ArrayList<Player> expectedPlayers = new ArrayList<>();
        Player expectedPlayer01 = new Player("Player01", 100, false, 1, 15);
        int[] medAvenueRent = new int[]{2,10,30,90,160,250};
        Property medAvenue = new Property ("Mediterranean Avenue","Brown",60,50,medAvenueRent,expectedPlayer01,30,0,true);
        int[] illAvenueRent = new int[]{20,100,300,750,925,1100};
        Property illAvenue = new Property ("Illinois Avenue","Red",240,150,illAvenueRent,expectedPlayer01,120,0,false);
        Player expectedPlayer02 = new Player("Player02", 200, true, 0, 10);
        int[] bWalkRent = new int[]{50,200,600,1400,1700,2000};
        Property bWalk = new Property ("Boardwalk","Dark Blue",400,200,bWalkRent,expectedPlayer02,200,0,false);
        expectedPlayer01.addProperty(medAvenue);
        expectedPlayer01.addProperty(illAvenue);
        expectedPlayer02.addProperty(bWalk);

        expectedPlayers.add(expectedPlayer01);
        expectedPlayers.add(expectedPlayer02);

        for (int i = 0; i < returnedPlayers.size(); i++){
            assert expectedPlayers.get(i).getName().equals(returnedPlayers.get(i).getName());
            assert expectedPlayers.get(i).getMoney() == returnedPlayers.get(i).getMoney();
            assert expectedPlayers.get(i).getJailCards() == returnedPlayers.get(i).getJailCards();
            assert expectedPlayers.get(i).getPosition() == returnedPlayers.get(i).getPosition();
            assert expectedPlayers.get(i).isInJail() == returnedPlayers.get(i).isInJail();

            ArrayList<Property> expectedProperties = expectedPlayers.get(i).getProperties();
            ArrayList<Property> returnedProperties = returnedPlayers.get(i).getProperties();
            for (int k = 0; k < returnedProperties.size(); k++){
                // assert property and property owner are the same for expected and returned properties
                assert expectedProperties.get(k).getName().equals(returnedProperties.get(k).getName());
                assert expectedProperties.get(k).getOwner().getName().equals(returnedProperties.get(k).getOwner().getName());
            }
        }

        //create expected cell values
        ArrayList<Cell> cellProperties = gameCreator.parsePropertyData(properties);
        ArrayList<Cell> propertiesSoFar = new ArrayList<>();
        propertiesSoFar.add(medAvenue);
        propertiesSoFar.add(illAvenue);
        propertiesSoFar.add(bWalk);
        List<Cell> expectedCells = gameCreator.createCells(propertiesSoFar, cellProperties);

        for (int j = 0; j < returnedCells.size(); j++) {
            assert returnedCells.get(j).getType().equals(expectedCells.get(j).getType());
            if (returnedCells.get(j).getType().equals("Property")){
                Property currCell = (Property) returnedCells.get(j);
                if (currCell.getOwner() != null) {
                    Property expectedCell = (Property) expectedCells.get(j);
                    assert currCell.getOwner().getName().equals(expectedCell.getOwner().getName());
                }
            }
        }

    }
}
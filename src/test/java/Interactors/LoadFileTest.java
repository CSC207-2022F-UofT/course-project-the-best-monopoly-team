package Interactors;

import Persistence.LoadAccess;
import Persistence.LoadFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class LoadFileTest {
    File properties = new File("src/save/properties.txt");
    File cards = new File("src/save/cards.txt");
    LoadAccess load = new LoadFile(properties);


    @Test
    void testLoadGame() throws FileNotFoundException {
        // create a savedGame
        // assert that the values of the return arraylist matches expected string
        LoadAccess load = new LoadFile(new File("src/gameData/test.txt"));
        ArrayList<ArrayList<String[]>> loadedData = load.loadGame();
        ArrayList<String[]> playerData = loadedData.get(0);
        ArrayList<String[]> mainStates = loadedData.get(1);

        ArrayList<String[]> expectedPlayer = new ArrayList<>();
        expectedPlayer.add(new String[] {"Player01", "100", "false", "1", "10"});
        expectedPlayer.add(new String[] {"Mediterranean Avenue","Brown","60","50","2","10","30","90","160","250","Player01","30","0","true"});
        expectedPlayer.add(new String[] {"Illinois Avenue","Red","240","150","20","100","300","750","925","1100","Player01","120","0","false"});
        expectedPlayer.add(new String[] {"Player02", "200", "true", "0", "10"});
        expectedPlayer.add(new String[] {"Boardwalk","Dark Blue","400","200","50","200","600","1400","1700","2000","Player02","200","0","false"});
        ArrayList<String[]> expectedStates = new ArrayList<>();
        expectedStates.add(new String[] {"1", "2", "3", "4", "5"});

        for (int i = 0; i < playerData.size(); i++){
            assert Arrays.equals(playerData.get(i), expectedPlayer.get(i));
        }

        assert Arrays.equals(mainStates.get(0), expectedStates.get(0));
    }

    @Test
    void testCheckSaves() {
        // search save directory and assert the return text to match
        // "cards.txt, properties.txt"
        String[] expectedReturn = new String[] {"cards.txt", "properties.txt"};

        String path = new File("src/save").getAbsolutePath();
        File save = new File(path);
        String[] actualReturn = save.list();
        assert Arrays.equals(actualReturn, expectedReturn);
    }

    @Test
    void testLoadProperties() throws FileNotFoundException {
        // call loadProperties
        // assert each property "instance" matches the "expected" values
        // (a hardcoded array of all property instances containing expected values)
        // we hardcode the expected array instead of looping through the file
        // to test that the loop logic implemented in loadProperties works
        ArrayList<String[]> expected = new ArrayList<>();
        expected.add(new String[]{"Mediterranean Avenue","Brown","60","50","2","10","30","90","160","250","30","0"});
        expected.add(new String[]{"Baltic Avenue","Brown","60","50","4","20","60","180","320","450","30","0"});
        expected.add(new String[]{"Reading Railroad","Railroad","200","0","25","50","100","200","0","0","100","0"});
        expected.add(new String[]{"Oriental Avenue","Light Blue","100","50","6","30","90","270","400","550","50","0"});
        expected.add(new String[]{"Vermont Avenue","Light Blue","100","50","6","30","90","270","400","550","50","0"});
        expected.add(new String[]{"Connecticut Avenue","Light Blue","120","50","8","40","100","300","450","600","60","0"});
        expected.add(new String[]{"St. Charles Place","Pink","140","100","10","50","150","450","625","750","70","0"});
        expected.add(new String[]{"Electric Company","Utility","150","0","0","0","0","0","0","0","75","0"});
        expected.add(new String[]{"States Avenue","Pink","140","100","10","50","150","450","625","750","70","0"});
        expected.add(new String[]{"Virginia Avenue","Pink","160","100","12","60","180","500","700","900","80","0"});
        expected.add(new String[]{"Pennsylvania Railroad","Railroad","200","0","25","50","100","200","0","0","100","0"});
        expected.add(new String[]{"St. James Place","Orange","180","100","14","70","200","550","750","950","90","0"});
        expected.add(new String[]{"Tennessee Avenue","Orange","180","100","14","70","200","550","750","950","90","0"});
        expected.add(new String[]{"New York Avenue","Orange","200","100","16","80","220","600","800","1000","100","0"});
        expected.add(new String[]{"Kentucky Avenue","Red","220","150","18","90","250","700","875","1050","110","0"});
        expected.add(new String[]{"Indiana Avenue","Red","220","150","18","90","250","700","875","1050","110","0"});
        expected.add(new String[]{"Illinois Avenue","Red","240","150","20","100","300","750","925","1100","120","0"});
        expected.add(new String[]{"B&O Railroad","Railroad","200","0","25","50","100","200","0","0","100","0"});
        expected.add(new String[]{"Atlantic Avenue","Yellow","260","150","22","110","330","800","975","1150","130","0"});
        expected.add(new String[]{"Ventnor Avenue","Yellow","260","150","22","110","330","800","975","1150","130","0"});
        expected.add(new String[]{"Water Works","Utility","150","0","0","0","0","0","0","0","75","0"});
        expected.add(new String[]{"Marven Gardens","Yellow","280","150","24","120","360","850","1025","1200","0","140"});
        expected.add(new String[]{"Pacific Avenue","Green","300","200","26","130","390","900","1100","1275","0","150"});
        expected.add(new String[]{"South Carolina Avenue","Green","300","200","26","130","390","900","1100","1275","150","0"});
        expected.add(new String[]{"Pennsylvania Avenue","Green","320","200","28","150","450","1000","1200","1400","160","0"});
        expected.add(new String[]{"Kings Cross","Railroad","200","0","25","50","100","200","0","0","100","0"});
        expected.add(new String[]{"Park Place","Dark Blue","350","200","35","175","500","1100","1300","1500","175","0"});
        expected.add(new String[]{"Boardwalk","Dark Blue","400","200","50","200","600","1400","1700","2000","200","0"});

        ArrayList<String[]> returnedProperties = load.loadProperties();
        for (int i = 0; i < returnedProperties.size(); i++){
            assert Arrays.equals(returnedProperties.get(i), expected.get(i));
        }
    }

    @Test
    void testLoadCards() throws IOException {
        // call loadCards
        // assert that the return string matches expect strings
        // (a hardcoded string of all card values)
        List<String> expected = new ArrayList<>();
        expected.add("chance:Advance:advance:0");
        expected.add("chance:Bank pays you $50 dividend:getPaid:50");
        expected.add("chance:Get out of jail free:getOutOfJailCard:0");
        expected.add("chance:Speeding fine, pay $15:pay:15");
        expected.add("chance:Pay each player $10:payAll:10");
        expected.add("chance:Building loan matures, collect $150:getPaid:150");
        expected.add("chance:Go to jail:goToJail:0");
        expected.add("comchest:Advance:advance:200");
        expected.add("comchest:Bank error in your favour($200):getPaid:200");
        expected.add("comchest:Doctor's fee($50):pay:50");
        expected.add("comchest:Collect $50 from sale of stock:getPaid:50");
        expected.add("comchest:Get out of jail free:getOutOfJailCard:0");
        expected.add("comchest:Holiday fund matures, collect $100:getPaid:100");
        expected.add("comchest:Income tax refund, collect $20:getPaid:20");
        expected.add("comchest:It is your birthday collect 10$ from each player:getPaid:10");
        expected.add("comchest:Life insurance matures, collect $100:getPaid:100");
        expected.add("comchest:Pay hospital fees, pay $100:pay:100");
        expected.add("comchest:Pay school fees, pay $50:pay:50");
        expected.add("comchest:Receive $25 consultancy fee:getPaid:25");
        expected.add("comchest:You have won second prize in a beauty contest, collect $10:getPaid:10");
        expected.add("comchest:Pay each player $20:payAll:20");
        expected.add("comchest:You inherit $100:getPaid:100");
        expected.add("jail:Pay child support($100):pay:100");
        expected.add("jail:Pay the guards($20):pay:20");
        expected.add("jail:Pay the warden($50):pay:50");
        expected.add("jail:Pay for your mom's living expenses($100):pay:100");
        expected.add("jail:Pay for extra benefits in jail($25):pay:25");

        List<String> returnedCards = load.loadCards(cards);
        for (int i = 0; i < returnedCards.size(); i++) {
            assert returnedCards.get(i).equals(expected.get(i));
        }
    }
}
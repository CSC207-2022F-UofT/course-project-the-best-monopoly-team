package Entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ActionSpace extends Cell {
    private final HashMap<String, ArrayList<Object>> jailCards;
    private final HashMap<String, ArrayList<Object>> comChestCards;
    private final HashMap<String, ArrayList<Object>> chanceCards;
    private final String type;

    /**
    *   The constructor class for ActionSpace.
    *
    * @param type   this corresponds to the type of the ActionSpace it is, this includes jailCards that the user can
    *               draw for every single turn that they are in the jail. Also, this includes chanceCards and ComCards
    *               that is drawn when the player lands on the respective action space.
    * @throws IOException   When we have time we should have proper throw and catch statements.
    */
    public ActionSpace(String type) throws IOException {
//       /* Assigning type os that when we land on these corresponding cells we can just get the type and draw from the
//       corresponding cell*/
        this.type = type;
//
        // loadGameFiles returns a list with 3 HashMaps that are the corresponding HashMaps for the different cards
        this.jailCards = loadGameFiles().get(0);
        this.comChestCards = loadGameFiles().get(1);
        this.chanceCards = loadGameFiles().get(2);
        // Setting the private variables equal to the HashMaps that we created

    }

    /**
     * This
     * @return this returns the type of the actionSpace which includes jail, chance and comchest.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returning the jailCards HashMap, mostly for testing purposes and does not really add to the implementation of the
     * game.
     * @return this returns the full JailCard HashMap.
     */
    public HashMap<String, ArrayList<Object>> getJailCards() {
        return this.jailCards;
    }

    /**
     * Returning the comChest HashMap, mostly for testing purposes and does not really add to the implementation of the
     * game.
     * @return this returns the full comChest HashMap.
     */
    public HashMap<String, ArrayList<Object>> getComChestCards() {
        return this.comChestCards;
    }

    /**
     * Returning the chance HashMap, mostly for testing purposes and does not really add to the implementation of the
     * game.
     * @return this returns the full chance HashMap.
     */
    public HashMap<String, ArrayList<Object>> getChanceCards() {
        return this.chanceCards;
    }

    /**
     *  This method loads chestcomjail.txt from the GameFile folder
     * @return returns the list that then gets called in the constructor method and assigned to the proper HashMaps
     * @throws IOException When we have time we should have proper throw and catch statements.
     */
    public List<HashMap<String, ArrayList<Object>>> loadGameFiles() throws IOException {

        /* Initialising the different Card HashMaps as we want to set these to the private instance attributes later*/
        HashMap<String, ArrayList<Object>> jailCards = new HashMap<>();
        HashMap<String, ArrayList<Object>> comChestCards = new HashMap<>();
        HashMap<String, ArrayList<Object>> chanceCards = new HashMap<>();
        List<HashMap<String, ArrayList<Object>>> list = Arrays.asList(jailCards, comChestCards, chanceCards);
//      // Reading from the game files
        List<String> actions = Files.readAllLines(Paths.get("src/main/GameFile/chestcomjail.txt"));
        // load in from the GameFiles
        for (String lines: actions) {
            // String splitting each line's words and data with the : regrex
            String[] line = lines.split(":");
            String action = line[1]; // index 0 is the type of card that it is (chance, comchest, jail)
            String actionType = line[2];
            Integer dollarAmount = Integer.valueOf(line[3]);
            if (Objects.equals(line[0], "chance")) {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                chanceCards.put(action, lst); // putting this into the chanceCard variable
            } else if (Objects.equals(line[0], "comchest")) {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                comChestCards.put(action, lst); // putting this into the comChestCards variable
            } else {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                jailCards.put(action, lst); // putting this into the jailCards variable
            }
        }
        return list;
    }

    /**
     * This method gets the Card from the correct hashMap that is based on the current ActionSpaces type
     * @param action this is the action that we want to get which is just a string value that acts as a string
     * @return this returns an array list which is the value of within the hashmap with the actionType, and some
     *          numerical value if there is any associated, otherwise it would be 0.
     */
    public ArrayList<Object> getCard(String action) {
        String type = getType();
        if (Objects.equals(type, "jail")) {
            return this.jailCards.get(action);
        } else if (Objects.equals(type, "chance")) {
            return this.chanceCards.get(action);
        } else {
            return this.comChestCards.get(action);
        }
    }

    /**
     * This function takes the player that the action is to be performed on (can be removed if you want),
     * actionType just tells if its advance, getPaid, pay or, getOutOfJailCard.
     * amount is the number associated to the actions if appropriate.
     * action is just the string of the description on the action card.
     * For advance, it automatically generates a number and then calculates the position that it would go to and checks
     * if it ever passes the Go
     * @param player the corresponding player, so that it could update values using setter methods within player.
     * @param board the board to update player position.
     * @return the returns value is just the description, this would be passed so that it can be displayed to the user.
     */
    @Override
    public String performAction(Player player, Board board) {
        // Generating random card
        ArrayList<Object> generatedCard = getCard(generateRandomCard());
        // Getting the action property of the String
        String action = String.valueOf(generatedCard.get(0));
        //Getting the actionType
        String actionType = String.valueOf(generatedCard.get(1));
        //Getting the number associated with the action if any
        Integer amount = (Integer) generatedCard.get(2);

        if (Objects.equals(actionType, "advance")){
            // Generating a random number of steps for the user to go forward
            int randomNumberOfSteps = new Random().nextInt(39);
            // moves the player
            player.move(randomNumberOfSteps);
            //updates the players position on the board
            board.updatePlayerPosition(player);
            // returns a new action string as the action string in the game file is just advance
            action = action + " " + randomNumberOfSteps + " steps.";
            // checks the position of if they have pass go
            if (player.getPosition() == 0 || player.getPosition() + randomNumberOfSteps > 40) {
                // if the player did pass go then add 200 dollars to their balance
                player.changeMoney(200);
            }
        } else if(Objects.equals(actionType, "getPaid")) {
            player.changeMoney(amount);
        } else if (Objects.equals(actionType, "pay")) {
            player.pay(amount);
        } else if (Objects.equals(actionType, "payAll")) {
            for (int i = 0; i < board.getPlayers().length; i++) {
                player.pay(board.getPlayers()[i], amount);
            }
        } else if (Objects.equals(actionType, "goToJail")) {
            player.changeJailStatus();
        } else {
                player.setJailCards(player.getJailCards() + 1);
            }
            return action;
        }

    /**
     * This method generates a random card from the corresponding list
     * @return returns the key value of the card so that this method can be called to get the value of this random card
     */
    public String generateRandomCard() {
        // Initialise String object called card to store the return value for each case
        String card;
        // Getting the type of ActionSpace that the player is currently on
        String type = getType();
        if (Objects.equals(type, "jail")) {
            List<String> keyList = new ArrayList<>(jailCards.keySet());
            int random_index = new Random().nextInt(keyList.size());
            card = String.valueOf(jailCards.get(keyList.get(random_index)));
        } else if (Objects.equals(type, "chance")) {
            List<String> keyList = new ArrayList<>(chanceCards.keySet());
            int random_index = new Random().nextInt(keyList.size());
            card = String.valueOf(chanceCards.get(keyList.get(random_index)));
        } else {
            List<String> keyList = new ArrayList<>(comChestCards.keySet());
            int random_index = new Random().nextInt(keyList.size());
            card = String.valueOf(comChestCards.get(keyList.get(random_index)));
        }
        return card;

    }

}

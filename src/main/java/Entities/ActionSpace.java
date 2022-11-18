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

    public ActionSpace(String type) throws IOException {
//       /* Assigning type os that when we land on these corresponding cells we can just get the type and draw from the
//       corresponding cell*/
        this.type = type;
//
        this.jailCards = loadGameFiles().get(0);
        this.comChestCards = loadGameFiles().get(1);
        this.chanceCards = loadGameFiles().get(2);
        // Setting the private variables equal to the HashMaps that we created

    }

    // Getting the type of action space so that we can look in the correct HashMap
    public String getType() {
        return this.type;
    }

    public HashMap<String, ArrayList<Object>> getJailCards() {
        return this.jailCards;
    }

    public HashMap<String, ArrayList<Object>> getComChestCards() {
        return this.comChestCards;
    }

    public HashMap<String, ArrayList<Object>> getChanceCards() {
        return this.chanceCards;
    }

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

    // Getting the Card that is associated with the certain action
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

    @Override
    public String performAction(Player player, Board board) {
        // get the current type of the current ActionSpace that the user is on
        String type = getType();
        // Generating random card
        ArrayList<Object> generatedCard = getCard(generateRandomCard());
        // Getting the action property of the String
        String action = String.valueOf(generatedCard.get(0));
        //Getting the actionType
        String actionType = String.valueOf(generatedCard.get(1));
        //Getting the number associated with the action if any
        Integer amount = (Integer) generatedCard.get(2);

        //This function takes the player that the action is to be performed on (can be removed if you want),
        //actionType just tells if its advance, getPaid, pay or, getOutOfJailCard.
        //amount is the number associated to the actions if appropriate. This would be 0 if advance to go or the
        //cell number. Could be the amount to be received or paid.
        //action is just the string of the description on the action card.
        //it just returns action after performing the action.
        if (Objects.equals(actionType, "advance")){
            int randomNumberOfSteps = new Random().nextInt(39);
            player.move(randomNumberOfSteps);
            board.updatePlayerPosition(player);
            action = action + " " + randomNumberOfSteps + " steps.";
            if (player.getPosition() == 0 || player.getPosition() + randomNumberOfSteps > 40) {
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
            player.changeJailStatus(player);
        } else {
                player.setJailCards(player.getJailCards() + 1);
            }
            return action;
        }

    // Randomly selects card from the action space that the player is currently on.
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

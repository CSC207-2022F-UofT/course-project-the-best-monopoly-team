package Entities;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ActionSpace {
    private final HashMap<String, ArrayList<Object>> jailCards;
    private final HashMap<String, ArrayList<Object>> comChestCards;
    private final HashMap<String, ArrayList<Object>> chanceCards;
    private final String type;

    public ActionSpace(String type) throws IOException {
        // calling load cards method
        this.type = type;
        HashMap<String, ArrayList<Object>> jailCards = new HashMap<>();
        HashMap<String, ArrayList<Object>> comChestCards = new HashMap<>();
        HashMap<String, ArrayList<Object>> chanceCards = new HashMap<>();
        List<String> actions = Files.readAllLines(Paths.get("GameFiles/chestcomjail.txt"));
        // load in from the GameFiles
        for (String lines: actions) {
            String[] line = lines.split(":");
            String action = line[1];
            String actionType = line[2];
            Integer dollarAmount = Integer.valueOf(line[3]);
            if (Objects.equals(line[0], "chance")) {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                chanceCards.put(action, lst);
            } else if (Objects.equals(line[0], "comchest")) {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                comChestCards.put(action, lst);
            } else {
                ArrayList<Object> lst = new ArrayList<>();
                lst.add(actionType);
                lst.add(dollarAmount);
                jailCards.put(action, lst);
            }
        }

        this.chanceCards = chanceCards;
        this.jailCards = jailCards;
        this.comChestCards = comChestCards;

    }

    public List<Object> getCard(String action) {
        if (this.type == "jail") {
            return this.jailCards.get(action);
        } else if (this.type == "chance") {
            return this.chanceCards.get(action);
        } else {
            return this.comChestCards.get(action);
        }
    }

    public List<Object> performAction() {
        // get current player
        return getCard(generateRandomCard());
        // Map the key to a function call?
        // Hard code the logic??
    }

    public String generateRandomCard() {

        String card;
        if (Objects.equals(this.type, "jail")) {
            List<String> keyList = new ArrayList<>(jailCards.keySet());
            int random_index = new Random().nextInt(keyList.size());
            card = String.valueOf(jailCards.get(keyList.get(random_index)));
        } else if (Objects.equals(this.type, "chance")) {
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

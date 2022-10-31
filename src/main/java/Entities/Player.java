package Entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    // Represents a player in the game

    public String name;
    public int money;
    public ArrayList<Property> properties;
    public boolean inJail;
    public int jailCards;
    public int position;
    public Map<Integer, Integer> actions;

    public Player(String name){
        this.name = name;
        this.money = 1500;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.jailCards = 0;
        this.position = 0;}

    public void move(int step){
        position += step;
        if (step > 40){
            position -= 40;}
    }

    public String rollDice() {
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
            if (a == b){
                this.rollDice();
            } else {
                this.move(a + b);
                return (a + "\n" + b);
            }
        }
        this.move(a + b);
        return (a + "\n" + b);
    }

    public void buildHouse(Property property){
            properties.add(property);
        }

    public void changeMoney(int change){
        money += change;}

    public void pay(int money){
        this.money -= money;}

    public void pay(Player player, int money){
        this.money -= money;
        player.money += money;}

    public void mortgage(Property property){
        this.properties.remove(property);
//        this.money += property.getMortgageValue();
    }
    public void load(File pw) {
        try{
        BufferedReader br = new BufferedReader(new FileReader(pw));
        String line;
        ArrayList all = new ArrayList<ArrayList>();
        while ((line = br.readLine()) != null){
            ArrayList player = new ArrayList<String>();
            player.add(line);
            if(line == "\n"){
                all.add(player);
            }
        }
        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    private void createPlayer(ArrayList<ArrayList> All){
        for(ArrayList player: All){
            String name = (String) player.get(0);
            Player p = new Player(name);
            p.money = (int)player.get(1);
            p.inJail = (boolean) player.get(2);
            p.jailCards = (int) player.get(3);
            p.position = (int) player.get(4);
            int length = player.size();
            for(int i = 5;i < length;i ++){
                p.properties.add((Property)player.get(i));
            }


        }
    }

    }


package UseCases;

import Entities.Player;

public class PlayerLogic {
    private Player player;

    public PlayerLogic(Player player) {
        this.player = player;
    }

    public String rollDice(int consecutive){
        int max = 6;
        int min = 1;
        int a =  (int) Math.floor(Math.random() * (max - min + 1) + min);
        int b = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (this.player.isInJail() && a == b){
                this.player.setInJail(false);
                this.player.move(a + b);
            }
        else if (! this.player.isInJail() && a != b ){
                this.player.move(a + b);
                return (a + " " + b + "\n");
            }
        else if (! this.player.isInJail() && a == b && (consecutive + 1) < 3){
                return this.rollDice((consecutive + 1));
            }
        else if(! this.player.isInJail() && a == b && (consecutive + 1) == 3){
                // the player goes to jail
                this.player.setInJail(true);
                return (a + " " + b + "\n" + "player goes to jail");
            }
        return (a + " " + b + "\n");
    }
}


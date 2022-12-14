package Interactors;

import java.util.Objects;
import java.util.Random;

import Entities.ActionSpace;
import Entities.Board;
import Entities.Card;
import Entities.Player;
import UseCases.PerformActionSpaceUseCase;

/**
 * This class generates and performs the correct action when a player draws an action card.
 */
public class PerformActionSpaceCardInteractor implements PerformActionSpaceUseCase{
    
    /**
     * Generates a random card from the action space
     * @param actionSpace the action space
     * @return the card
     */
    public Card generateRandomCard(ActionSpace actionSpace) {
        int random_index = new Random().nextInt(actionSpace.getCards().size());
        return actionSpace.getCards().get(random_index);
        
    }

    /**
     * Performs the advance action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String advanceAction(Player player, Card card) {
            String action = card.getAction();
            int randomNumberOfSteps = new Random().nextInt(15);
            player.move(randomNumberOfSteps);
            action = " " + action + " " + randomNumberOfSteps + " steps.";

            if (player.getPosition() == 0 || player.getPosition() + randomNumberOfSteps > 40) {
                player.changeMoney(200);
            }
            return action;
    }

    /**
     * Performs the paid action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String getPaidAction(Player player, Card card) {
        String action = " " + card.getAction();
        player.changeMoney(card.getAmount());
        return action;
    }

    /**
     * Performs the pay action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String payAction(Player player, Card card) {
        String action = " " + card.getAction();
        player.pay(card.getAmount());
        return action;
    }

    /**
     * Performs the pay all players action of the card
     * @param player the player
     * @param board the board
     * @param card the card
     * @return the message
     */
    public String payAllAction(Player player, Board board, Card card) {
        String action = " " + card.getAction();
        for (int i = 0; i < board.getPlayers().size(); i++) {
            player.pay(board.getPlayers().get(i), card.getAmount());
        }
        return action;
    }

    /**
     * Performs the go-to jail action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String goToJailAction(Player player, Card card) {
        String action = " " + card.getAction();
        player.setInJail(true);
        player.setPosition(10);
        return action;
    }

    /**
     * Performs the get out of jail card action of the card, adds one to the users jail cards
     * @param player the player
     * @param card the card
     * @return the message
     */
    public String getOutOfJailAction(Player player, Card card) {
        String action = " " + card.getAction();
        player.setJailCards(player.getJailCards() + 1);
        return action;
    }

    /**
     * Aggregates all the actions of the card and the main perform action method
     * @param player the player
     * @param board the board
     * @return the message
     */
    public String performAction(ActionSpace actionSpace, Player player, Board board) {
        Card card = generateRandomCard(actionSpace);
        String actionType = card.getActionType();
        if (Objects.equals(actionType, "advance")) {
            return advanceAction(player, card);
        } else if (Objects.equals(actionType, "getPaid")) {
            return getPaidAction(player, card);
        } else if (Objects.equals(actionType, "pay")) {
            return payAction(player, card);
        } else if (Objects.equals(actionType, "payAll")) {
            return payAllAction(player, board, card);
        } else if (Objects.equals(actionType, "goToJail")) {
            return goToJailAction(player, card);
        } else {
            return getOutOfJailAction(player, card);
        }
    }

}

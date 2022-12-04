package Interactors;

import java.util.Random;

import Entities.ActionSpace2;
import Entities.Board;
import Entities.Card;
import Entities.Player;
import UseCases.PerformActionSpaceUseCase;

/**
 * GenerateActionSpaceCardUseCase
 */
public class PerformActionSpaceCardInteractor implements PerformActionSpaceUseCase{
    
    /**
     * Generates a random card from the action space
     * @param actionSpace2 the action space
     * @return the card
     */
    private Card generateRandomCard(ActionSpace2 actionSpace2) {
        int random_index = new Random().nextInt(actionSpace2.getCards().size());
        return actionSpace2.getCards().get(random_index);
        
    }

    /**
     * Performs the advance action of the card
     * @param player the player
     * @param board the board
     * @param card the card
     * @return the message
     */
    private String advanceAction(Player player, Board board, Card card) {
            String action = card.getAction();
            int randomNumberOfSteps = new Random().nextInt(39);
            player.move(randomNumberOfSteps);
            board.updatePlayerPosition(player);
            action = new String(action + " " + randomNumberOfSteps + " steps.");

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
    private String getPaidAction(Player player, Card card) {
        String action = card.getAction();
        player.changeMoney(card.getAmount());
        return action;
    }

    /**
     * Performs the pay action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    private String payAction(Player player, Card card) {
        String action = card.getAction();
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
    private String payAllAction(Player player, Board board, Card card) {
        String action = card.getAction();
        for (int i = 0; i < board.getPlayers().size(); i++) {
            player.pay(board.getPlayers().get(i), card.getAmount());
        }
        return action;
    }

    /**
     * Performs the go to jail action of the card
     * @param player the player
     * @param card the card
     * @return the message
     */
    private String goToJailAction(Player player, Card card) {
        String action = card.getAction();
        player.changeJailStatus();
        return action;
    }

    /**
     * Performs the get out of jail card action of the card, adds one to the users jail cards
     * @param player the player
     * @param card the card
     * @return the message
     */
    private String getOutOfJailAction(Player player, Card card) {
        String action = card.getAction();
        player.setJailCards(player.getJailCards() + 1);
        return action;
    }

    /**
     * Aggregates all the actions of the card and the main perform action method
     * @param player the player
     * @param board the board
     * @return the message
     */
    public String performAction(ActionSpace2 actionSpace2, Player player, Board board) {
        Card card = generateRandomCard(actionSpace2);
        String actionType = card.getActionType();
        if (actionType == "advance") {
            return advanceAction(player, board, card);
        } else if (actionType == "getPaid") {
            return getPaidAction(player, card);
        } else if (actionType == "pay") {
            return payAction(player, card);
        } else if (actionType == "payAll") {
            return payAllAction(player, board, card);
        } else if (actionType == "goToJail") {
            return goToJailAction(player, card);
        } else {
            return getOutOfJailAction(player, card);
        }
    }

}

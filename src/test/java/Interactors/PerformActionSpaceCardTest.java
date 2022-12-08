package Interactors;

import Entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PerformActionSpaceCardTest {

    @Test
    void testGetRandomCard() {
        Card card = new Card("test", "test", "pay", 100);
        List<Card> lst = new ArrayList<>();
        lst.add(card);
        HashMap<String, List<Card>> cardList = new HashMap<>();
        cardList.put("test", lst);
        ActionSpace actionSpace = new ActionSpace(cardList);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        Card randomCard = performActionSpaceCardInteractor.generateRandomCard(actionSpace);
        assert Objects.equals(randomCard.getAction(), "test");
    }

    @Test
    void testAdvanceAction() {
        Card card = new Card("test", "test", "advance", 100);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        performActionSpaceCardInteractor.advanceAction(player, card);

        assert player.getPosition() >= 0;
    }

    @Test
    void testGetPaidAction() {
        Card card = new Card("test", "test", "getPaid", 100);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        performActionSpaceCardInteractor.getPaidAction(player, card);

        assert player.getMoney() == 1100;
    }

    @Test
    void testGetOutOfJailAction() {
        Card card = new Card("test", "test", "getOutOfJailCard", 0);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        performActionSpaceCardInteractor.getOutOfJailAction(player, card);

        assert player.getJailCards() == 1;
    }

    @Test
    void testGoToJailAction() {
        Card card = new Card("test", "test", "goToJail", 0);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        performActionSpaceCardInteractor.goToJailAction(player, card);

        assert player.isInJail();
        assert player.getPosition() == 10;
    }

    @Test
    void testPayAllAction() {

        Card card = new Card("test", "test", "payAll", 100);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);
        Player player1 = new Player("test1", 0, false, 0, 0);
        Player player2 = new Player("test2", 0, false, 0, 0);
        Player player3 = new Player("test3", 0, false, 0, 0);

        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, player, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);

        Board board = new Board(playerList, cells);

        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        performActionSpaceCardInteractor.payAllAction(player, board, card);

        assert player.getMoney() == 700;
        assert player1.getMoney() == 100;
        assert player2.getMoney() == 100;
        assert player3.getMoney() == 100;
    }

    @Test
    void testPerformAction() {

        // Creating a card and card deck for the testing actionspace
        Card card = new Card("test", "test", "pay", 100);
        List<Card> lst = new ArrayList<>();
        lst.add(card);
        HashMap<String, List<Card>> cardList = new HashMap<>();
        cardList.put("test", lst);
        ActionSpace actionSpace = new ActionSpace(cardList);

        // Setting up the player so that we can check value after the action is performed
        Player player = new Player("test", 1000, false, 0, 0);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);

        // Setting up cells and properties so that we can create a testing board
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, player, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);

        Board board = new Board(playerList, cells);

        // Testing perform action
        PerformActionSpaceCardInteractor performActionSpaceCardInteractor = new PerformActionSpaceCardInteractor();
        // Checking if it returns the correct string
        performActionSpaceCardInteractor.performAction(actionSpace, player, board);
        // Checking if the player now has the correct amount.
        assert player.getMoney() == 900;

    }


}

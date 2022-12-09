package Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BoardTest {
    @Test
    public void testGetPlayers(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        List<Player> actual = board.getPlayers();
        List<Player> expected = Arrays.asList(playerOne, playerTwo);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testRemovePlayer(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        board.removePlayer(playerOne);
        List<Player> actual = board.getPlayers();
        List<Player> expected = List.of(playerTwo);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testGetCells(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        List<Cell> actual = board.getCells();
        List<Cell> expected = Arrays.asList(cellOne, cellTwo);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testGetCell(){
        Player playerOne = new Player("PlayerOne", 0, false, 0, 1);
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        Cell actual = board.getCell(1);
        Cell expected = board.cells.get(1);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testGetPlayerCell(){
        Player playerOne = new Player("PlayerOne", 0, false, 0, 1);
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        Cell actual = board.getPlayerCell(playerOne);
        Cell expected = cells.get(playerOne.getPosition());
        Assertions.assertEquals(actual, expected);
    }

}

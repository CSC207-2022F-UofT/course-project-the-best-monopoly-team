package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.*;

public class BoardTest {
    @Test
    public void testGetPlayers(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        List<Player> actual = board.getPlayers();
        List<Player> expected = Arrays.asList(playerOne, playerTwo);
        assertEquals(actual, expected);
    }

    @Test
    public void testRemovePlayer(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<Cell>();
        Board board = new Board(players, cells);
        board.removePlayer(playerOne);
        List<Player> actual = board.getPlayers();
        List<Player> expected = List.of(playerTwo);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetCells(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        List<Cell> actual = board.getCells();
        List<Cell> expected = Arrays.asList(cellOne, cellTwo);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetCell(){
        Player playerOne = new Player("PlayerOne", 0, false, 0, 1);
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        Cell actual = board.getCell(1);
        Cell expected = board.cells.get(1);
        assertEquals(actual, expected);
    }


    @Test
    public void testGetPlayerPositions(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        playerOne.move(3);
        playerTwo.move(2);
        board.updatePlayerPosition(playerOne);
        board.updatePlayerPosition(playerTwo);
        Map<Player, Integer> actual = board.getPlayerPositions();
        Map<Player, Integer> expected = new HashMap<Player, Integer>();
        expected.put(playerOne, 3);
        expected.put(playerTwo, 2);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPlayerCell(){
        Player playerOne = new Player("PlayerOne", 0, false, 0, 1);
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        Cell actual = board.getPlayerCell(playerOne);
        Cell expected = cells.get(playerOne.getPosition());
        assertEquals(actual, expected);
    }

    @Test
    public void testUpdatePlayerPosition(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1, 2, 3, 4, 5, 6};
        int [] rentValues2 = new int[] {2, 4, 6, 8, 10, 12};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Board board = new Board(players, cells);
        playerOne.move(3);
        board.updatePlayerPosition(playerOne);
        assert board.playerPositions.get(playerOne) == 3;
    }

}

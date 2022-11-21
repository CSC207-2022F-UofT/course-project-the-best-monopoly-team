package Entities;

import org.junit.jupiter.api.Test;

import java.util.*;

class BoardTest {
    @Test
    void getPlayers(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1,2,3,4,5};
        int [] rentValues2 = new int[] {2,4,6,8,10};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
//        Object[][] propertyData = {{"propertyOne", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerOne, 0, 0, false},
//                {"propertyTwo", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerTwo, 0, 0, false}};
        Board board = new Board(players, cells);
        List<Player> actual = board.getPlayers();
        List<Player> expected = Arrays.asList(new Player("PlayerOne"), new Player("PlayerTwo"));
        assert actual == expected;
    }

    // @Test
    // TODO create test for removePlayer

    @Test
    void getCells(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1,2,3,4,5};
        int [] rentValues2 = new int[] {2,4,6,8,10};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Object[][] propertyData = {{"propertyOne", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerOne, 0, 0, false},
                {"propertyTwo", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerTwo, 0, 0, false}};
        Board board = new Board(players, cells);
        List<Cell> actual = board.getCells();
        List<Cell> expected = Arrays.asList(new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false), new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false));
        assert actual == expected;
    }

    // @Test
    // TODO create test for getCell

    @Test
    void getPlayerPositions(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1,2,3,4,5};
        int [] rentValues2 = new int[] {2,4,6,8,10};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
//        Object[][] propertyData = {{"propertyOne", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerOne, 0, 0, false},
//                {"propertyTwo", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerTwo, 0, 0, false}};
        Board board = new Board(players, cells);
        playerOne.move(3);
        playerTwo.move(2);
        board.updatePlayerPosition(playerOne);
        board.updatePlayerPosition(playerTwo);
        Map<Player, Integer> actual = board.getPlayerPositions();
        Map<Player, Integer> expected = new HashMap<Player, Integer>();
        expected.put(playerOne, 3);
        expected.put(playerTwo, 2);
        assert expected == actual;
    }

    @Test
    void getPlayerCell(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1,2,3,4,5};
        int [] rentValues2 = new int[] {2,4,6,8,10};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Object[][] propertyData = {{"propertyOne", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerOne, 0, 0, false},
                {"propertyTwo", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerTwo, 0, 0, false}};
        Board board = new Board(players, cells);
        List<Cell> actual = board.getCells();
        List<Cell> expected = Arrays.asList(new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                        0, false),
                new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                        0, false));
        assert actual == expected;
    }

    @Test
    void updatePlayerPosition(){
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");
        List<Player> players = new ArrayList<Player>();
        players.add(playerOne);
        players.add(playerTwo);
        int [] rentValues1 = new int[] {1,2,3,4,5};
        int [] rentValues2 = new int[] {2,4,6,8,10};
        Property cellOne = new Property("cellOne", "Colour", 0, 0, rentValues1, playerOne, 0,
                0, false);
        Property cellTwo = new Property("cellTwo", "Colour", 0, 0, rentValues2, playerTwo, 0,
                0, false);
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cellOne);
        cells.add(cellTwo);
        Object[][] propertyData = {{"propertyOne", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerOne, 0, 0, false},
                {"propertyTwo", "Colour", 0, 0, 0, 0, 0, 0, 0, 0, playerTwo, 0, 0, false}};
        Board board = new Board(players, cells);
        playerOne.move(3);
        board.updatePlayerPosition(playerOne);
        assert board.playerPositions.get(playerOne) == 3;
    }

}

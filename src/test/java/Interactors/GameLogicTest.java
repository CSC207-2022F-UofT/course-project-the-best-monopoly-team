package Interactors;

import Entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameLogicTest {

    @Test
    public void testSetCurrentTreeToMaxParent(){
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
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);

        GameLogicTree auctionTree = new GameLogicTree("AuctionTree");
        GameLogicTree lowOption = new GameLogicTree("LowOption");
        GameLogicTree mediumOption = new GameLogicTree("MediumOption");
        GameLogicTree highOption = new GameLogicTree("HighOption");
        GameLogicTree fold = new GameLogicTree("Fold");

        auctionTree.setIsSwitchBlock(true);
        auctionTree.addChild(lowOption);
        auctionTree.addChild(mediumOption);
        auctionTree.addChild(highOption);
        auctionTree.addChild(fold);

        gameLogic.setCurrentTree(auctionTree);

        GameLogicTree expected = (GameLogicTree) gameLogic.getCurrentTree().getMaxParent();
        gameLogic.setCurrentTreeToMaxParent();
        assertEquals(gameLogic.getCurrentTree(), expected);
    }


    @Test
    public void testPerformInput(){
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
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GameLogic gameLogic1 = new GameLogic(playerOne, board);


    }

    @Test
    public void testTraverseCurrentTree(){

    }

    @Test
    public void testHandleTree(){

    }

    @Test
    public void testSetupAuction(){

    }

}

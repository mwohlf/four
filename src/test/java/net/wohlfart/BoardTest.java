package net.wohlfart;

import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;

public class BoardTest {

    @Test
    public void countPossibleMovesSize() {
        int size = 8;
        Board board = new DebugBoard(size);
        Enumeration<Board> enumeration = board.enumeration(FieldState.BLUE);
        int moves = 0;
        while (enumeration.hasMoreElements()) {
            Board nextBoard = enumeration.nextElement();
            moves++;
        }

        Assert.assertEquals(size, moves);
    }


    @Test
    public void countPossibleMoves3() {
        int size = 8;

        Board board = new DebugBoard(size);
        for (int i = 0; i < size; i++) {
            board = board.insert(1, FieldState.BLUE);
        }

        Enumeration<Board> enumeration = board.enumeration(FieldState.BLUE);
        int moves = 0;
        while (enumeration.hasMoreElements()) {
            Board nextBoard = enumeration.nextElement();
            moves++;
        }

        Assert.assertEquals(size - 1, moves);
    }

}

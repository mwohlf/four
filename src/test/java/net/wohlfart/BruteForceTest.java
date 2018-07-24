package net.wohlfart;

import org.junit.Test;

public class BruteForceTest {


    @Test
    public void windWinVertical() {
        int size = 8;

        MoveFinder moveFinder = new BruteForce(1, 4);

        Board board = new DebugBoard(size);
        board = board.insert(1, FieldState.BLUE);
        board = board.insert(1, FieldState.BLUE);
        board = board.insert(1, FieldState.BLUE);


        DebugBoard debugBoard = new DebugBoard(board);

        debugBoard.assertMove(1, 0, FieldState.BLUE);
        debugBoard.assertMove(1, 1, FieldState.BLUE);
        debugBoard.assertMove(1, 2, FieldState.BLUE);
        debugBoard.assertMove(1, 3, FieldState.EMPTY);

        debugBoard = new DebugBoard(moveFinder.bestMove(board, FieldState.BLUE));
        debugBoard.assertMove(1, 3, FieldState.BLUE);
    }


    @Test
    public void winVertical() {
        int size = 8;

        MoveFinder moveFinder = new BruteForce(1, 4);

        Board board = new DebugBoard(size);
        board = board.insert(1, FieldState.BLUE);
        board = board.insert(2, FieldState.BLUE);
        board = board.insert(3, FieldState.BLUE);

        DebugBoard debugBoard = new DebugBoard(board);

        debugBoard.assertMove(1, 0, FieldState.BLUE);
        debugBoard.assertMove(2, 0, FieldState.BLUE);
        debugBoard.assertMove(3, 0, FieldState.BLUE);
        debugBoard.assertMove(4, 0, FieldState.EMPTY);

        debugBoard = new DebugBoard(moveFinder.bestMove(board, FieldState.BLUE));
        debugBoard.assertMove(4, 0, FieldState.BLUE);
    }


    @Test
    public void winDiagonal() {
        int size = 8;

        MoveFinder moveFinder = new BruteForce(1, 4);

        Board board = new DebugBoard(size);
        board = board.insert(1, FieldState.BLUE);

        board = board.insert(2, FieldState.RED);
        board = board.insert(2, FieldState.BLUE);

        board = board.insert(3, FieldState.RED);
        board = board.insert(3, FieldState.RED);
        board = board.insert(3, FieldState.BLUE);

        board = board.insert(4, FieldState.BLUE);
        board = board.insert(4, FieldState.RED);
        board = board.insert(4, FieldState.RED);

        DebugBoard debugBoard = new DebugBoard(board);

        debugBoard.assertMove(4, 3, FieldState.EMPTY);

        debugBoard = new DebugBoard(moveFinder.bestMove(board, FieldState.BLUE));
        debugBoard.assertMove(4, 3, FieldState.BLUE);
    }


    @Test
    public void defendVertical() {
        int size = 8;

        MoveFinder moveFinder = new BruteForce(1, 4);

        Board board = new DebugBoard(size);
        board = board.insert(1, FieldState.RED);
        board = board.insert(1, FieldState.RED);
        board = board.insert(1, FieldState.RED);


        DebugBoard debugBoard = new DebugBoard(board);

        debugBoard.assertMove(1, 0, FieldState.RED);
        debugBoard.assertMove(1, 1, FieldState.RED);
        debugBoard.assertMove(1, 2, FieldState.RED);
        debugBoard.assertMove(1, 3, FieldState.EMPTY);

        debugBoard = new DebugBoard(moveFinder.bestMove(board, FieldState.BLUE));
        debugBoard.assertMove(1, 3, FieldState.BLUE);
    }

}

package net.wohlfart;

import java.util.*;

public class BoardGame {

    final int boardSize = 8;

    final int winSize = 4;

    final FieldState computer = FieldState.BLUE;
    final FieldState human = computer.next();

    final MoveFinder moveFinder = new BruteForce(3, 4);

    public static void main(String[] args) {
        new BoardGame().run();
    }

    private void run() {
        Board board = new Board(boardSize);
        FieldState current = FieldState.RED;
        while (true) {
            System.out.print(board);

            if (current == computer) {
                board = moveFinder.bestMove(board, current);
            } else {
                board = humanMove(board);
            }

            if (board.hasCount(current, winSize)) {
                System.out.print(board);
                System.out.println(" - end - ");
                break;
            }
            current = current.next();
        }
    }

    private Board humanMove(Board board) {
        int column = 0;
        while (true) {
            try {
                System.out.println("enter column for " + human + ": ");
                Scanner scanner = new Scanner(System.in);
                column = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex) {
                // try again
            }
        }
        return board.insert(column, human);

    }

}

package net.wohlfart;

import java.util.*;

public class RandomMove implements MoveFinder {

    final Random random = new Random();

    @Override
    public Board bestMove(Board board, FieldState fieldState) {
        ArrayList<Board> boards = new ArrayList<>();
        final Enumeration<Board> nextMoves = board.enumeration(fieldState);
        while (nextMoves.hasMoreElements()) {
            boards.add(nextMoves.nextElement());
        }

        return boards.get(random.nextInt(boards.size()));
    }

}

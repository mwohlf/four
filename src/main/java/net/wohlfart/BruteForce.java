package net.wohlfart;

import java.util.*;

public class BruteForce implements MoveFinder {

    final Random random = new Random();
    final  HashMap<Board, Float> weights = new HashMap<>();

    private final int lookahead;
    private final int winSize;

    BruteForce(int lookahead, int winSize) {
        this.lookahead = lookahead;
        this.winSize = winSize;
    }

    @Override
    public Board bestMove(Board board, FieldState computer) {

        float bestWeight = Float.NEGATIVE_INFINITY;
        final Set<Board> potentialMoves = new HashSet<>();

        final Enumeration<Board> nextLevel = board.enumeration(computer);
        while (nextLevel.hasMoreElements()) {
            final Board next = nextLevel.nextElement();
            float nextWeight = weight(next, computer, lookahead);
            if (nextWeight > bestWeight) {
                potentialMoves.clear();
                potentialMoves.add(next);
                bestWeight = nextWeight;
            } else if (nextWeight == bestWeight) {
                potentialMoves.add(next);
            }
        }

        return potentialMoves.toArray(new Board[]{})[random.nextInt(potentialMoves.size())];
    }

    // weight a board
    private float weight(Board board, FieldState lastMove, int depth) {

        if (weights.containsKey(board)) {
            return weights.get(board); // already calculated
        } else if (depth == 0) {
            return 0;  // down't go deeper
        }

        if (board.hasCount(lastMove, winSize)) {
            final float weight = Float.POSITIVE_INFINITY;
            weights.put(board, weight);
            return weight;
        }

        float weight = 0f;
        final Enumeration<Board> nextLevel = board.enumeration(lastMove.next());
        while (nextLevel.hasMoreElements()) {
            final Board next = nextLevel.nextElement();
            weight -= weight(next, lastMove.next(), depth - 1);
        }
        return weight;
    }

}

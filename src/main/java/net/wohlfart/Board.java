package net.wohlfart;


import java.util.Arrays;
import java.util.Enumeration;


/**
 * wrapper for a board state,
 * method to find a n-match
 */
public class Board {

    protected final FieldState[][] board;

    Board(int size) {
        this.board = new FieldState[size][size];
        init();
    }

    protected Board(Board original) {
        this.board = clone(original.board);
    }

    private void init() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = FieldState.EMPTY;
            }
        }
    }

    private FieldState[][] clone(FieldState[][] src) {
        FieldState[][] result = new FieldState[src.length][];
        for (int i = 0; i < src.length; i++) {
            result[i] = Arrays.copyOf(src[i], src[i].length);
        }
        return result;
    }

    Enumeration<Board> enumeration(FieldState state) {

        return new Enumeration<Board>() {

            int column = 1;

            @Override
            public boolean hasMoreElements() {
                while (column <= board.length) {
                    if (board[column - 1][0] == FieldState.EMPTY) {
                        return true;
                    }
                    column++;
                }
                return false;
            }

            @Override
            public Board nextElement() {
                assert board[column - 1][0] == FieldState.EMPTY : "internal error board column is full: " + column;
                assert column <= board.length : "board column is invalid: " + column;
                assert column >= 1 : "board column is invalid: " + column;

                Board result = Board.this.insert(column, state);
                column += 1;
                return result;
            }

        };

    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }
        Board thatBoard = (Board) that;
        return Arrays.deepEquals(board, thatBoard.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }


    // return a cloned board with the coin inserted
    Board insert(int column, FieldState state) {
        assert board[column - 1][0] == FieldState.EMPTY : "internal error board column is full: " + column;

        Board clone = new Board(this);

        for (int i = clone.board[column - 1].length - 1; i >= 0 ; i--) {
            if (clone.board[column - 1][i] == FieldState.EMPTY ) {
                clone.board[column - 1][i] = state;
                return clone;
            }
        }
        throw new IllegalStateException("insertion into column " + column + " failed");
    }

    boolean canInsert(int c) {
        int column = c - 1;
        return board[column][0] == FieldState.EMPTY;
    }

    // TODO: improve this
    boolean hasCount(FieldState state, int winSize) {
        boolean found;

        for (int x = 0; x <= board.length - winSize; x++) {
            for (int y = 0; y <= board.length - winSize; y++) {

                if (board[x][y] != state) {
                    continue;
                }


                // try right
                found = true;
                for (int xx = 1; xx < winSize; xx++) {
                    if (board[x + xx][y] != state) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }

                // try down-right
                found = true;
                for (int xy = 1; xy < winSize; xy++) {
                    if (board[x + xy][y + xy] != state) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }

                // try down
                found = true;
                for (int yy = 1; yy < winSize; yy++) {
                    if (board[x][y + yy] != state) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }

            }

            for (int y = board.length - winSize + 1; y < board.length; y++) {

                if (board[x][y] != state) {
                    continue;
                }

                // try right
                found = true;
                for (int xx = 1; xx < winSize; xx++) {
                    if (board[x + xx][y] != state) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return true;
                }

            }
        }

        return false;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                stringBuilder.append(" ");
                stringBuilder.append(board[x][y]);
                stringBuilder.append(" ");
            }
            stringBuilder.append(System.lineSeparator());
        }
        for (int x = 0; x < board.length; x++) {
            stringBuilder.append(String.format("%2s ", (x + 1)));
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }

}

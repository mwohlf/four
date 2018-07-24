package net.wohlfart;

public class DebugBoard extends Board {


    DebugBoard(int size) {
        super(size);
    }

    public DebugBoard(Board board) {
        super(board);
    }

    void assertMove(int column, int row, FieldState state) {

    }

}

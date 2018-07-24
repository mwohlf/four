package net.wohlfart;

public class BroadSearch implements MoveFinder {

    @Override
    public Board bestMove(Board board, FieldState fieldState) {

        return null;
    }

    private class Node {

        Board board;

        Node parent;

        float weight;

    }

}

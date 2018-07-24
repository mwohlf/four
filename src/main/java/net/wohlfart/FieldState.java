package net.wohlfart;

enum FieldState {
    EMPTY('.'),
    RED('o'),
    BLUE('#'),
    ;

    private final char symbol;

    FieldState(char symbol) {
        this.symbol = symbol;
    }

    FieldState next() {
        switch (this) {
            case EMPTY:
                return EMPTY;
            case RED:
                return BLUE;
            case BLUE:
                return RED;
            default:
                return EMPTY;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

}

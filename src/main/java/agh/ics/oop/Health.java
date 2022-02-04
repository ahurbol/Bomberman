package agh.ics.oop;

public enum Health {
    ALL,
    LESS,
    LAST,
    DEAD;

    public Health reduceHealth() {
        if (this == ALL) {
            return LESS;
        }
        if (this == LESS) {
            return LAST;
        }
        if (this == LAST) {
            return DEAD;
        }
        return DEAD;
    }

}

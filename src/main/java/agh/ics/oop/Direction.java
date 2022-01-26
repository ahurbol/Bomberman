package agh.ics.oop;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Vector2d toUnitVector() {
        return switch (this) {
            case UP -> new Vector2d(0, -1);
            case RIGHT -> new Vector2d(1, 0);
            case DOWN -> new Vector2d(0, 1);
            case LEFT -> new Vector2d(-1, 0);
        };
    }
}

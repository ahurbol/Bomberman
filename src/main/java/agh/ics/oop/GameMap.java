package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameMap {
    private final int height = 10;
    private final int width = 10;
    private final Map<Player, Vector2d> players = new LinkedHashMap<>();


    public void putPlayers(Player player1, Player player2) {
        this.players.put(player1, new Vector2d(1, 1));
        this.players.put(player2, new Vector2d(width - 2, height - 2));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean hasSthAt(Vector2d position) {
        return false;
    }

    public void tryMove(Player player, Direction direction) {

    }
}

package agh.ics.oop;

import java.util.*;

public class GameMap {
    private final int height = 11;
    private final int width = 11;
    private final Map<Player, Vector2d> players = new LinkedHashMap<>();
    private final ArrayList<Vector2d> walls = new ArrayList<>();
    private final ArrayList<Vector2d> chests = new ArrayList<>();
    private final ArrayList<Vector2d> bombs = new ArrayList<>();
    private final static Direction[] explosion = {Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT};
    private final ArrayList<Vector2d> nearCorners;

    private  Player player1;
    private  Player player2;
    private  Vector2d positionPlayer1;
    private  Vector2d positionPlayer2;

    private boolean notNearCorners(Vector2d position) {
        return !nearCorners.contains(position);
    }

    public GameMap() {
        nearCorners = new ArrayList<>();
        nearCorners.add(new Vector2d(2,1));
        nearCorners.add(new Vector2d(1,2));
        nearCorners.add(new Vector2d(1,1));
        Vector2d lowerRight = new Vector2d(10, 10);
        nearCorners.add(new Vector2d(lowerRight.x - 2, lowerRight.y - 1));
        nearCorners.add(new Vector2d(lowerRight.x - 1, lowerRight.y - 2));
        nearCorners.add(new Vector2d(lowerRight.x - 1, lowerRight.y - 1));
        Wall wall = new Wall();
        for(int i = 0; i <= height - 1; i++) {
            this.walls.add(new Vector2d(i, 0));
        }
        for(int i = 0; i <= height - 1; i++) {
            this.walls.add(new Vector2d(i, lowerRight.y));
        }
        for(int i = 1; i < width - 1; i++) {
            this.walls.add(new Vector2d(0, i));
        }
        for(int i = 1; i < width - 1; i++) {
            this.walls.add(new Vector2d(lowerRight.x, i));
        }

        for(int i = 2; i < height - 2; i+=2) {
            for(int j = 2; j < width - 2; j+=2) {
                this.walls.add(new Vector2d(i, j));
            }
        }
        Random random = new Random();
        int i = 0;
        while (i < 40) {
            Vector2d position = new Vector2d(random.nextInt(10), random.nextInt(10));
            if (!isOccupied(position) && notNearCorners(position)) {
                Chest chest = new Chest();
                this.chests.add(position);
                i++;
            }
        }
    }

    public void putPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.positionPlayer1 = new Vector2d(1, 1);
        this.positionPlayer2 = new Vector2d(width - 2, height - 2);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

//    public boolean hasSthAt(Vector2d position) {
//        return isOccupied(position) || players.containsValue(position);
//    }

    public boolean isInGameMap(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width - 1, height - 1));
    }

    public boolean canMoveTo(Vector2d position) {
        if (isOccupied(position) || playerAt(position) != null) return false;
        return isInGameMap(position);
    }

    public void tryMove(Player player, Direction direction) {
        if(player == player1) {
            Vector2d position = this.positionPlayer1.add(direction.toUnitVector());
            if(canMoveTo(position))
                positionPlayer1 = position;
        }
       else {
            Vector2d position = this.positionPlayer2.add(direction.toUnitVector());
            if(canMoveTo(position))
                positionPlayer2 = position;
        }
    }
    public Object objectAt(Vector2d position) {
        if(this.walls.contains(position))
            return new Wall();
        if(this.chests.contains(position))
            return new Chest();
        if (this.bombs.contains(position)) {
            return new Bomb();
        }
        return playerAt(position);
    }

    public boolean isOccupied(Vector2d position) {
        return this.walls.contains(position) || this.chests.contains(position) || this.bombs.contains(position);
    }

    public boolean putBomb(Player player, Bomb bomb) {
        Vector2d position;
        if(player1.equals(player))
             position = positionPlayer1;
        else
            position = positionPlayer2;
        if (isOccupied(position)) {
            return false;
        }
        this.bombs.add(position);
        bomb.addPosition(position);
        bomb.addBombObserver(this);
        bomb.place(player);
        return true;
    }

    public Player playerAt(Vector2d position) {
        if(position.equals(positionPlayer1))
            return player1;
        if(position.equals(positionPlayer2))
            return player2;
        return null;
    }

    public void bombExploded(Vector2d position, Player pl) {
        pl.addBomb();
        Player player = playerAt(position);
        if (player != null) {
            player.bombReached();
        }
        for (Direction direction : explosion) {
            Vector2d pos = position.add(direction.toUnitVector());
            destroyChest(pos);
            player = playerAt(pos);
            if (player != null) {
                player.bombReached();
            }
        }
        this.bombs.remove(position);
    }

    public void destroyChest(Vector2d position) {
        this.chests.remove(position);
    }
}

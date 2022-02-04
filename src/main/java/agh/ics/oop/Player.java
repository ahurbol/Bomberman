package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Player {
    private Health health;
    private final GameMap gameMap;
    private final LinkedList<Bomb> bombs = new LinkedList<>();


    public Player(GameMap gameMap) {
        this.gameMap = gameMap;
        this.health = Health.ALL;
        addBomb();
    }

    public void addBomb() {
        Bomb bomb = new Bomb();
        bomb.addBombObserver(gameMap);
        this.bombs.add(bomb);
    }

    public Health getHealth() {
        return this.health;
    }

    public void tryPutBomb() {
        if (!this.bombs.isEmpty()) {
            Bomb bomb = this.bombs.pop();
            if(!this.gameMap.putBomb(this, bomb)) {
                this.bombs.add(bomb);
            }
        }
    }

    public void bombReached() {
        this.health = this.health.reduceHealth();
        if (this.health == Health.DEAD) {
//jak umiera to co
        }
    }

    public boolean equals(Object obj){
        return obj == this;
    }

    public int hashCode() { return Objects.hash(); }
}

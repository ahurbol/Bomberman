package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {
    private Vector2d position;
    private final static Timer timer = new Timer(false);
    private final ArrayList<GameMap> observers = new ArrayList<>();

    public void place(Player pl) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (GameMap observer : observers) {
                    observer.bombExploded(position, pl);
                }
            }
        }, 800);
    }

    public void addPosition(Vector2d position) {
        this.position = position;
    }

    public void addBombObserver(GameMap observer) {
        this.observers.add(observer);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Bomb);
    }

    @Override
    public int hashCode(){
        return Objects.hash();
    }}

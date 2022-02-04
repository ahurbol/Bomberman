package agh.ics.oop;

import java.util.Objects;

public class Chest {
    @Override
    public int hashCode(){
        return Objects.hash();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Chest);
    }
}

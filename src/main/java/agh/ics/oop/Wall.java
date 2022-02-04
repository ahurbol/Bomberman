package agh.ics.oop;

import java.util.Objects;

public class Wall {
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Wall);
    }

    @Override
    public int hashCode(){
        return Objects.hash();
    }
}

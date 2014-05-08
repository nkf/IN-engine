package engine;

import java.util.ArrayList;
import java.util.List;

public class Location {
    public final String name;
    public final List<Actor> actors;

    public Location(String name) {
        this.name = name;
        actors = new ArrayList<Actor>();
    }

    @Override
    public String toString() {
        return name;
    }
}

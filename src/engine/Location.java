package engine;

import java.util.ArrayList;
import java.util.List;

public class Location {
    public final String     Name;
    public final List<Item> items;

    public Location(String name) {
        Name = name;
        items = new ArrayList<Item>();
    }

    @Override
    public String toString() {
        return Name;
    }
}

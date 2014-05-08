package deadgiveaway.location;

import deadgiveaway.items.Item;
import engine.Location;

import java.util.ArrayList;
import java.util.List;

public class Room extends Location {
    public final List<Item> items = new ArrayList<Item>();
    public String description() {
        return "";
    }
    public Room(String name) {
        super(name);
    }
}

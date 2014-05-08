package deadgiveaway.location;

import engine.Item;
import engine.ItemType;
import engine.Location;

import java.util.HashMap;

public class House {
    public static final Location dinnerRoom =   new Room("the dinner room");
    public static final Location toilet =       new Room("the toilet");
    public static final Location bedroom =      new Room("the bedroom");
    public static final Location hall =         new Room("the hall");
    public static final Location kitchen =      new Room("the kitchen");
    public static final Location basement =     new Room("the basement");
    public static final HashMap<Location,Location[]> connections = new HashMap<Location, Location[]>();

    static {
        connections.put(dinnerRoom, new Location[]{ hall, bedroom, kitchen });
        connections.put(toilet,     new Location[]{ hall });
        connections.put(bedroom,    new Location[]{ dinnerRoom });
        connections.put(hall,       new Location[]{ toilet, basement, dinnerRoom });
        connections.put(kitchen,    new Location[]{ dinnerRoom });
        connections.put(basement,   new Location[]{ hall });
    }
}

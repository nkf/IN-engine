package deadgiveaway.location;

import engine.Location;

import java.util.HashMap;

public class House {
    public static final Location dinnerRoom = new Location("the dinner room");
    public static final Location toilet = new Location("the toilet");
    public static final Location bedroom = new Location("the bedroom");
    public static final Location hall = new Location("the hall");
    public static final Location kitchen = new Location("the kitchen");
    public static final Location basement = new Location("the basement");
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

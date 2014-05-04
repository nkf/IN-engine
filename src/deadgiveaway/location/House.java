package deadgiveaway.location;

import engine.Location;

import java.util.HashMap;

public class House {
    public static final Location dinnerRoom = new Location("the dinner room");
    public static final Location toilet = new Location("the toilet");
    public static final Location bedroom = new Location("the bedroom");
    public static final HashMap<Location,Location[]> connections = new HashMap<Location, Location[]>();
    static {
        connections.put(dinnerRoom, new Location[]{ toilet, bedroom });
        connections.put(toilet, new Location[]{ dinnerRoom });
        connections.put(bedroom, new Location[]{ dinnerRoom });
    }
}

package engine;
import java.util.List;

public abstract class Actor {
    public abstract Action selectAction(List<Action> actions);
    private Location currentLocation;
    private boolean active = true;
    protected final String Name;

    public String getName() {
        return Name;
    }

    public Location getLocation() {
        return currentLocation;
    }
    public void setLocation(Location location) {
        currentLocation.actors.remove(this);
        this.currentLocation = location;
        location.actors.add(this);
    }
    public Actor(String name, Location startLocation) {
        Name = name;
        currentLocation = startLocation;
        currentLocation.actors.add(this);
    }

    @Override
    public String toString() {
        return Name +" @ "+ currentLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

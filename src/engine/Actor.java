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
    public void setLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
    public Actor(String name, Location startLocation) {
        Name = name;
        currentLocation = startLocation;
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

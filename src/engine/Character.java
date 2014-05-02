package engine;
import java.util.List;

public abstract class Character {
    public abstract Action selectAction(List<Action> actions);
    private Location currentLocation;
    private String Name;
    public Location getLocation() {
        return currentLocation;
    }
    public void setLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
    public String getName() {
        return Name;
    }
    public Character(String name, Location startLocation) {
        Name = name;
        currentLocation = startLocation;
    }
}

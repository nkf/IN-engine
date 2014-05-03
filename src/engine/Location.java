package engine;

public class Location {
    public final String Name;

    public Location(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }
}

package engine;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class Action {

    public abstract boolean precondition();
    public abstract void postcondition();
    public abstract String description();
    public abstract String narrativeDescription();
    public final Character character;
    public final List<Location> locations;
    public final Location location;
    public final WorldState state;
    protected Action(Character character, WorldState state) {
        this.character = character;
        locations = new ArrayList<Location>();
        locations.add( character.getLocation() );
        location = character.getLocation();
        this.state = state;
    }
}

package engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Action {

    public abstract boolean precondition();
    public abstract void postcondition();
    public abstract String description();
    public abstract String narrativeDescription();
    public abstract String effectDescription();
    public final Actor actor;
    public final List<Location> locations;
    public final Location location;
    public final WorldState state;
    protected Action(Actor actor, WorldState state) {
        this.actor = actor;
        locations = new ArrayList<Location>();
        locations.add( actor.getLocation() );
        location = actor.getLocation();
        this.state = state;
    }
}

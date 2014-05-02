package engine;

import java.lang.reflect.Type;
import java.util.List;

public abstract class Action {

    public abstract boolean precondition();
    public abstract void postcondition();
    public abstract String description();
    public abstract String narrativeDescription();
    public abstract Type[] argumentVariables();
    public abstract Action create(Character character, WorldState state, List<Object> args);
    public final Character character;
    public final Location location;
    public final WorldState state;
    public Action(Character character, Location location, WorldState state) {
        this.character = character;
        this.location = character.getLocation();
        this.state = state;
    }

}

package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Actor;

import java.lang.reflect.Type;
import java.util.List;

public class GoToRoom extends Action{
    private Location room;

    @Override
    public boolean precondition() {
        return !((DGACharacter) actor).isBusy;
    }

    @Override
    public void postcondition() {
        actor.setLocation(room);
    }

    @Override
    public String description() {
        return "Go to " + room.Name;
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " walks from " + location + " into " + room;
    }
    private GoToRoom(Actor c, WorldState s, Location room) {
        super(c, s);
        this.room = room;
        locations.add(room);
    }

    public static final GoToRoomFactory factory = new GoToRoomFactory();
    public static class GoToRoomFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new GoToRoom(actor, state, (Location)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Location.class };
        }
    }

    @Override
    public String toString() {
        return actor.getName() + " ~ " + locations + " -> " + room;
    }
}

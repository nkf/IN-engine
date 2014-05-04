package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Character;

import java.lang.reflect.Type;
import java.util.List;

public class GoToRoom extends Action{
    private Location room;

    @Override
    public boolean precondition() {
        return !((DGACharacter)character).isBusy();
    }

    @Override
    public void postcondition() {
        character.setLocation(room);
    }

    @Override
    public String description() {
        return "Go to " + room.Name;
    }

    @Override
    public String narrativeDescription() {
        return character.Name + " walks from " + location + " into " + room;
    }
    private GoToRoom(Character c, WorldState s, Location room) {
        super(c, s);
        this.room = room;
        locations.add(room);
    }

    public static final GoToRoomFactory factory = new GoToRoomFactory();
    public static class GoToRoomFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new GoToRoom(character, state, (Location)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Location.class };
        }
    }

    @Override
    public String toString() {
        return character.Name + " ~ " + locations.get(0) + " -> " + room;
    }
}

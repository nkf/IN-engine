package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Character;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class ContinueDying extends Action {

    @Override
    public boolean precondition() {
        return character instanceof Victim
            && ((Victim)character).isBeingKilled;
    }

    @Override
    public void postcondition() {
        //See ContinueMurder for postconditions on murder.
    }

    @Override
    public String description() {
        return "You see a bright light at the end of a tunnel";
    }

    @Override
    public String narrativeDescription() {
        return character.Name + " falls to the floor and lands in a pool of blood";
    }
    private ContinueDying(Character c, WorldState s) {
        super(c, s);
    }

    public static final ContinueDyingFactory factory = new ContinueDyingFactory();
    public static class ContinueDyingFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new ContinueDying(character, state);
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ };
        }
    }

}

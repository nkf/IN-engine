package deadgiveaway.actions;

import engine.*;
import engine.Actor;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class ContinueDying extends Action {

    @Override
    public boolean precondition() {
        return actor instanceof Victim
            && ((Victim) actor).isBeingKilled;
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
        return actor.Name + " falls to the floor and lands in a pool of blood";
    }
    private ContinueDying(Actor c, WorldState s) {
        super(c, s);
    }

    public static final ContinueDyingFactory factory = new ContinueDyingFactory();
    public static class ContinueDyingFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new ContinueDying(actor, state);
        }
        @Override
        public Type[] parameterVariables() {
            return new Type[]{ };
        }
    }

}

package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.location.House;
import engine.Action;
import engine.ActionFactory;
import engine.Actor;
import engine.WorldState;

import java.lang.reflect.Type;
import java.util.List;

public class DoNothing extends Action{
    @Override
    public boolean precondition() {
        return !((DGACharacter) actor).isBusy;
    }

    @Override
    public void postcondition() {

    }

    @Override
    public String description() {
        return "Stand around and do nothing.";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " stands and wonder.";
    }

    @Override
    public String effectDescription() {
        return "You do nothing of importance.";
    }

    private DoNothing(Actor c, WorldState s) {
        super(c, s);
    }

    public static final DoNothingFactory factory = new DoNothingFactory();
    public static class DoNothingFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new DoNothing(actor, state);
        }
        @Override
        public Type[] parameterVariables() {
            return new Type[]{ };
        }
    }

}

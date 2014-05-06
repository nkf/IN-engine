package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.location.House;
import engine.Action;
import engine.ActionFactory;
import engine.Actor;
import engine.WorldState;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class EatShitAndDie extends Action {
    @Override
    public boolean precondition() {
        return location == House.toilet
            && actor == Victim.player
            && !((DGACharacter) actor).isBusy;
    }

    @Override
    public void postcondition() {
        actor.setActive(false);
        Victim v = Victim.getActiveNonPlayer();
        if(v != null) v.setPlayerControlled();
        else state.setGameOver();
    }

    @Override
    public String description() {
        return "Eat some shit from the toilet and die";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " eats a bit of shit from the toilet and dies";
    }
    private EatShitAndDie(Actor c, WorldState s) {
        super(c, s);
    }

    public static final EatShitAndDieFactory factory = new EatShitAndDieFactory();
    public static class EatShitAndDieFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new EatShitAndDie(actor, state);
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ };
        }
    }

}

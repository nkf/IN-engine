package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.characters.Victim;
import engine.Action;
import engine.ActionFactory;
import engine.Actor;
import engine.WorldState;

import java.lang.reflect.Type;
import java.util.List;

public class KnockOutPerson extends Action {
    private DGACharacter target;
    @Override
    public boolean precondition() {
        return actor == Victim.player
            && (Integer)state.get("MurderScenesVisited") > 0
            && !((DGACharacter)actor).isBusy;
    }

    @Override
    public void postcondition() {
        target.isBusy = true;
    }

    @Override
    public String description() {
        return "Thinking that " + target.getName() + " is the murderer, you knock " + target.sex + " the him out";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " knocks " + target.getName() + " out cold, leaving "+target.sex+" unconscious on the floor";
    }

    @Override
    public String effectDescription() {
        return actor.getName() + " knock " + target.getName() + " out cold, leaving "+target.sex+" unconscious on the floor";
    }
    private KnockOutPerson(Actor c, WorldState s, Actor target) {
        super(c, s);
        this.target = (DGACharacter)target;
    }

    public static final KnockOutPersonFactory factory = new KnockOutPersonFactory();
    public static class KnockOutPersonFactory implements ActionFactory {
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new KnockOutPerson(actor, state, (Actor)args.get(0));
        }
        public Type[] parameterVariables() {
            return new Type[]{ Actor.class };
        }
    }
}

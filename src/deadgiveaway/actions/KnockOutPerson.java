package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.characters.Victim;
import deadgiveaway.items.Item;
import engine.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KnockOutPerson extends Action {
    private DGACharacter target;
    @Override
    public boolean precondition() {
        return actor == Victim.player
            && ((Set<Item>)state.get("BodiesInvestigated")).size() > 0
            && !((DGACharacter)actor).isBusy
            && !target.isBusy
            && target.getLocation() == location
            && target != actor
            && ((HashSet<DGACharacter>)state.get("KnockedOutCharacters")).size() == 0;
    }

    @Override
    public void postcondition() {
        ((HashSet<DGACharacter>)state.get("KnockedOutCharacters")).add(target);
        target.isBusy = true;
    }
    private String himOrHer(DGACharacter.Sex sex) {
        if(sex == DGACharacter.Sex.Female) return "her";
        else                               return "him";
    }
    @Override
    public String description() {
        return "Thinking that " + target.getName() + " is the murderer, you knock " + himOrHer(target.sex) + " out";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " knocks " + target.getName() + " out cold, leaving " + himOrHer(target.sex) + " unconscious on the floor";
    }

    @Override
    public String effectDescription() {
        return actor.getName() + " knock " + target.getName() + " out cold, leaving " + himOrHer(target.sex) + " unconscious on the floor";
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

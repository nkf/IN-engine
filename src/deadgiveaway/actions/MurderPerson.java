package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Actor;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class MurderPerson extends Action {
    private DGACharacter target;

    @Override
    public boolean precondition() {
        return actor instanceof Murderer
            && target instanceof Victim
            && target.getLocation() == location
            && !((DGACharacter) actor).isBusy();
    }

    @Override
    public void postcondition() {
        Murderer murderer = (Murderer) actor;
        murderer.murderInProgress = true;
        murderer.setBusy(true);
        Victim victim = (Victim)target;
        victim.isBeingKilled = true;
        victim.setBusy(true);
    }

    @Override
    public String description() {
        return "Attempt to kill " + target;
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " starts murdering " + target.getName() + " in front of your very eyes";
    }
    private MurderPerson(Actor c, WorldState s, Actor target) {
        super(c, s);
        this.target = (DGACharacter)target;
    }

    public static final MurderPersonFactory factory = new MurderPersonFactory();
    public static class MurderPersonFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new MurderPerson(actor, state, (Actor)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Actor.class };
        }
    }

}

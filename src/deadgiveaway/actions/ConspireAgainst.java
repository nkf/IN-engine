package deadgiveaway.actions;


import deadgiveaway.characters.DGACharacter;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;
import deadgiveaway.items.Item;
import engine.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class ConspireAgainst extends Action{
    private DGACharacter against;
    private DGACharacter with;

    @Override
    public boolean precondition() {
        return against != with
            && against != actor
            && with != actor
            && with.getLocation() == location
            && actor == Victim.player
            && ((Set<Item>)state.get("BodiesInvestigated")).size() > 0;
    }

    @Override
    public void postcondition() {
        //this should be split into two actions with different preconditions but... what ever
        if(with instanceof Murderer) {
            actor.setActive(false);
            Victim activeNonPlayer = Victim.getActiveNonPlayer();
            if (activeNonPlayer != null)
                activeNonPlayer.setPlayerControlled();
            else
                state.setGameOver();
        } else {
            state.setGameOver();
        }
    }

    @Override
    public String description() {
        return "Tell " + with.getName() + " that you think " + against.getName() + " is the murderer";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " talks to " + with.getName();
    }

    @Override
    public String effectDescription() {
        if(with instanceof Murderer) return with.getName() + " looks at you for a moment, before killing you with a quick blow to the head.";
        else return "You talk with " + with.getName() + ", and conclude that its time call the police and tell them your story.";
    }

    private ConspireAgainst(Actor c, WorldState s, Actor with, Actor against) {
        super(c, s);
        this.with = (DGACharacter)with;
        this.against = (DGACharacter)against;
    }

    public static final ConspireAgainstFactory factory = new ConspireAgainstFactory();
    public static class ConspireAgainstFactory implements ActionFactory {
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new ConspireAgainst(actor, state, (Actor)args.get(0), (Actor)args.get(1));
        }
        public Type[] parameterVariables() {
            return new Type[]{ Actor.class, Actor.class };
        }
    }

}

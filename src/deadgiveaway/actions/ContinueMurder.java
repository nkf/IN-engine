package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Actor;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class ContinueMurder extends Action {
    private DGACharacter target;

    @Override
    public boolean precondition() {
        return actor instanceof Murderer
            && target instanceof Victim
            && ((Victim)target).isBeingKilled
            && ((Murderer) actor).murderInProgress;
    }

    @Override
    public void postcondition() {
        //Kill the victim
        Victim victim = (Victim) target;
        victim.setActive(false);
        victim.isBusy = false;

        Murderer murderer = (Murderer) actor;
        murderer.murderInProgress = false;
        murderer.isBusy = false;

        //Find new character for the player to control, if the victim was the player.
        if(victim == Victim.player) {
            Victim activeNonPlayer = Victim.getActiveNonPlayer();
            if (activeNonPlayer != null)
                activeNonPlayer.setPlayerControlled();
            else
                state.setGameOver();
        }
    }

    @Override
    public String description() {
        return "Finish off" + target + "!";
    }

    @Override
    public String narrativeDescription() {
        return actor.Name + " stands above the body of " + target.Name + " with a weapon in hand";
    }
    private ContinueMurder(Actor c, WorldState s, Actor target) {
        super(c, s);
        this.target = (DGACharacter)target;
    }

    public static final ContinueMurderFactory factory = new ContinueMurderFactory();
    public static class ContinueMurderFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new ContinueMurder(actor, state, (Actor)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Actor.class };
        }
    }

}

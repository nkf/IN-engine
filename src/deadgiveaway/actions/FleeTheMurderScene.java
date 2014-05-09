package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.items.Item;
import deadgiveaway.items.ItemType;
import deadgiveaway.location.Room;
import engine.*;
import engine.Actor;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class FleeTheMurderScene extends Action {
    private final Room room;
    private final DGACharacter target;

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
        Murderer murderer = (Murderer) actor;

        victim.setActive(false);
        victim.isBusy = false;
        //Add a body to the scene
        Item body = new Item( victim.getActualName(), ItemType.BODY, (Room)location, murderer.items.remove(0) );
        state.addVariable("BodyOf"+victim.getActualName(), body);

        murderer.murderInProgress = false;
        murderer.isBusy = false;
        actor.setLocation(room);

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
        return "Finish off" + target.getName() + "! and flee to " + room;
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " walks from " + location + " into " + room;
    }

    @Override
    public String effectDescription() {
        return "";
    }

    private FleeTheMurderScene(Actor c, WorldState s, Actor target, Room room) {
        super(c, s);
        this.target = (DGACharacter)target;
        this.room = room;
    }

    public static final ContinueMurderFactory factory = new ContinueMurderFactory();
    public static class ContinueMurderFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new FleeTheMurderScene(actor, state, (Actor)args.get(0), (Room)args.get(1));
        }
        @Override
        public Type[] parameterVariables() {
            return new Type[]{ Actor.class, Location.class };
        }
    }

}

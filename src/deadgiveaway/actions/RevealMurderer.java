package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;
import engine.*;
import engine.Actor;

import java.lang.reflect.Type;
import java.util.List;

public class RevealMurderer extends Action{
    private DGACharacter accused;
    @Override
    public boolean precondition() {
        return accused instanceof Murderer
            && accused.getLocation() == location
            && ((Murderer)accused).murderInProgress
            && actor == Victim.player
            && !Victim.player.isBeingKilled;
    }

    @Override
    public void postcondition() {
        state.setGameOver();
    }

    @Override
    public String description() {
        return "Reveal that "+ accused.getName() + " is the murderer!";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName() + " reveals that the murderer is " + accused.getName() + "!";
    }

    @Override
    public String effectDescription() {
        return "You reveal " + accused.getName() + " to be the murderer! Thus solving the murder mystery!";
    }

    private RevealMurderer(Actor c, WorldState s, DGACharacter accused) {
        super(c, s);
        this.accused = accused;
    }

    public static final RevealMurdererFactory factory = new RevealMurdererFactory();
    public static class RevealMurdererFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new RevealMurderer(actor, state, (DGACharacter)args.get(0));
        }
        @Override
        public Type[] parameterVariables() {
            return new Type[]{ Actor.class };
        }
    }

}

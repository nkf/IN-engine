package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Character;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class ContinueMurder extends Action {
    private DGACharacter target;

    @Override
    public boolean precondition() {
        return character instanceof Murderer
            && target instanceof Victim
            && ((Victim)target).isBeingKilled
            && ((Murderer)character).murderInProgress;
    }

    @Override
    public void postcondition() {
        //Kill the victim
        Victim victim = (Victim) target;
        victim.setActive(false);
        victim.setBusy(false);

        Murderer murderer = (Murderer) character;
        murderer.murderInProgress = false;
        murderer.setBusy(false);

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
        return character.Name + " stands above the body of " + target.Name + " with a weapon in hand";
    }
    private ContinueMurder(Character c, WorldState s, Character target) {
        super(c, s);
        this.target = (DGACharacter)target;
    }

    public static final ContinueMurderFactory factory = new ContinueMurderFactory();
    public static class ContinueMurderFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new ContinueMurder(character, state, (Character)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Character.class };
        }
    }

}

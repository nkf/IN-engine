package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.*;
import engine.Character;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class MurderPerson extends Action {
    private DGACharacter target;

    @Override
    public boolean precondition() {
        return character instanceof Murderer
            && target instanceof Victim
            && target.getLocation() == location
            && !((DGACharacter)character).isBusy();
    }

    @Override
    public void postcondition() {
        Murderer murderer = (Murderer)character;
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
        return character.Name + " starts murdering " + target.Name + " in front of your very eyes";
    }
    private MurderPerson(Character c, WorldState s, Character target) {
        super(c, s);
        this.target = (DGACharacter)target;
    }

    public static final MurderPersonFactory factory = new MurderPersonFactory();
    public static class MurderPersonFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new MurderPerson(character, state, (Character)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Character.class };
        }
    }

}

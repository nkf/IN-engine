package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;
import deadgiveaway.location.House;
import engine.*;
import engine.Character;

import java.lang.reflect.Type;
import java.util.List;

public class RevealMurderer extends Action{
    private DGACharacter accused;
    @Override
    public boolean precondition() {
        return accused instanceof Murderer
            && accused.getLocation() == location
            && ((Murderer)accused).murderInProgress
            && character == Victim.player;
    }

    @Override
    public void postcondition() {
        state.setGameOver();
    }

    @Override
    public String description() {
        return "Reveal that "+ accused.Name + " is the murderer!";
    }

    @Override
    public String narrativeDescription() {
        return character.Name + " reveals that the murderer is " + accused.Name + "!";
    }
    private RevealMurderer(Character c, WorldState s, DGACharacter accused) {
        super(c, s);
        this.accused = accused;
    }

    public static final RevealMurdererFactory factory = new RevealMurdererFactory();
    public static class RevealMurdererFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new RevealMurderer(character, state, (DGACharacter)args.get(0));
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ Character.class };
        }
    }

}

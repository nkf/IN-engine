package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import engine.Action;
import engine.ActionFactory;
import engine.Character;
import engine.WorldState;
import deadgiveaway.characters.Victim;

import java.lang.reflect.Type;
import java.util.List;

public class EatShitAndDie extends Action {
    @Override
    public boolean precondition() {
        return location.Name.equals("the toilet")
            && character == Victim.player
            && !((DGACharacter)character).isBusy();
    }

    @Override
    public void postcondition() {
        character.setActive(false);
        Victim v = Victim.getActiveNonPlayer();
        if(v != null) v.setPlayerControlled();
        else state.setGameOver();
    }

    @Override
    public String description() {
        return "Eat some shit from the toilet and die";
    }

    @Override
    public String narrativeDescription() {
        return character.Name + " eats a bit of shit from the toilet and dies";
    }
    private EatShitAndDie(engine.Character c, WorldState s) {
        super(c, s);
    }

    public static final EatShitAndDieFactory factory = new EatShitAndDieFactory();
    public static class EatShitAndDieFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new EatShitAndDie(character, state);
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ };
        }
    }

}

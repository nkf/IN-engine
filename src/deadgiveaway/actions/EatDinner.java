package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.location.House;
import engine.*;
import engine.Character;

import java.lang.reflect.Type;
import java.util.List;

public class EatDinner extends Action{
    @Override
    public boolean precondition() {
        return location == House.dinnerRoom
            && !((DGACharacter)character).isBusy();
    }

    @Override
    public void postcondition() {

    }

    @Override
    public String description() {
        return "Eat some food at the table";
    }

    @Override
    public String narrativeDescription() {
        return character.Name + " eats a bit of dinner at the table";
    }
    private EatDinner(Character c, WorldState s) {
        super(c, s);
    }

    public static final EatDinnerFactory factory = new EatDinnerFactory();
    public static class EatDinnerFactory implements ActionFactory {
        @Override
        public Action create(Character character, WorldState state, List<Object> args) {
            return new EatDinner(character, state);
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ };
        }
    }

}

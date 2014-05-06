package deadgiveaway.actions;

import deadgiveaway.characters.DGACharacter;
import deadgiveaway.location.House;
import engine.*;
import engine.Actor;

import java.lang.reflect.Type;
import java.util.List;

public class EatDinner extends Action{
    @Override
    public boolean precondition() {
        return location == House.dinnerRoom
            && !((DGACharacter) actor).isBusy;
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
        return actor.getName() + " eats a bit of dinner at the table";
    }
    private EatDinner(Actor c, WorldState s) {
        super(c, s);
    }

    public static final EatDinnerFactory factory = new EatDinnerFactory();
    public static class EatDinnerFactory implements ActionFactory {
        @Override
        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new EatDinner(actor, state);
        }
        @Override
        public Type[] argumentVariables() {
            return new Type[]{ };
        }
    }

}

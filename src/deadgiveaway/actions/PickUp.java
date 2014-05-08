package deadgiveaway.actions;

import deadgiveaway.characters.Murderer;
import engine.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Asger on 08/05/14.
 */
public class PickUp extends Action {

    protected Item item;

    @Override
    public boolean precondition() {
        return actor.getLocation().equals(item.getLocation()) // Actor and Item must be in same location
                && actor instanceof Murderer;
    }

    @Override
    public void postcondition() {
        item.setLocation(null);

        actor.items.add(item);
    }

    @Override
    public String description() {
        return "Pick up "+item.name;
    }

    @Override
    public String narrativeDescription() {
        return actor.getName()+" picked up "+item.name;
    }

    @Override
    public String effectDescription() {
        return "You pick up "+item.name;
    }

    public static final PickUpFactory factory = new PickUpFactory();
    public static class PickUpFactory implements ActionFactory {

        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new PickUp(actor, state, (Item) args.get(0));
        }
        public Type[] parameterVariables() {
            return new Type[]{ Item.class };
        }
    }

    protected PickUp(Actor actor, WorldState state, Item i) {
        super(actor, state);
        item = i;
    }
}

package deadgiveaway.actions;

import deadgiveaway.characters.Victim;
import deadgiveaway.items.Item;
import deadgiveaway.items.ItemType;
import deadgiveaway.location.Room;
import engine.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class InvestigateBody extends Action {
    protected Item body;

    @Override
    public boolean precondition() {
        return ((Room)location).items.contains(body)
            && actor == Victim.player
            && body.type == ItemType.BODY;
    }

    @Override
    public void postcondition() {
        ((Set<Item>)state.get("BodiesInvestigated")).add(body);
    }

    @Override
    public String description() {
        return "Investigate the body on the floor";
    }

    @Override
    public String narrativeDescription() {
        return actor.getName()+" looks at a dead body";
    }

    @Override
    public String effectDescription() {
        return "You look at the body and see that it is the body of " + body.name + " that lies on the floor. The murder appears to have been committed with " + body.accessory.name;
    }

    public static final InvestigateBodyFactory factory = new InvestigateBodyFactory();
    public static class InvestigateBodyFactory implements ActionFactory {

        public Action create(Actor actor, WorldState state, List<Object> args) {
            return new InvestigateBody(actor, state, (Item)args.get(0));
        }
        public Type[] parameterVariables() {
            return new Type[]{ Item.class };
        }
    }

    private InvestigateBody(Actor actor, WorldState state, Item body) {
        super(actor, state);
        this.body = body;
    }

}

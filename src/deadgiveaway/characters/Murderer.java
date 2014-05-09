package deadgiveaway.characters;

import deadgiveaway.actions.MurderPerson;
import deadgiveaway.actions.PickUp;
import deadgiveaway.items.ItemType;
import engine.Action;
import engine.Location;
import deadgiveaway.ai.RandomAI;

import java.util.ArrayList;
import java.util.List;

public class Murderer extends DGACharacter {
    private RandomAI ai = new RandomAI();
    public boolean murderInProgress = false;

    public Murderer(String name, Location startLocation, Sex sex) {
        super(name, startLocation, sex);
    }
    public boolean hasMurderWeapon() {
        for (int i = 0; i < items.size(); i++)
            if (items.get(i).type == ItemType.MURDER_WEAPON) return true;
        return false;
    }
//    /*
    //Random actions
    @Override
    public Action selectAction(List<Action> actions) {
        return actions.get( ai.getIndex(actions.size()) );
    }
//    */
    /*
    //Prioritize MurderPerson
    @Override
    public Action selectAction(List<Action> actions) {
        for (Action a : actions) {
            if(a instanceof MurderPerson) return a;
        }
        return actions.get( ai.getIndex(actions.size()) );
    }
    */
    /*
    //Prioritize MurderPerson - Pickup
    @Override
    public Action selectAction(List<Action> actions) {
        for (Action a : actions) {
            if(a instanceof MurderPerson) return a;
            if(a instanceof PickUp) return a;
        }
        return actions.get( ai.getIndex(actions.size()) );
    }
    */
    /*
    //Prioritize MurderPerson when alone with the person - Pickup
    @Override
    public Action selectAction(List<Action> actions) {
        List<Action> nonSuspect = new ArrayList<Action>();
        for (Action a : actions) {
            if(a instanceof MurderPerson && getLocation().actors.size() > 2) return a;
            if(a instanceof PickUp) return a;
            nonSuspect.add(a);
        }
        return nonSuspect.get( ai.getIndex( nonSuspect.size()) );
    }
    */
}

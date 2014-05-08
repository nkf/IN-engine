package deadgiveaway.characters;

import deadgiveaway.actions.MurderPerson;
import engine.Action;
import engine.Location;
import deadgiveaway.ai.RandomAI;

import java.util.List;

public class Murderer extends DGACharacter {
    private RandomAI ai = new RandomAI();
    public boolean murderInProgress = false;

    public Murderer(String name, Location startLocation, Sex sex) {
        super(name, startLocation, sex);
    }

    @Override
    public Action selectAction(List<Action> actions) {
        for (Action a : actions) {
            if(a instanceof MurderPerson) return a;
        }
        return actions.get( ai.getIndex(actions.size()) );
    }
}

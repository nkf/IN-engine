package deadgiveaway.characters;

import deadgiveaway.actions.MurderPerson;
import engine.Action;
import engine.Character;
import engine.Location;
import deadgiveaway.ai.RandomAI;

import java.util.List;

public class Murderer extends DGACharacter {
    private RandomAI ai = new RandomAI();
    public boolean murderInProgress = false;

    public Murderer(String name, Location startLocation) {
        super(name, startLocation);
    }

    @Override
    public Action selectAction(List<Action> actions) {
        for (Action a : actions) {
            if(a instanceof MurderPerson) return a;
        }
        return actions.get( ai.getIndex(actions.size()) );
    }
}

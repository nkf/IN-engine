package deadgiveaway.characters;

import engine.Action;
import engine.Character;
import engine.Location;
import deadgiveaway.ai.RandomAI;

import java.util.List;

public class Murderer extends DGACharacter {
    private RandomAI ai = new RandomAI();

    public Murderer(String name, Location startLocation) {
        super(name, startLocation);
    }

    @Override
    public Action selectAction(List<Action> actions) {
        return actions.get( ai.getIndex(actions.size()) );
    }
}

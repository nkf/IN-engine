package game.characters;

import engine.*;
import game.ai.RandomAI;

import java.util.List;

public class NPC extends engine.Character{
    private RandomAI ai = new RandomAI();

    public NPC(String name, Location startLocation) {
        super(name, startLocation);
    }

    @Override
    public Action selectAction(List<Action> actions) {
        return actions.get(ai.getIndex(actions.size()));
    }
}

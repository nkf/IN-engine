package deadgiveaway.characters;

import engine.*;
import engine.Actor;

public abstract class DGACharacter extends Actor {
    public DGACharacter(String name, Location startLocation) {
        super(name, startLocation);
    }
    public boolean isBusy = false;
}

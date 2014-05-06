package deadgiveaway.characters;

import engine.*;
import engine.Actor;

public abstract class DGACharacter extends Actor {
    public DGACharacter(String name, Location startLocation) {
        super(name, startLocation);
    }

    public boolean isBusy = false;

    @Override
    public String getName() {
        if (Victim.player.equals(this))
            return "You";
        return super.getName();
    }

    /**
     * Necessary to print actual name when starting the engine.
     * @return the NAME
     */
    public String getActualName() {
        return super.getName();
    }
}

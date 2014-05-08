package deadgiveaway.characters;

import engine.*;
import engine.Actor;

public abstract class DGACharacter extends Actor {
    public DGACharacter(String name, Location startLocation, Sex sex) {
        super(name, startLocation);
        if   (sex == Sex.Female)  this.sex = "Her";
        else this.sex = "Him";
    }

    public enum Sex{
        Male, Female
    }
    public boolean isBusy = false;
    public final String sex;
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

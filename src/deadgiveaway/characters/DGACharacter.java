package deadgiveaway.characters;

import deadgiveaway.items.Item;
import engine.*;
import engine.Actor;

import java.util.ArrayList;
import java.util.List;

public abstract class DGACharacter extends Actor {
    public DGACharacter(String name, Location startLocation, Sex sex) {
        super(name, startLocation);
        items = new ArrayList<Item>();
        if   (sex == Sex.Female)  this.sex = "Her";
        else this.sex = "Him";
    }
    public final List<Item> items;

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

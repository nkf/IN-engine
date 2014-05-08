package engine;

/**
 * Created by Asger on 08/05/14.
 */
public class Item {
    public final String   name;
    public final ItemType type;
    protected Location location;

    /**
     *
     * @param name Name of the item
     * @param type Type of the item
     * @param loc Initial location of the item
     */
    public Item(String name, ItemType type, Location loc) {
        this.name = name;
        this.type = type;
        setLocation(loc);
    }

    public void setLocation(Location loc) {
        // Remove from old location
        if (this.location != null)
            this.location.items.remove(this);

        this.location = loc;

        // Add to new location
        if(loc != null && !loc.items.contains(this))
            loc.items.add(this);
    }

    public Location getLocation() {
        return this.location;
    }

}

package deadgiveaway.items;

import deadgiveaway.location.Room;
import engine.Location;

/**
 * Created by Asger on 08/05/14.
 */
public class Item {
    public final String   name;
    public final ItemType type;
    public final Item     accessory;
    protected Room room;

    /**
     *
     * @param name name of the item
     * @param type Type of the item
     * @param loc Initial room of the item
     */
    public Item(String name, ItemType type, Room loc, Item accessory) {
        this.name = name;
        this.type = type;
        this.accessory = accessory;
        setRoom(loc);
    }
    public Item(String name, ItemType type, Room loc) {
        this(name, type, loc, null);
    }

    public void setRoom(Room loc) {
        // Remove from old room
        if (this.room != null)
            this.room.items.remove(this);

        this.room = loc;

        // Add to new room
        if(loc != null && !loc.items.contains(this))
            loc.items.add(this);
    }

    public Location getRoom() {
        return this.room;
    }

}

package deadgiveaway.location;

import deadgiveaway.characters.DGACharacter;
import engine.Location;

import java.util.*;

public class House {
    public static final Room dinnerRoom =   new Room("the dinner room");
    public static final Room toilet =       new Room("the toilet");
    public static final Room bedroom =      new Room("the bedroom");
    public static final Room hall =         new Room("the hall");
    public static final Room kitchen =      new Room("the kitchen");
    public static final Room basement =     new Room("the basement");
    public static final HashMap<Location,Location[]> connections = new HashMap<Location, Location[]>();

    static {
        connections.put(dinnerRoom, new Location[]{ hall, bedroom, kitchen });
        connections.put(toilet,     new Location[]{ hall });
        connections.put(bedroom,    new Location[]{ dinnerRoom });
        connections.put(hall,       new Location[]{ toilet, basement, dinnerRoom });
        connections.put(kitchen,    new Location[]{ dinnerRoom });
        connections.put(basement,   new Location[]{ hall });
    }

    public static final Person[] Persons = new Person[4];
    public static class Person {
        public final String name;
        public final DGACharacter.Sex sex;
        private Person(String name, DGACharacter.Sex sex) {
            this.name = name;
            this.sex = sex;
        }
    }

    static {
        Persons[0] = new Person("Lisa", DGACharacter.Sex.Female);
        Persons[1] = new Person("Jack", DGACharacter.Sex.Male);
        Persons[2] = new Person("Peter", DGACharacter.Sex.Male);
        Persons[3] = new Person("Mike", DGACharacter.Sex.Male);
    }
    private static List<Person> pl;
    public static Random rng = new Random();
    public static Person GetRandomPerson() {
        if(pl == null || pl.isEmpty()) pl = new ArrayList<Person>(Arrays.asList(Persons));
        Person res = pl.remove(rng.nextInt(pl.size()));
        return res;
    }
}

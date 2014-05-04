import engine.*;
import engine.Character;
import deadgiveaway.actions.EatDinner;
import deadgiveaway.actions.EatShitAndDie;
import deadgiveaway.actions.GoToRoom;
import deadgiveaway.actions.MurderPerson;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Define locations
        Location dinnerRoom = new Location("the dinner room");
        Location toilet = new Location("the toilet");
        Location bedroom = new Location("the bedroom");

        //Define connections between locations
        HashMap<Location, Location[]> connections = new HashMap<Location, Location[]>();
        connections.put(dinnerRoom, new Location[]{ toilet, bedroom });
        connections.put(toilet, new Location[]{ dinnerRoom });
        connections.put(bedroom, new Location[]{ dinnerRoom });

        //Define characters
        Character[] characters = new Character[] {
                new Victim("Lisa", dinnerRoom, false),
                new Victim("Peter", dinnerRoom, false),
                new Victim("Jack", dinnerRoom, true),
                new Murderer("Mike", dinnerRoom)
        };

        //Define action actions
        ActionFactory[] actions = new ActionFactory[] {
            GoToRoom.factory,
            EatDinner.factory,
            EatShitAndDie.factory,
            MurderPerson.factory
        };

        //Define initial worldstate ???
        WorldState worldState = new WorldState();

        //Fire up the engine
        final Engine game = new Engine(characters, connections, actions, worldState);
        game.addTurnListener(new TurnEvent() {
            public void TurnPassed() {
                List<Action> recap = game.turnRecap(Victim.player);
                for(Action a : recap) {
                    System.out.println(a.narrativeDescription());
                }
            }
        });
        game.start();
    }
}

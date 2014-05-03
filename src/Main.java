import engine.*;
import engine.Character;
import game.actions.EatDinner;
import game.actions.GoToRoom;
import game.characters.NPC;
import game.characters.Player;

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
        final Character player = new Player("Jack", dinnerRoom);
        Character[] characters = new Character[] {
                new NPC("Lisa", dinnerRoom),
                new NPC("Peter", dinnerRoom),
                player
        };

        //Define action actions
        ActionFactory[] actions = new ActionFactory[] {
            GoToRoom.factory,
            EatDinner.factory
        };

        //Define initial worldstate ???
        WorldState worldState = new WorldState();

        //Fire up the engine
        final Engine game = new Engine(characters, connections, actions, worldState);
        game.addTurnListener(new TurnEvent() {
            public void TurnPassed() {
                List<Action> recap = game.turnRecap(player);
                for(Action a : recap) {
                    System.out.println(a.narrativeDescription());
                }
            }
        });
        game.start();
    }
}

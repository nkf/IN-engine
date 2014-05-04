import deadgiveaway.actions.*;
import deadgiveaway.location.House;
import engine.*;
import engine.Character;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //See house class for location definitions.

        //Define characters
        Character[] characters = new Character[] {
                new Victim("Lisa", House.dinnerRoom, false),
                new Victim("Peter", House.dinnerRoom, false),
                new Victim("Jack", House.dinnerRoom, true),
                new Murderer("Mike", House.dinnerRoom)
        };

        //Define action actions
        ActionFactory[] actions = new ActionFactory[] {
                GoToRoom.factory,
                EatDinner.factory,
                EatShitAndDie.factory,
                MurderPerson.factory,
                ContinueMurder.factory,
                ContinueDying.factory,
                RevealMurderer.factory
        };

        //Define initial worldstate
        WorldState worldState = new WorldState();

        //Fire up the engine
        final Engine game = new Engine(characters, House.connections, actions, worldState);
        game.addTurnListener(new TurnEvent() {
            public void TurnStarted(Character character) {
                if(character != Victim.player) return;
                List<Action> recap = game.previousTurnRecap(Victim.player);
                for(Action a : recap) {
                    System.out.println(a.narrativeDescription());
                }
            }
        });
        game.start();
    }
}

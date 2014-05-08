import deadgiveaway.actions.*;
import deadgiveaway.characters.DGACharacter;
import deadgiveaway.location.House;
import engine.*;
import engine.Actor;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //See house class for location definitions.


        //Define characters
        Actor[] actors = new Actor[] {
                new Victim("Lisa", House.dinnerRoom, DGACharacter.Sex.Female, false),
                new Victim("Peter", House.dinnerRoom, DGACharacter.Sex.Male, false),
                new Victim("Jack", House.dinnerRoom, DGACharacter.Sex.Male, true),
                new Murderer("Mike", House.dinnerRoom, DGACharacter.Sex.Male)
        };

        //Define action actions
        ActionFactory[] actions = new ActionFactory[] {
                GoToRoom.factory,
                EatDinner.factory,
                //EatShitAndDie.factory,
                MurderPerson.factory,
                ContinueMurder.factory,
                ContinueDying.factory,
                RevealMurderer.factory,
                KnockOutPerson.factory,
                PickUp.factory
        };

        //Define initial worldstate
        WorldState worldState = new WorldState();
        worldState.addVariable("MurderScenesVisited", 0);

        worldState.addVariable("testKnife", new Item("Knife of batshit insane slaying", ItemType.MURDER_WEAPON, House.kitchen));


        //Fire up the engine
        System.out.println("You play as "+ Victim.player.getActualName());
        System.out.println("You are in "+ Victim.player.getLocation());

        final Engine game = new Engine(actors, House.connections, actions, worldState);
        game.addTurnListener(new TurnEvent() {
            public void TurnStarted(Actor actor) {
                if (actor != Victim.player) return;
                List<Action> recap = game.previousTurnRecap(Victim.player);
                for (Action a : recap) {
                    if (a.actor.equals((Victim.player)))
                        System.out.println(a.effectDescription());
                    else
                        System.out.println(a.narrativeDescription());
                }
            }
        });
        game.start();
        System.out.println("Game Ended");
    }
}

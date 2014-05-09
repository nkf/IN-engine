import deadgiveaway.actions.*;
import deadgiveaway.characters.DGACharacter;
import deadgiveaway.items.Item;
import deadgiveaway.items.ItemType;
import deadgiveaway.location.House;
import engine.*;
import engine.Actor;
import deadgiveaway.characters.Murderer;
import deadgiveaway.characters.Victim;

import java.util.HashSet;
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
                FleeTheMurderScene.factory,
                ContinueDying.factory,
                RevealMurderer.factory,
                KnockOutPerson.factory,
                PickUp.factory,
                InvestigateBody.factory,
                ConspireAgainst.factory,
                DoNothing.factory
        };

        //Define initial worldstate
        WorldState worldState = new WorldState();
        worldState.addVariable("BodiesInvestigated", new HashSet<Item>());
        worldState.addVariable("KnockedOutCharacters", new HashSet<DGACharacter>());

        worldState.addVariable("Knife", new Item("a knife", ItemType.MURDER_WEAPON, House.kitchen));
        worldState.addVariable("Wrench", new Item("a wrench", ItemType.MURDER_WEAPON, House.basement));
        worldState.addVariable("Lamp", new Item("a lamp", ItemType.MURDER_WEAPON, House.bedroom));
        worldState.addVariable("BaseballBat", new Item("a baseball bat", ItemType.MURDER_WEAPON, House.hall));


        //Fire up the engine
        System.out.println("You play as " + Victim.player.getActualName());
        System.out.println("You are in "+ Victim.player.getLocation());

        final Engine game = new Engine(actors, House.connections, actions, worldState);
        game.addTurnListener(new TurnEvent() {
            public void TurnStarted(Actor actor) {
                if (actor != Victim.player) return;
                PrintTurn(game);
            }
        });
        game.start();
        PrintTurn(game);
        System.out.println("Game Over");
    }
    private static void PrintTurn(Engine game) {
        List<Action> recap = game.previousTurnRecap(Victim.player);
        for (Action a : recap) {
            if (a.actor.equals((Victim.player)))
                System.out.println(a.effectDescription());
            else
                System.out.println(a.narrativeDescription());
        }
    }
}


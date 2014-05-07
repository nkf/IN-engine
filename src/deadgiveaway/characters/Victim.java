package deadgiveaway.characters;

import engine.*;
import deadgiveaway.ai.RandomAI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Victim extends DGACharacter {

    private RandomAI ai = new RandomAI();
    private Scanner scanner = new Scanner(System.in);
    private boolean playerControl;

    public boolean isBeingKilled = false;

    private static List<Victim> victims = new ArrayList<Victim>();
    public static Victim player;

    public Victim(String name, Location startLocation, boolean playerControlled) {
        super(name, startLocation);
        this.playerControl = playerControlled;
        if(playerControlled) player = this;
        victims.add(this);
    }
    public static Victim getActiveNonPlayer() {
        for (Victim v : victims) {
            if(v != player && v.isActive()) return v;
        }
        return null;
    }
    public void setPlayerControlled() {
        // Print actual name before switching control
        System.out.println("You now control " + getName());
        System.out.println("You are in the "+ getLocation());

        // Remove control from old player to this one
        if(player != null)
            player.playerControl = false;
        playerControl = true;
        player = this;
    }
    @Override
    public Action selectAction(List<Action> actions) {
        if(playerControl) {
            return playerAction(actions);
        } else {
            return aiAction(actions);
        }
    }
    private Action aiAction(List<Action> actions) {
        return actions.get(ai.getIndex(actions.size()));
    }

    private Action playerAction(List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ". " + actions.get(i).description());
        }
        return actions.get( getChoice(actions.size()-1) );
    }
    private int getChoice(int max) {
        int choice;
        do {
            choice = scanner.nextInt(); //fails with anything but ints.
        } while (choice < 0 || choice > max);
        return choice;
    }
}
